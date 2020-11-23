package com.github.myweather.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.github.myweather.R;

public class MainActivity extends AppCompatActivity {

    EditText editTxtLocation;
    Button butGetWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTxtLocation = findViewById(R.id.editTxtLocation);
        butGetWeather = findViewById(R.id.butGetWeather);
        butGetWeather.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
            intent.putExtra("location", editTxtLocation.getText().toString());
            startActivity(intent);
        });
    }
}