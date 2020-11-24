package com.github.myweather.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.myweather.R;

import org.jetbrains.annotations.NotNull;

public class CurrentObservationFragment extends Fragment {

    public static class DataViewModel extends ViewModel {

        private MutableLiveData<Integer> windDirection;
        private MutableLiveData<Float> windSpeed;

        public LiveData<Integer> getWindDirection() {
            if (windDirection == null) {
                windDirection = new MutableLiveData<Integer>();
            }
            return windDirection;
        }

        public void setWindDirection(Integer direction) {
            this.windDirection.setValue(direction);
        }

        public LiveData<Float> getWindSpeed() {
            if (windSpeed == null) {
                windSpeed = new MutableLiveData<Float>();
            }
            return windSpeed;
        }

        public void setWindSpeed(Float speed) {
            this.windSpeed.setValue(speed);
        }
    }

    TextView windDirection;
    TextView windSpeed;

    public CurrentObservationFragment() {

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
        return v;
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        DataViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(CurrentObservationFragment.DataViewModel.class);

        viewModel.getWindDirection().observe(getViewLifecycleOwner(), item -> {
            windDirection.setText(getString(R.string.text_wind_direction, item));
        });
        viewModel.getWindSpeed().observe(getViewLifecycleOwner(), item -> {
            windSpeed.setText(getString(R.string.text_wind_speed, item));
        });
    }
}