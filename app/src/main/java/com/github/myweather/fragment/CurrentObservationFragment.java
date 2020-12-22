package com.github.myweather.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.myweather.R;
import com.github.myweather.data.CurrentObservationViewModel;

import org.jetbrains.annotations.NotNull;

public class CurrentObservationFragment extends Fragment {

    private TextView windDirection;
    private TextView windSpeed;
    private TextView atmosphereHumidity;
    private TextView atmospherePressure;
    private TextView atmosphereVisibility;
    private TextView astronomySunrise;
    private TextView astronomySunset;

    public CurrentObservationFragment() {

    }

    public static CurrentObservationFragment newInstance() {
        return new CurrentObservationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_current_observation, container, false);
        windDirection = v.findViewById(R.id.direction);
        windSpeed = v.findViewById(R.id.speed);
        atmosphereHumidity = v.findViewById(R.id.humidity);
        atmospherePressure = v.findViewById(R.id.pressure);
        atmosphereVisibility = v.findViewById(R.id.visibility);
        astronomySunrise = v.findViewById(R.id.sunrise);
        astronomySunset = v.findViewById(R.id.sunset);
        return v;
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        CurrentObservationViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(CurrentObservationViewModel.class);

        viewModel.getWindDirection().observe(getViewLifecycleOwner(), item -> {
            if (item != null) {
                windDirection.setText(getString(R.string.text_wind_direction, item));
            } else {
                windDirection.setText("");
            }
        });
        viewModel.getWindSpeed().observe(getViewLifecycleOwner(), item -> {
            if (item != null) {
                windSpeed.setText(getString(R.string.text_wind_speed, item));
            } else {
                windSpeed.setText("");
            }
        });
        viewModel.getAtmosphereHumidity().observe(getViewLifecycleOwner(), item -> {
            if (item != null) {
                atmosphereHumidity.setText(getString(R.string.text_humidity, item));
            } else {
                atmosphereHumidity.setText("");
            }
        });
        viewModel.getAtmospherePressure().observe(getViewLifecycleOwner(), item -> {
            if (item != null) {
                atmospherePressure.setText(getString(R.string.text_pressure, item));
            } else {
                atmospherePressure.setText("");
            }
        });
        viewModel.getAtmosphereVisibility().observe(getViewLifecycleOwner(), item -> {
            if (item != null) {
                atmosphereVisibility.setText(getString(R.string.text_visibility, item));
            } else {
                atmosphereVisibility.setText("");
            }
        });
        viewModel.getAstronomySunrise().observe(getViewLifecycleOwner(), item -> {
            if (item != null) {
                astronomySunrise.setText(getString(R.string.text_sunrise, item));
            } else {
                astronomySunrise.setText("");
            }
        });
        viewModel.getAstronomySunset().observe(getViewLifecycleOwner(), item -> {
            if (item != null) {
                astronomySunset.setText(getString(R.string.text_sunset, item));
            } else {
                astronomySunset.setText("");
            }
        });
    }
}