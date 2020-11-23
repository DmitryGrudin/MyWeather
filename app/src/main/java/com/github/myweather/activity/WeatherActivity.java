package com.github.myweather.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.github.myweather.R;
import com.github.myweather.client.model.Weather;

import android.os.Bundle;

public class WeatherActivity extends AppCompatActivity {

    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weather = (Weather)getIntent().getSerializableExtra("weather");
    }
}