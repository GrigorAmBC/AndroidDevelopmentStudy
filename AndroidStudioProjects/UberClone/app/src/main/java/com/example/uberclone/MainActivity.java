package com.example.uberclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    boolean isDriverModeActive;
    public void getStarted(View v) {
        if (isDriverModeActive) {

        } else {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Switch RiderDriverSwitch = findViewById(R.id.switch1);
        isDriverModeActive = RiderDriverSwitch.isChecked();
    }
}
