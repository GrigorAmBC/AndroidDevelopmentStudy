package com.example.flea;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SongControlActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private List<AudioTrack> mAudioTracks;
    private Button mPauseResumeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_control);


        try {
            getSupportActionBar().hide();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        mMediaPlayer = DataManager.getMediaPlayer();
        mAudioTracks = new ArrayList<>();
        mAudioTracks.addAll(DataManager.getAudioTracks());

        mPauseResumeButton = findViewById(R.id.PauseResumeButton);
        Log.i("number", "Size : " + mAudioTracks.size());
    }

    public void onPreviousSong(View v) {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mMediaPlayer.stop();
        }

        mMediaPlayer.reset();
        mMediaPlayer.release();

        mMediaPlayer = new MediaPlayer();
        DataManager.setMediaPlayer(mMediaPlayer);
        DataManager.setCurrentSongPosition(DataManager.getCurrentSongPosition()-1);
        Log.i("number", "Song position " + DataManager.getCurrentSongPosition().toString());
        try {
            mMediaPlayer.setDataSource(mAudioTracks.get(DataManager.getCurrentSongPosition()).data);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "IOEXCEPTION", Toast.LENGTH_SHORT).show();
        }
    }

    public void onNextSong(View v) {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mMediaPlayer.stop();
        }

        mMediaPlayer.reset();
        mMediaPlayer.release();

        mMediaPlayer = new MediaPlayer();
        DataManager.setMediaPlayer(mMediaPlayer);
        DataManager.setCurrentSongPosition(DataManager.getCurrentSongPosition()+1);
        Log.i("number", "Song position " + DataManager.getCurrentSongPosition().toString());
        try {
            mMediaPlayer.setDataSource(mAudioTracks.get(DataManager.getCurrentSongPosition()).data);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            Toast.makeText(this, "IOEXCEPTION", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void onPauseResumeSong(View v) {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mPauseResumeButton.setText("Resume");
        } else {
            mMediaPlayer.start();
            mPauseResumeButton.setText("Pause");
        }
    }

    public void onCloseActivity(View v) {
        finish();
    }

    @Override
    public void onBackPressed() {
        onCloseActivity(null);
    }
}
