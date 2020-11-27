package com.github.myweather.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.myweather.client.model.Forecast;

import java.util.List;

public class ForecastsViewModel extends ViewModel {
    private final MutableLiveData<List<Forecast>> forecasts = new MutableLiveData<>();

    public LiveData<List<Forecast>> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts.setValue(forecasts);
    }
}
