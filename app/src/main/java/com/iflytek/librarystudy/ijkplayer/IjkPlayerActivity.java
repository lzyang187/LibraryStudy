package com.iflytek.librarystudy.ijkplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.iflytek.librarystudy.R;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IjkPlayerActivity extends AppCompatActivity implements IMediaPlayer.OnPreparedListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener, IjkMediaPlayer.OnMediaCodecSelectListener, IMediaPlayer.OnVideoSizeChangedListener {
    private static final String TAG = "IjkPlayerActivity";
    public static final String VIDEO_PATH = "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4";
    private SurfaceView surfaceView;
    private IjkMediaPlayer ijkMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijk_player);
        findViewById(R.id.pause_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ijkMediaPlayer.isPlaying()) {
                    ijkMediaPlayer.pause();
                } else {
                    ijkMediaPlayer.start();
                }
            }
        });
        initPlayer();
        surfaceView = findViewById(R.id.surface_view);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                initPlayer();
                ijkMediaPlayer.setDisplay(holder);
                try {
                    ijkMediaPlayer.setDataSource(VIDEO_PATH);
                    ijkMediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.i(TAG, "surfaceChanged: " + width + " " + height);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.i(TAG, "surfaceDestroyed: ");
            }
        });
    }

    private void initPlayer() {
        ijkMediaPlayer = new IjkMediaPlayer();
        ijkMediaPlayer.setLooping(true);
        ijkMediaPlayer.setVolume(0.5f, 0.5f);
        ijkMediaPlayer.setOnPreparedListener(this);
        ijkMediaPlayer.setOnCompletionListener(this);
        ijkMediaPlayer.setOnErrorListener(this);
        ijkMediaPlayer.setScreenOnWhilePlaying(true);
        ijkMediaPlayer.setOnMediaCodecSelectListener(this);
        ijkMediaPlayer.setOnVideoSizeChangedListener(this);
        ijkMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // 设置编码方式 1:硬编码 0:软编码
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "mediacodec", 1);
        // 设置不自动播放（ijkPlayer默认准备就自动播放）
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void releasePlayer() {
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.release();
            ijkMediaPlayer = null;
        }
        IjkMediaPlayer.native_profileEnd();
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        Log.i(TAG, "onPrepared: ");
    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        Log.i(TAG, "onCompletion: ");
    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        Log.i(TAG, "onError: " + i + " " + i1);
        return false;
    }

    @Override
    public String onMediaCodecSelect(IMediaPlayer iMediaPlayer, String s, int i, int i1) {
        Log.i(TAG, "onMediaCodecSelect: " + s + " " + i + " " + i1);
        return null;
    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
        Log.i(TAG, "onVideoSizeChanged: " + i + " " + i1 + " " + i2 + " " + i3);
        ViewGroup.LayoutParams layoutParams = surfaceView.getLayoutParams();
        layoutParams.width = 1440;
        layoutParams.height = 1440 * (i / i1);
        surfaceView.setLayoutParams(layoutParams);
    }

}
