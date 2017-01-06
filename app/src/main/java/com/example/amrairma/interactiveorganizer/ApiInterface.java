package com.example.amrairma.interactiveorganizer;

import com.example.amrairma.interactiveorganizer.Models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("forecast.json")
    Call<Weather> getWeather(@Query("key") String apiKey, @Query("q") String query, @Query("days") int days);

}
