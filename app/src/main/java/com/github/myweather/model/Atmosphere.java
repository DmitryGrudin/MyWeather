package com.github.myweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Atmosphere {

    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("pressure")
    @Expose
    private Float pressure;
    @SerializedName("rising")
    @Expose
    private Integer rising;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public Integer getRising() {
        return rising;
    }

    public void setRising(Integer rising) {
        this.rising = rising;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    @NotNull
    @Override
    public String toString() {
        return "Atmosphere{" +
                "humidity=" + humidity +
                ", pressure=" + pressure +
                ", rising=" + rising +
                ", visibility=" + visibility +
                '}';
    }
}