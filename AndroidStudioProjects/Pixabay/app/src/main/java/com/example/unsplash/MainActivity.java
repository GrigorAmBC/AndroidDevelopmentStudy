package com.example.unsplash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    ImageView mImageView;
    UnsplashApi mPixabayApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.imageView);
        createPixabayApi();
        Picasso.get().load("")
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE);

        mPixabayApi.getPhotos("");
    }

    private void createPixabayApi() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .registerTypeAdapter(ImageData.class, new ImageDataDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UnsplashApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mPixabayApi = retrofit.create(UnsplashApi.class);
    }
}
