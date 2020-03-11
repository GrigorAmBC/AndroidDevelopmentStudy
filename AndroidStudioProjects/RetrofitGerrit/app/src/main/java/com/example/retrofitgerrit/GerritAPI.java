package com.example.retrofitgerrit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface GerritAPI  {

    @GET("changes/")
    Call<List<Change>> loadChanges(@Query("q") String status);

    @GET("user")
    Call<UserDetails> getUserDetails(@Header("Authorization") String credentials);
}







