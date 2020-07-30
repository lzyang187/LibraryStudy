package com.iflytek.librarystudy.ijkplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.iflytek.librarystudy.R;
import com.iflytek.librarystudy.exoplayer.ExoPlayerActivity;

import java.io.File;

import tv.danmaku.ijk.media.exo.IjkExoMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IjkExoPlayerActivity extends AppCompatActivity implements IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnPreparedListener {
    private static final String TAG = "IjkExoPlayerActivity";
    private IjkExoMediaPlayer ijkExoMediaPlayer;
    private TextureView textureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijk_exo_player);
        initPlayer();
        findViewById(R.id.pause_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ijkExoMediaPlayer.isPlaying()) {
                    ijkExoMediaPlayer.pause();
                } else {
                    ijkExoMediaPlayer.start();
                }
            }
        });
        textureView = findViewById(R.id.texture_view);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Surface surface1 = new Surface(surface);
                ijkExoMediaPlayer.setSurface(surface1);
                ijkExoMediaPlayer.setDataSource(IjkPlayerActivity.VIDEO_PATH);
                ijkExoMediaPlayer.prepareAsync();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                Log.i(TAG, "onSurfaceTextureSizeChanged: " + width + " " + height);
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }

    private void initPlayer() {
        ijkExoMediaPlayer = new IjkExoMediaPlayer(this);
        ijkExoMediaPlayer.setVolume(1.0f, 1.0f);
        ijkExoMediaPlayer.setOnCompletionListener(this);
        ijkExoMediaPlayer.setOnErrorListener(this);
        ijkExoMediaPlayer.setOnVideoSizeChangedListener(this);
        ijkExoMediaPlayer.setOnPreparedListener(this);
    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        Log.i(TAG, "onCompletion: ");
    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
        Log.i(TAG, "onVideoSizeChanged: ");
        ViewGroup.LayoutParams layoutParams = textureView.getLayoutParams();
        layoutParams.width = 1440;
        layoutParams.height = 1440 * (i / i1);
        textureView.setLayoutParams(layoutParams);
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        Log.i(TAG, "onPrepared: ");
        ijkExoMediaPlayer.start();
    }
}
