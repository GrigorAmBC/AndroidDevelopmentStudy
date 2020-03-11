package com.example.unsplash;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UnsplashApi {

    public static final String ACCESS_KEY = "ef92c50c24e01de0ff09f8" +
            "669c4140f2530c29e4458ddc0ee722bcbf9c90107e";

    public final static String SECRET_KEY = "196694a365db6ec51da1bf" +
            "44ce821ec448326c2b0fa4c2f1ec84da156dd032ec";

    public static final String BASE_URL = "https://api.unsplash.com/";

    @GET("?key={}")
    Call<List<ImageData>> getPhotos(@Query("q") String queryItem);


}
