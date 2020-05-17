package com.iflytek.librarystudy.exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.source.ClippingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.EventLogger;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener;
import com.iflytek.librarystudy.R;

import java.io.IOException;
import java.net.URI;

public class ExoPlayerActivity extends AppCompatActivity {
    private static final String TAG = "ExoPlayerActivity";

    private Button pauseBtn;
    private PlayerView playerView;
    private Uri mp4VideoUri = Uri.parse("http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4");
    private Uri aacAudioUri = Uri.parse("http://oss.kuyinyun.com/11W2MYCO/rescloud1/bccc7e01b7594907a55de3acf2259607.aac");
    private SimpleExoPlayer simpleExoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);
        pauseBtn = findViewById(R.id.pause_button);
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                simpleExoPlayer.setPlayWhenReady(false);
//                simpleExoPlayer.stop();
//                simpleExoPlayer.stop(true);
                simpleExoPlayer.seekTo(50000);
            }
        });
        playerView = findViewById(R.id.player_view);
        simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(simpleExoPlayer);
//        simpleExoPlayer.setVideoTextureView();
//        simpleExoPlayer.setVideoSurfaceView();
        playerView.setUseController(true);

        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "libraryStudy"));
        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(mp4VideoUri);

        //视频播放剪辑为从​​5秒开始到10秒结束。
//        ClippingMediaSource clippingSource =
//                new ClippingMediaSource(videoSource,
//                        /* startPositionUs= */ 5_000_000,
//                        /* endPositionUs= */ 10_000_000);

        //给定一个视频文件和一个单独的字幕文件，MergingMediaSource可以将它们合并到一个源中进行播放。
        // Build the subtitle MediaSource.
//        Format subtitleFormat = Format.createTextSampleFormat(
//                id, // An identifier for the track. May be null.
//                MimeTypes.APPLICATION_SUBRIP, // The mime type. Must be set correctly.
//                selectionFlags, // Selection flags for the track.
//                language); // The subtitle language. May be null.
//        MediaSource subtitleSource =
//                new SingleSampleMediaSource.Factory(...)
//        .createMediaSource(subtitleUri, subtitleFormat, C.TIME_UNSET);
//        // Plays the video with the sideloaded subtitle.
//        MergingMediaSource mergedSource =
//                new MergingMediaSource(videoSource, subtitleSource);

        // Prepare the player with the source.
        simpleExoPlayer.prepare(videoSource);
        simpleExoPlayer.setRepeatMode(ExoPlayer.REPEAT_MODE_OFF);
        simpleExoPlayer.setPlayWhenReady(true);

        // Add a listener to receive events from the player.
        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {
                Log.i(TAG, "onLoadingChanged: " + isLoading);
            }


            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                //Player.STATE_IDLE: This is the initial state, the state when the player is stopped, and when playback failed.
                //Player.STATE_BUFFERING: The player is not able to immediately play from its current position. This mostly happens because more data needs to be loaded.
                //Player.STATE_READY: The player is able to immediately play from its current position.
                //Player.STATE_ENDED: The player finished playing all media.
                Log.i(TAG, "onPlayerStateChanged: " + playWhenReady + " " + playbackState);
            }

            @Override
            public void onPlaybackSuppressionReasonChanged(int playbackSuppressionReason) {

            }

            @Override
            public void onIsPlayingChanged(boolean isPlaying) {
                Log.i(TAG, "onIsPlayingChanged: " + isPlaying);
                if (isPlaying) {
                    // Active playback.
                } else {
                    // Not playing because playback is paused, ended, suppressed, or the player
                    // is buffering, stopped or failed. Check player.getPlaybackState,
                    // player.getPlayWhenReady, player.getPlaybackError and
                    // player.getPlaybackSuppressionReason for details.
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {
                Log.i(TAG, "onRepeatModeChanged: " + repeatMode);
            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
                Log.i(TAG, "onShuffleModeEnabledChanged: " + shuffleModeEnabled);
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                Log.e(TAG, "onPlayerError: " + error.getMessage());
                if (error.type == ExoPlaybackException.TYPE_SOURCE) {
                    IOException cause = error.getSourceException();
                    if (cause instanceof HttpDataSource.HttpDataSourceException) {
                        // An HTTP error occurred.
                        HttpDataSource.HttpDataSourceException httpError = (HttpDataSource.HttpDataSourceException) cause;
                        // This is the request for which the error occurred.
                        DataSpec requestDataSpec = httpError.dataSpec;
                        // It's possible to find out more about the error both by casting and by
                        // querying the cause.
                        if (httpError instanceof HttpDataSource.InvalidResponseCodeException) {
                            // Cast to InvalidResponseCodeException and retrieve the response code,
                            // message and headers.
                        } else {
                            // Try calling httpError.getCause() to retrieve the underlying cause,
                            // although note that it may be null.
                        }
                    }
                }
            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {
                Log.i(TAG, "onSeekProcessed: ");
            }
        });
        //添加音频监听
        simpleExoPlayer.addAudioListener(new AudioListener() {
            @Override
            public void onAudioSessionId(int audioSessionId) {
                Log.i(TAG, "onAudioSessionId: " + audioSessionId);
            }

            @Override
            public void onAudioAttributesChanged(AudioAttributes audioAttributes) {

            }

            @Override
            public void onVolumeChanged(float volume) {
                Log.i(TAG, "onVolumeChanged: " + volume);
            }
        });
        //添加视频监听
        simpleExoPlayer.addVideoListener(new VideoListener() {
            @Override
            public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
                Log.i(TAG, "onVideoSizeChanged: " + width + " " + height + " " +
                        unappliedRotationDegrees + " " + pixelWidthHeightRatio);
            }

            @Override
            public void onSurfaceSizeChanged(int width, int height) {
                Log.i(TAG, "onSurfaceSizeChanged: " + width + " " + height);
            }

            @Override
            public void onRenderedFirstFrame() {
                Log.i(TAG, "onRenderedFirstFrame: ");
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }

}
