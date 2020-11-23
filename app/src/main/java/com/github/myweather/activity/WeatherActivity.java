package com.github.myweather.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.github.myweather.R;
import com.github.myweather.client.YahooWeatherClient;
import com.github.myweather.client.model.Weather;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class WeatherActivity extends AppCompatActivity {

    private static final YahooWeatherClient client = new YahooWeatherClient();

    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        String location = getIntent().getStringExtra("location");

        client.getWeather(location,
                new YahooWeatherClient.WeatherCallback() {
                    @Override
                    public void onResponse(Weather response) {
                        weather = response;
                    }

                    @Override
                    public void onFailure(String error) {
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                        Log.w("Weather", error);
                    }
                });
    }
}