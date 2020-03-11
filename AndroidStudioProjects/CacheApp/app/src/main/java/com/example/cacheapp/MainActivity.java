package com.example.cacheapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.editText);
    }

    public void onClick(View v) {
        String text = mEditText.getText().toString();
        if (text.isEmpty()) {
            Toast.makeText(this, "TYPE IT!!", Toast.LENGTH_SHORT).show();
        } else {
            cache(text);
        }
    }

    private void cache(String text) {

    }

    private void enableHttpResponseCache() {

    }
}
