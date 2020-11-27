package com.github.myweather.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CurrentObservationViewModel extends ViewModel {
    private final MutableLiveData<Integer> windDirection = new MutableLiveData<>();
    private final MutableLiveData<Float> windSpeed = new MutableLiveData<>();
    private final MutableLiveData<Integer> atmosphereHumidity = new MutableLiveData<>();
    private final MutableLiveData<Float> atmospherePressure = new MutableLiveData<>();
    private final MutableLiveData<Float> atmosphereVisibility = new MutableLiveData<>();
    private final MutableLiveData<String> astronomySunrise = new MutableLiveData<>();
    private final MutableLiveData<String> astronomySunset = new MutableLiveData<>();

    public LiveData<Integer> getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Integer direction) {
        this.windDirection.setValue(direction);
    }

    public LiveData<Float> getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Float speed) {
        this.windSpeed.setValue(speed);
    }

    public LiveData<Integer> getAtmosphereHumidity() {
        return atmosphereHumidity;
    }

    public void setAtmosphereHumidity(Integer humidity) {
        this.atmosphereHumidity.setValue(humidity);
    }

    public LiveData<Float> getAtmospherePressure() {
        return atmospherePressure;
    }

    public void setAtmospherePressure(Float pressure) {
        this.atmospherePressure.setValue(pressure);
    }

    public LiveData<Float> getAtmosphereVisibility() {
        return atmosphereVisibility;
    }

    public void setAtmosphereVisibility(Float atmosphereVisibility) {
        this.atmosphereVisibility.setValue(atmosphereVisibility);
    }

    public LiveData<String> getAstronomySunrise() {
        return astronomySunrise;
    }

    public void setAstronomySunrise(String astronomySunrise) {
        this.astronomySunrise.setValue(astronomySunrise);
    }

    public LiveData<String> getAstronomySunset() {
        return astronomySunset;
    }

    public void setAstronomySunset(String astronomySunset) {
        this.astronomySunset.setValue(astronomySunset);
    }
}
