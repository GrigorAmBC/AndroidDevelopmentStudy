package com.example.flea;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.UUID;

public class AudioRecordActivity extends AppCompatActivity {

    private static MediaRecorder mMediaRecorder;
    private static String mPath;
    private static Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);

        mButton = findViewById(R.id.saveButton);

        if (!hasMicrophone()) {
            Log.i("music", "NO MICROPHONE");
        }

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.i("music", "WRITE_OK");
        }

        mPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                 + "/audio_record.3gp";

        mMediaRecorder = new MediaRecorder();
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mMediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mMediaRecorder.setOutputFile(mPath);
        try {
            mMediaRecorder.prepare();
        } catch (IOException e) {
            if (e != null)
                Log.i("music", "ERROR IN 2nd activity");
            Log.i("music", e.getMessage());
        }
        mMediaRecorder.start();
    }

    protected boolean hasMicrophone() {
        PackageManager packageManager = this.getPackageManager();
        return packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE);
    }

    public void onSaveAudio(View v) {
        mMediaRecorder.stop();
        mMediaRecorder.reset();
        mMediaRecorder.release();
        finish();
        mButton.setEnabled(false);
    }
}
