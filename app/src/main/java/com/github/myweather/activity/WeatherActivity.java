package com.github.myweather.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.github.myweather.R;
import com.github.myweather.client.YahooWeatherClient;
import com.github.myweather.client.model.Condition;
import com.github.myweather.client.model.CurrentObservation;
import com.github.myweather.client.model.Location;
import com.github.myweather.client.model.Weather;
import com.github.myweather.client.model.Wind;
import com.github.myweather.fragment.CurrentObservationFragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class WeatherActivity extends AppCompatActivity {

    private static final YahooWeatherClient client = new YahooWeatherClient();

    private CurrentObservationFragment.DataViewModel viewModel;

    TextView locationCity;
    TextView locationRegionCountry;
    TextView temperature;
    TextView condition;
    TextView windchill;

    private String location;
    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        locationCity = findViewById(R.id.location_city);
        locationRegionCountry = findViewById(R.id.location_region_country);
        temperature = findViewById(R.id.temperature);
        condition = findViewById(R.id.condition);
        windchill = findViewById(R.id.windchill);

        viewModel = new ViewModelProvider(this)
                .get(CurrentObservationFragment.DataViewModel.class);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new CurrentObservationFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        location = getIntent().getStringExtra("location");

        updateWeather();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    private void updateWeather() {
        client.getWeather(location,
                new YahooWeatherClient.WeatherCallback() {
                    @Override
                    public void onResponse(Weather response) {
                        weather = response;
                        if (weather != null) {
                            Location weatherLocation = weather.getLocation();
                            if (weatherLocation != null) {
                                if (weatherLocation.getCity() != null) {
                                    locationCity.setText(weatherLocation.getCity());
                                } else {
                                    locationCity.setText("");
                                }

                                if ((weatherLocation.getRegion() != null) && (weatherLocation.getCity() != null)) {
                                    locationRegionCountry.setText(
                                            getString(R.string.text_location_region_country,
                                                    weatherLocation.getRegion(),
                                                    weatherLocation.getCity()));
                                } else {
                                    locationRegionCountry.setText("");
                                }
                            }

                            CurrentObservation currentObservation = weather.getCurrentObservation();
                            if (currentObservation != null) {
                                Condition weatherCondition = currentObservation.getCondition();
                                if (weatherCondition != null) {
                                    if (weatherCondition.getTemperature() != null) {
                                        temperature.setText(
                                                getString(R.string.text_temperature,
                                                        weatherCondition.getTemperature()));
                                    } else {
                                        temperature.setText("");
                                    }

                                    if (weatherCondition.getText() != null) {
                                        condition.setText(weatherCondition.getText());
                                    } else {
                                        condition.setText("");
                                    }
                                }
                                Wind wind = currentObservation.getWind();
                                if (wind != null) {
                                    if (wind.getChill() != null){
                                        windchill.setText(getString(R.string.text_windchill,
                                                wind.getChill()));
                                    } else {
                                        windchill.setText("");
                                    }

                                    if (wind.getDirection() != null) {
                                        viewModel.setWindDirection(wind.getDirection());
                                    } else {
                                        viewModel.setWindDirection(null);
                                    }

                                    if (wind.getSpeed() != null) {
                                        viewModel.setWindSpeed(wind.getSpeed());
                                    } else {
                                        viewModel.setWindSpeed(null);
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(String error) {
                        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
                        Log.w("Weather", error);
                    }
                });
    }
}