package com.github.myweather.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.myweather.R;
import com.github.myweather.model.Weather;
import com.github.myweather.retrofit.ServiceGenerator;
import com.github.myweather.services.WeatherService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText txtResult;
    EditText editTxtLocation;
    Button butGetWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);
        editTxtLocation = findViewById(R.id.editTxtLocation);
        butGetWeather = findViewById(R.id.butGetWeather);
        butGetWeather.setOnClickListener(v -> ServiceGenerator.createService(
                WeatherService.class).getWeather(editTxtLocation.getText().toString()).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NotNull Call<Weather> call, @NotNull Response<Weather> response) {
                if (response.isSuccessful()) {
                    Log.i("Weather", response.body().toString());
                    txtResult.setText(response.body().toString());
                } else {
                    switch (response.code()) {
                        case 404:

                            break;
                        case 500:

                            break;
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<Weather> call, @NotNull Throwable t) {
                txtResult.setText(t.getMessage());
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        }));
    }
}