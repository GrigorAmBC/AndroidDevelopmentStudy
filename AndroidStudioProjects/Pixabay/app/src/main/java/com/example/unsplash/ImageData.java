package com.example.unsplash;

import com.google.gson.annotations.SerializedName;

public class ImageData {

    public String height;
    public String width;

    @SerializedName("raw")
    public String rawImageUrl;
    @SerializedName("small")
    public String smallImageUrl;

   /* @SerializedName("likes")
    public String likes;

    public String largeImageURL;
    public String views;
    public String user;
    public String imageURL;*/
}
