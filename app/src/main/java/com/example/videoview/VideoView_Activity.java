package com.example.videoview;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.List;

public class VideoView_Activity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mc;
    private volatile int position;
    private boolean playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        playing = false;
        videoView = (VideoView) findViewById(R.id.videoView);
        mc = new MediaController(this);
        Uri videoLink = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video);
        videoView.setVideoURI(videoLink);
        videoView.setMediaController(mc);
    }

    @Override
    protected void onStart() {
        super.onStart();
        videoView.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (videoView.isPlaying()) {
            playing = true;
        }
        else {
            playing = false;
        }
        videoView.pause();
        position = videoView.getCurrentPosition();
        outState.putInt("position", position);
        outState.putBoolean("playing", playing);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        playing = savedInstanceState.getBoolean("playing");
        videoView.seekTo(savedInstanceState.getInt("position"));

        if (playing) {
            videoView.start();
        }
        else {
            videoView.pause();
        }

    }
}
