package com.example.dresswardrobe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "http://192.168.18.157/DressWardrobeAPI/api/";
    @GET("imagefile/getImage")
    Call<List<Example>> getImage();


}
