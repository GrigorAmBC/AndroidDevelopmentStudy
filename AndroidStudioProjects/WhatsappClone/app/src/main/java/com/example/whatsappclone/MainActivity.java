package com.example.whatsappclone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    private boolean loginModeActive = true;
    private Button mActionButton;
    private TextView mToggleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Whatsapp login");
        mActionButton = findViewById(R.id.loginSignupButton);
        mToggleTextView = findViewById(R.id.textView);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Hello notification!!");

        EditText tweet = new EditText(this);

        builder.setView(tweet);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

    }

    public void showUserFeed(View v) {
        Intent intent = new Intent(this, HashMapActivity.class);
        startActivity(intent);
    }

    public void toggleLoginSignupMode(View v) {
        if (loginModeActive) {
            mActionButton.setText("Sign up");
            mToggleTextView.setText("or, Log in");
        } else {
            mActionButton.setText("Log in");
            mToggleTextView.setText("or, Sign up");
        }
    }

    public void loginSignupClicked(View v) {
        EditText mUsernameEditText = findViewById(R.id.usernameEditText);
        EditText mPasswordEditText = findViewById(R.id.passwordEditText);

        if (loginModeActive) {
            ParseUser.logInInBackground(mUsernameEditText.getText().toString(),
                    mPasswordEditText.getText().toString());
        } else {
            ParseUser parseUser = new ParseUser();
            parseUser.setPassword(mPasswordEditText.getText().toString());
            parseUser.setUsername(mUsernameEditText.getText().toString());
            parseUser.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(MainActivity.this,
                                "Signup successful",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
