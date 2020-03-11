/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.example.instagramclone;
//TODO:check this out
//import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;
import java.util.ResourceBundle;


public class MainActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {
    Boolean signupModeActive = false;
    Button actionButton;
    TextView textView;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionButton = findViewById(R.id.actionButton);
        textView = findViewById(R.id.switchTextView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signupModeActive) {
                    textView.setText(R.string.or_signup);
                    actionButton.setText(R.string.login);
                    signupModeActive = false;
                } else {
                    textView.setText(R.string.or_login);
                    actionButton.setText(R.string.signup);
                    signupModeActive = true;
                }
            }
        });
        ConstraintLayout constraintLayout = findViewById(R.id.backgroundConstraint);
        constraintLayout.setOnClickListener(this);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
        passwordEditText = findViewById(R.id.passwordEditText);

        passwordEditText.setOnKeyListener(this);


//        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }



    public void actionButtonClicked(View v) {
        EditText usernameEditText = findViewById(R.id.usernameEditText);

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (password.length() == 0 || username.length() == 0) {
            Toast.makeText(this,
                    "Username/password are required", Toast.LENGTH_SHORT).show();
        }


        if (signupModeActive) {
            //sign up
            Toast.makeText(this, "SIGNINGUP", Toast.LENGTH_SHORT).show();
            //TODO: delete down
            getToUserListActivity();
//            ParseUser user = new ParseUser();
//            user.setUsername(username);
//            user.setPassword(password);
//            user.signUpInBackground(new SignUpCallback() {
//                @Override
//                public void done(ParseException e) {
//                    if (e != null) {
//                        Log.i("Signup", e.getMessage());
//                        Toast.makeText(MainActivity.this, e.getMessage(),
//                                Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        Toast.makeText(MainActivity.this,
//                                "You're signed up", Toast.LENGTH_SHORT).show();
//                        getToUserListActivity();
//                    }
//                }
//            });
        } else {
            //log in
            Toast.makeText(this, "Logging in", Toast.LENGTH_LONG).show();
            //TODO: delete down
            getToUserListActivity();
            /*ParseUser.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (e == null && user != null) {
                        Toast.makeText(MainActivity.this, "You're logged in",
                                Toast.LENGTH_SHORT).show();
                        getToUserListActivity();
                    }
                    else {
                        Toast.makeText(MainActivity.this, e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }

                }
            });*/
        }

    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                actionButtonClicked(view);
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException e) {

        }
    }

    private void getToUserListActivity() {
        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);


    }
}