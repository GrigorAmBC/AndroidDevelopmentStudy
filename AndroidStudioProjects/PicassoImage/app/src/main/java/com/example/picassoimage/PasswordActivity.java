package com.example.picassoimage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {

    private EditText passwordEditText;
    private int i = 0;
    public static int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_password);
        REQUEST_CODE = getIntent().getIntExtra("request_code", 0);

        passwordEditText = findViewById(R.id.passwordEditText);
    }

    @Override
    protected void onStart() {
        super.onStart();
        passwordEditText.setText("");
    }

    public void onHideStatusBar(View v) {
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void onClick(View view) {
        if (passwordEditText.getText().toString().equals("fuck25")) {
            if (DataManager.isFirstOpened()) {
                Intent intent = new Intent(this, MainActivity.class);
                DataManager.isFirstOpened(false);
                markActivities();
                startActivity(intent);
            } else {
                markActivities();
                finish();
            }
        } else {
            Toast.makeText(this, "WRONG!! Ask Grigor", Toast.LENGTH_SHORT).show();
        }
    }

    private void markActivities() {
        MainActivity.wasClosed = false;
        ImageActivity.wasClosed = false;
    }

    @Override
    public void onBackPressed() {

    }
}
