package com.example.myapplication.utils.networking;


import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/")
    Single<Integer> getResponse(@Query("expression") String expression);
}
