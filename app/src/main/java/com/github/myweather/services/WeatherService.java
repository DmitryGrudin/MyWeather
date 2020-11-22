package com.github.myweather.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import com.github.myweather.model.Weather;

public interface WeatherService {
    @Headers("Content-Type: application/json")
    @GET("forecastrss?format=json")
    Call<Weather> getWeather(@Query("location") String location);

    @Headers("Content-Type: application/json")
    @GET("forecastrss?format=json")
    Call<Weather> getWeather(@Query("lat") Double lat, @Query("lat") Double lon);

    @Headers("Content-Type: application/json")
    @GET("forecastrss?format=json")
    Call<Weather> getWeather(@Query("woeid") Integer woeid);
}
