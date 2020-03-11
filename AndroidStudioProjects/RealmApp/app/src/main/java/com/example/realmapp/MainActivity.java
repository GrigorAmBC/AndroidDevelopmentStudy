package com.example.realmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Realm realm;

    private EditText nameEditText;
    private EditText ageEditText;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setVisibility(View.GONE);

        findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int age = Integer.valueOf(ageEditText.getText().toString());
                String name = nameEditText.getText().toString();

                Dog dog1 = realm.where(Dog.class)
                        .equalTo("name", name)
                        .findFirst();

                if (dog1 != null) {
                    realm.beginTransaction();
                    dog1.setAge(age);
                    realm.commitTransaction();
                    Toast.makeText(MainActivity.this, "Dog's been created earlier!", Toast.LENGTH_SHORT).show();
                } else {
                    realm.beginTransaction();
                    Dog dog = realm.createObject(Dog.class, name);
                    dog.setAge(age);
                    realm.commitTransaction();
                    Toast.makeText(MainActivity.this, "Dog added!", Toast.LENGTH_SHORT).show();
                }
                if (linearLayout.getVisibility() == View.VISIBLE) {
                    showDogList();
                }
            }
        });

        realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        Person person = realm.createObject(Person.class);
        person.setId(4343);
        person.setName("George");
        realm.commitTransaction();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.showDogsMenuItem) {
            showDogList();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();

    }

    private void showDogList() {
        RealmResults<Dog> dogs = realm.where(Dog.class).findAll();

        linearLayout.removeAllViewsInLayout();
        if (!dogs.isEmpty()) {
            linearLayout.setVisibility(View.VISIBLE);
            LayoutInflater inflater = getLayoutInflater();
            for (Dog dog : dogs) {
                View view = inflater.inflate(R.layout.dog_item, null);

                ((TextView) view.findViewById(R.id.ageTextView))
                        .setText(String.valueOf(dog.getAge()));
                ((TextView)view.findViewById(R.id.nameTextView)).setText(dog.getName());
                linearLayout.addView(view);
            }

            //show
        } else {
            Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show();
        }
    }
}
