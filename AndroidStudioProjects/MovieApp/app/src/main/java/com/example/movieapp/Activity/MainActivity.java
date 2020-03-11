package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.movieapp.R;
import com.example.movieapp.model.MovieResponse;
import com.example.movieapp.rest.MovieApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String username;
    private String password;

    private MovieApi movieApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createMovieApi();

        movieApi.getTopRatedMovies("anApiKey").enqueue(movieResponseCallback);
    }

    private void createMovieApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();

                        Request newRequest = originalRequest.newBuilder()
                                .addHeader("Authentication", Credentials.basic(username, password))
                                .build();

                        return chain.proceed(newRequest);
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(MovieApi.BASE_URL)
                .build();

        movieApi = retrofit.create(MovieApi.class);
    }

    Callback<MovieResponse> movieResponseCallback = new Callback<MovieResponse>() {
        @Override
        public void onResponse(Call<MovieResponse> call, retrofit2.Response<MovieResponse> response) {
            if (response.isSuccessful()) {
                MovieResponse movieResponse = response.body();
            } else {
                Log.i("error", response.errorBody().toString());
            }
        }

        @Override
        public void onFailure(Call<MovieResponse> call, Throwable t) {
            t.printStackTrace();
        }
    };


}
