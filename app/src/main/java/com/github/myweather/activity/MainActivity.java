package com.github.myweather.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.myweather.R;
import com.github.myweather.client.YahooWeatherClient;
import com.github.myweather.client.model.Weather;

public class MainActivity extends AppCompatActivity {

    YahooWeatherClient client = new YahooWeatherClient();

    EditText editTxtLocation;
    Button butGetWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTxtLocation = findViewById(R.id.editTxtLocation);
        butGetWeather = findViewById(R.id.butGetWeather);
        butGetWeather.setOnClickListener(v -> client.getWeather(editTxtLocation.getText().toString(),
                new YahooWeatherClient.WeatherCallback() {
                    @Override
                    public void onResponse(Weather response) {
                        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                        intent.putExtra("weather", response);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(String error) {
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}