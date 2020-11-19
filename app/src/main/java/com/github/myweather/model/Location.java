package com.github.myweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Location {

    @SerializedName("woeid")
    @Expose
    private Integer woeid;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("long")
    @Expose
    private Double lon;
    @SerializedName("timezone_id")
    @Expose
    private String timezoneId;

    public Integer getWoeid() {
        return woeid;
    }

    public void setWoeid(Integer woeid) {
        this.woeid = woeid;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @NotNull
    @Override
    public String toString() {
        return "woeid: "          + woeid +
                ", city: "        + city +
                ", region: "      + region +
                ", country: "     + country +
                ", lat: "         + lat +
                ", long: "        + lon +
                ", timezone_id: " + timezoneId;
    }

}