package com.github.myweather.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.github.myweather.R;
import com.github.myweather.client.YahooWeatherClient;
import com.github.myweather.client.model.Astronomy;
import com.github.myweather.client.model.Atmosphere;
import com.github.myweather.client.model.Condition;
import com.github.myweather.client.model.CurrentObservation;
import com.github.myweather.client.model.Forecast;
import com.github.myweather.client.model.Location;
import com.github.myweather.client.model.Weather;
import com.github.myweather.client.model.Wind;
import com.github.myweather.data.CurrentObservationViewModel;
import com.github.myweather.data.ForecastsViewModel;
import com.github.myweather.databinding.ActivityWeatherBinding;
import com.github.myweather.fragment.CurrentObservationFragment;
import com.github.myweather.fragment.ForecastsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class WeatherActivity extends AppCompatActivity {

    private static final YahooWeatherClient client = new YahooWeatherClient();
    private final Fragment currentObservationFragment = CurrentObservationFragment.newInstance();
    private final Fragment forecastsFragment = ForecastsFragment.newInstance();

    private CurrentObservationViewModel currentObservationViewModel;
    private ForecastsViewModel forecastsViewModel;

    private TextView locationCity;
    private TextView locationRegionCountry;
    private TextView temperature;
    private TextView condition;
    private TextView windchill;
    private TextView dateUpdate;
    private ImageView conditionImg;

    private String location;
    private Weather weather;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWeatherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);

        locationCity = findViewById(R.id.location_city);
        locationRegionCountry = findViewById(R.id.location_region_country);
        temperature = findViewById(R.id.temperature);
        condition = findViewById(R.id.condition);
        windchill = findViewById(R.id.windchill);
        dateUpdate = findViewById(R.id.date_update);
        conditionImg = findViewById(R.id.condition_img);
        BottomNavigationView navFragment = findViewById(R.id.navigation);

        currentObservationViewModel = new ViewModelProvider(this)
                .get(CurrentObservationViewModel.class);
        forecastsViewModel = new ViewModelProvider(this)
                .get(ForecastsViewModel.class);

        navFragment.setOnNavigationItemSelectedListener(item -> {
            FragmentManager fm = getSupportFragmentManager();
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.details:
                    fragment = currentObservationFragment;
                    break;

                case R.id.forecasts:
                    fragment = forecastsFragment;
                    break;
            }
            if (fragment != null) {
                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment).commit();
            }
            return true;
        });
        navFragment.setSelectedItemId(R.id.details);

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
                            setWeatherLocation(weather.getLocation());
                            setWeatherCurrentObservation(weather.getCurrentObservation());
                            setForecasts(weather.getForecasts());
                        }
                    }

                    @Override
                    public void onFailure(String error) {
                        Toast.makeText(WeatherActivity.this, error, Toast.LENGTH_SHORT).show();
                        Log.w("Weather", error);
                    }
                });
    }

    private void setWeatherLocation(Location weatherLocation) {
        boolean locationCityValid = false;
        boolean locationRegionCountryValid = false;

        if (weatherLocation != null) {
            if (weatherLocation.getCity() != null) {
                locationCity.setText(weatherLocation.getCity());
                locationCityValid = true;
            }

            if ((weatherLocation.getRegion() != null) && (weatherLocation.getCity() != null)) {
                locationRegionCountry.setText(
                        getString(R.string.text_location_region_country,
                                weatherLocation.getRegion(),
                                weatherLocation.getCity()));
                locationRegionCountryValid = true;
            }
        }

        if (!locationCityValid) {
            locationCity.setText("");
        }
        if (!locationRegionCountryValid) {
            locationRegionCountry.setText("");
        }
    }

    private void setWeatherCurrentObservation(CurrentObservation currentObservation) {
        boolean temperatureValid = false;
        boolean conditionValid = false;
        boolean windchillValid = false;
        boolean windDirectionValid = false;
        boolean windSpeedValid = false;

        if (currentObservation != null) {
            Condition weatherCondition = currentObservation.getCondition();
            if (weatherCondition != null) {
                if (weatherCondition.getTemperature() != null) {
                    temperature.setText(
                            getString(R.string.text_temperature,
                                    weatherCondition.getTemperature()));
                    temperatureValid = true;
                }

                if (weatherCondition.getText() != null) {
                    condition.setText(weatherCondition.getText());
                    conditionValid = true;
                }

                if (weatherCondition.getCode() != null) {
                    Glide.with(this)
                            .load("http://l.yimg.com/a/i/us/we/52/" + weatherCondition.getCode() + ".gif")
                            .into(conditionImg);
                }
            }
            Wind wind = currentObservation.getWind();
            if (wind != null) {
                if (wind.getChill() != null){
                    windchill.setText(getString(R.string.text_windchill,
                            wind.getChill()));
                    windchillValid = true;
                }
                if (wind.getDirection() != null) {
                    currentObservationViewModel.setWindDirection(wind.getDirection());
                    windDirectionValid = true;
                }
                if (wind.getSpeed() != null) {
                    currentObservationViewModel.setWindSpeed(wind.getSpeed());
                    windSpeedValid = true;
                }
            }
            Atmosphere atmosphere = currentObservation.getAtmosphere();
            if (atmosphere != null) {
                if (atmosphere.getHumidity() != null) {
                    currentObservationViewModel.setAtmosphereHumidity(atmosphere.getHumidity());
                }
                if (atmosphere.getVisibility() != null) {
                    currentObservationViewModel.setAtmosphereVisibility(atmosphere.getVisibility());
                }
                if (atmosphere.getPressure() != null) {
                    currentObservationViewModel.setAtmospherePressure(atmosphere.getPressure());
                }
            }
            Astronomy astronomy = currentObservation.getAstronomy();
            if (astronomy != null) {
                if (astronomy.getSunrise() != null) {
                    currentObservationViewModel.setAstronomySunrise(astronomy.getSunrise());
                }
                if (astronomy.getSunset() != null) {
                    currentObservationViewModel.setAstronomySunset(astronomy.getSunset());
                }
            }

            if (currentObservation.getPubDate() != null) {
                DateFormat df = DateFormat.getDateTimeInstance(
                        DateFormat.SHORT,
                        DateFormat.SHORT);
                df.setTimeZone(TimeZone.getDefault());
                dateUpdate.setText(getString(R.string.text_date_update,
                        df.format(new Date(currentObservation.getPubDate() * 1000L))));
            }
        }

        if (!temperatureValid) {
            temperature.setText("");
        }
        if (!conditionValid) {
            condition.setText("");
        }
        if (!windchillValid) {
            windchill.setText("");
        }
        if (!windDirectionValid) {
            currentObservationViewModel.setWindDirection(null);
        }
        if (!windSpeedValid) {
            currentObservationViewModel.setWindSpeed(null);
        }
    }

    void setForecasts(List<Forecast> forecasts) {
        forecastsViewModel.setForecasts(forecasts);
    }
}