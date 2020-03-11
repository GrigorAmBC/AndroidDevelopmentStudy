package com.example.flea;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPresenter {

    public static void setup(@NonNull Context context) {
        DataManager.setAudioTracks(context);
    }

    public static void update(){}

    public static void onPauseSong() {
        MediaPlayer player = DataManager.getMediaPlayer();

        if (player.isPlaying())
            player.pause();
    }

    public static void onResumeSong() {
        MediaPlayer player = DataManager.getMediaPlayer();

        if (player != null)
        if (!player.isPlaying())
            player.start();
    }

    public static boolean isSongPlaying() {
        MediaPlayer player = DataManager.getMediaPlayer();
        if (player != null)
            return DataManager.getMediaPlayer().isPlaying();
        return false;
    }

    public static String getCurrentSongTitle() {
        return DataManager.getCurrentAudioTrack().title;
    }

    public static AudioTrack getAudioTrackByPosition(Integer position) {
        return DataManager.getAudioTrackByPosition(position);
    }

    public static void onNextSong() {
        MediaPlayer player = DataManager.getMediaPlayer();

        if (player != null) {
            if (player.isPlaying()) {
                player.pause();
                player.stop();
            }

            player.reset();
            player.release();
        }

        player = new MediaPlayer();
        DataManager.setMediaPlayer(player);

        String audio_data = DataManager.getNextAudioTrack().data;
        DataManager.setCurrentSongPosition(DataManager.getCurrentSongPosition() + 1);

        try {
            player.setDataSource(audio_data);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void onPreviousSong() {
        MediaPlayer player = DataManager.getMediaPlayer();

        if (player != null) {
            if (player.isPlaying()) {
                player.pause();
                player.stop();
            }

            player.reset();
            player.release();
        }

        player = new MediaPlayer();
        DataManager.setMediaPlayer(player);

        String audio_data = DataManager.getPreviousAudioTrack().data;
        DataManager.setCurrentSongPosition(DataManager.getCurrentSongPosition() - 1);

        try {
            player.setDataSource(audio_data);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void playSong(Integer position) {
        DataManager.setCurrentSongPosition(position);
        Log.i("number", "Song position " + DataManager.getCurrentSongPosition().toString());

        MediaPlayer player = DataManager.getMediaPlayer();
        if (player != null) {
            if (player.isPlaying()) {
                player.pause();
                player.stop();
                player.reset();
                player.release();
            }
        }

        player = new MediaPlayer();
        DataManager.setMediaPlayer(player);

        String audio_data = DataManager.getAudioTrackByPosition(position).data;

        try {
            player.setDataSource(audio_data);
            player.prepare();
            player.start();
        } catch (IOException e) {
            Log.i("number", "playing: " + e.getMessage());
        }
    }

    public static List<AudioTrack> getAudioTracks() {
        return DataManager.getAudioTracks();
    }


}
