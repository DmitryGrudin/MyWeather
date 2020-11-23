package com.github.myweather.client.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Weather implements Parcelable {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("current_observation")
    @Expose
    private CurrentObservation currentObservation;
    @SerializedName("forecasts")
    @Expose
    private List<Forecast> forecast = null;

    protected Weather(Parcel in) {
        location = in.readParcelable(Location.class.getClassLoader());
        currentObservation = in.readParcelable(CurrentObservation.class.getClassLoader());
        forecast = in.createTypedArrayList(Forecast.CREATOR);
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(location, i);
        parcel.writeParcelable(currentObservation, i);
        parcel.writeTypedList(forecast);
    }
}