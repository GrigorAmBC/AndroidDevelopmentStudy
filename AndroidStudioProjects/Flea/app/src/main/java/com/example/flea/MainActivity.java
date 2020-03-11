package com.example.flea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

  private RecyclerView mRecyclerView;
  private RecyclerView.LayoutManager mLayoutManager;
  private Adapter mAdapter;
  private TextView mSongTitleTextView;
  private Button mPauseButton;
  private Integer mReadRequestCode = 105;
  private Integer mWriteRequestCode = 106;
  private Integer mRecordRequestCode = 107;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mSongTitleTextView = findViewById(R.id.textView);
    //TODO:NOT WORKING here
    MainPresenter.setup(getApplicationContext());

    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      requestPermissions(new String[]{
              Manifest.permission.WRITE_EXTERNAL_STORAGE}, mWriteRequestCode);
    }

    if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
      requestPermissions(new String[]{
              Manifest.permission.RECORD_AUDIO}, mRecordRequestCode);
    }

    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, mReadRequestCode);
    } else {
      showAudioTrackList();
    }

    mPauseButton = findViewById(R.id.pauseButton);
    mPauseButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (MainPresenter.isSongPlaying()) {
          MainPresenter.onPauseSong();
          mPauseButton.setText("Resume");
        } else {
          MainPresenter.onResumeSong();
          mPauseButton.setText("Pause");
        }
      }
    });

    final Button mNextButton = findViewById(R.id.nextButton);
    mNextButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        MainPresenter.onNextSong();
        mSongTitleTextView.setText(DataManager.getCurrentAudioTrack().title);
        mPauseButton.setText("Pause");
      }
    });
  }

  public void onOpenSongActivity(View v) {
    showSongControlActivity();
  }

  private void showSongControlActivity() {
    Intent intent = new Intent(this, SongControlActivity.class);
    startActivity(intent);
  }

  public void playSong(View view, Integer position) {
    MainPresenter.playSong(position);

    mSongTitleTextView.setText(MainPresenter.getCurrentSongTitle());
  }

  private void showAudioTrackList() {
    mRecyclerView = findViewById(R.id.audioListRecyclerView);
    mAdapter = new Adapter(this, this);
    mLayoutManager = new LinearLayoutManager(this);

    mRecyclerView.setAdapter(mAdapter);
    mRecyclerView.setLayoutManager(mLayoutManager);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.audio_track_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.recordAudioItem) {
      if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        requestPermissions(new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, mWriteRequestCode);

      }

      if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
        requestPermissions(new String[]{
                Manifest.permission.RECORD_AUDIO}, mRecordRequestCode);

      }

      if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        Intent intent = new Intent(this, AudioRecordActivity.class);
        startActivity(intent);
      }
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if (requestCode == mReadRequestCode) {
      if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
        requestPermissions(new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE}, mReadRequestCode);
      }
    }

    if (requestCode == mWriteRequestCode) {
      if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
        requestPermissions(new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE}, mWriteRequestCode);
      }
    }

    if (requestCode == mRecordRequestCode) {
      if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
        requestPermissions(new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE}, mRecordRequestCode);
      }
    }
  }
}


