package com.example.flea;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
  private static List<AudioTrack> mAudioTracks;
  private static Integer mCurrentSongPosition;
  private static MediaPlayer mMediaPlayer;

  static {
    mAudioTracks = new ArrayList<>();
    mCurrentSongPosition = -1;
    mMediaPlayer = null;
  }

  public static class AsyncTask extends AsyncTaskLoader {
    private Context context;

    AsyncTask(@NonNull Context context) {
      super(context);
    }

    public void getSongs() {
      loadInBackground();
    }

    @Nullable
    @Override
    public Object loadInBackground() {
      Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

      String[] projection = {
              MediaStore.Audio.Media._ID,
              MediaStore.Audio.Media.ARTIST,
              MediaStore.Audio.Media.TITLE,
              MediaStore.Audio.Media.DATA,
              MediaStore.Audio.Media.DISPLAY_NAME,
      };

      Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

      Log.i("music", "MUSIC exists");
      assert cursor != null;
      while (cursor.moveToNext()) {
        String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
        String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
        String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
        String _ID = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));

        mAudioTracks.add(new AudioTrack(data, displayName, title, _ID));
        Log.i("music", displayName);
      }

      return null;
    }
  }


  public static MediaPlayer getMediaPlayer() {
    return mMediaPlayer;
  }

  public static void setMediaPlayer(MediaPlayer mMediaPlayer) {
    DataManager.mMediaPlayer = mMediaPlayer;
  }

  public static List<AudioTrack> getAudioTracks() {
    return mAudioTracks;
  }

  public static Integer getCurrentSongPosition() {
    return mCurrentSongPosition;
  }

  public static void setAudioTracks(Context context) {
    AsyncTask task = new AsyncTask(context);
    task.getSongs();
  }

  public static void setCurrentSongPosition(Integer mCurrentSongPosition) {
    if (mCurrentSongPosition == -1) {
      DataManager.mCurrentSongPosition = mAudioTracks.size() - 1;
      return;
    }
    if (mCurrentSongPosition == mAudioTracks.size()) {
      DataManager.mCurrentSongPosition = 0;
      return;
    }

    DataManager.mCurrentSongPosition = mCurrentSongPosition;
  }

  public static AudioTrack getCurrentAudioTrack() {
    return getAudioTracks().get(getCurrentSongPosition());
  }

  public static AudioTrack getNextAudioTrack() {
    int position;

    if (getCurrentSongPosition() == mAudioTracks.size() - 1) {
      position = 0;
    } else {
      position = getCurrentSongPosition() + 1;
    }

    return mAudioTracks.get(position);
  }

  public static AudioTrack getPreviousAudioTrack() {
    int position;

    if (getCurrentSongPosition() == 0) {
      position = getAudioTracks().size() - 1;
    } else {
      position = getCurrentSongPosition() - 1;
    }

    return mAudioTracks.get(position);
  }

  public static AudioTrack getAudioTrackByPosition(Integer position) throws IndexOutOfBoundsException {
    //TODO: try change this so that there's no IF statements; just return ...
    if (position > 0 && position < mAudioTracks.size()) {
      return mAudioTracks.get(position);
    } else {
      throw new IndexOutOfBoundsException();
    }
  }
}
