package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    UserListAdapter adapter;
    //List<ParseUser> mUsers;
    List<String> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

//        ParseQuery<ParseUser> query  = ParseUser.getQuery();
//        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
//        query.addAscendingOrder("username");
//
//        query.findInBackground(new FindCallback<ParseUser>() {
//            @Override
//            public void done(List<ParseUser> objects, ParseException e) {
//                if (e == null) {
//                    if (objects.size() > 0)
//                        mUsers = objects;
//                } else {
//                    e.printStackTrace();
//                }
//            }
//        });

        mUsers = new ArrayList<>();
        mUsers.add("grigor");
        mUsers.add("nick");
        mUsers.add("Gor");
        mUsers.add("Tigr");
        mUsers.add("grigor");
        mUsers.add("nick");
        mUsers.add("Gor");
        mUsers.add("Tigr");
        mUsers.add("grigor");
        mUsers.add("nick");
        mUsers.add("Gor");
        mUsers.add("Tigr");
        mUsers.add("grigor");
        mUsers.add("nick");
        mUsers.add("Gor");
        mUsers.add("Tigr");

        adapter = new UserListAdapter(mUsers, this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.userListRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    protected void onActivityResult(int request_code, int result_code, Intent data) {
        if(request_code == 1 && result_code == RESULT_OK && data != null) {

            Uri selectedImage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media
                        .getBitmap(this.getContentResolver(), selectedImage);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                byte[] byteArray = stream.toByteArray();

                ParseFile file = new ParseFile("image.png", byteArray);

//                ParseObject object = new ParseObject("Image");
//                object.put("image", file);
//                object.put("username", file);
//
//                object.saveInBackground(new SaveCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        if (e == null) {
//                            Toast.makeText(UserListActivity.this,
//                                    "Image shared!",
//                                    Toast.LENGTH_SHORT)
//                                    .show();
//                        } else {
//                            Toast.makeText(UserListActivity.this,
//                                    "Image couldn't be shared! Try later.",
//                                    Toast.LENGTH_SHORT)
//                                    .show();
//                        }
//                    }
//                });
//

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPhoto();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_import_option) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                getPhoto();
            }
        }

        if (item.getItemId() == R.id.logout) {
            Toast.makeText(UserListActivity.this,
                    "You're logged out!",
                    Toast.LENGTH_SHORT)
                    .show();
//            ParseUser.logOutInBackground(new LogOutCallback() {
//                @Override
//                public void done(ParseException e) {
//                    if (e == null) {
//                        Toast.makeText(UserListActivity.this,
//                                "You're logged out!",
//                                Toast.LENGTH_SHORT)
//                                .show();
//                    } else {
//                        Toast.makeText(UserListActivity.this,
//                                "Try again logging out later",
//                                Toast.LENGTH_SHORT)
//                                .show();
//                        e.printStackTrace();
//                    }
//                }
//            });

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            // TODO: incorrect activity transition
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

