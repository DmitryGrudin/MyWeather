package com.github.myweather.client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.github.myweather.client.model.Weather;
import com.github.myweather.client.retrofit.ServiceGenerator;
import com.github.myweather.client.services.WeatherService;

import org.jetbrains.annotations.NotNull;

public class YahooWeatherClient {
    private static final WeatherService service = ServiceGenerator.createService(WeatherService.class);

    public interface WeatherCallback {
        void onResponse(Weather response);
        void onFailure(String error);
    }

    public void getWeather(@NotNull String location, @NotNull WeatherCallback callback) {
        service.getWeather(location).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NotNull Call<Weather> call, @NotNull Response<Weather> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(response.body());
                } else {
                    callback.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(@NotNull Call<Weather> call, @NotNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
