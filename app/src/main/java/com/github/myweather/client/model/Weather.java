package com.github.myweather.client.model;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Weather {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("current_observation")
    @Expose
    private CurrentObservation currentObservation;
    @SerializedName("forecasts")
    @Expose
    private List<Forecast> forecast = null;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CurrentObservation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(CurrentObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    @NotNull
    @Override
    public String toString() {
        return "--- Location ---\n" + location + "\n" +
               "--- Current observation ---\n" + currentObservation + "\n" +
               "--- Forecasts ---\n" + forecast;
    }
}