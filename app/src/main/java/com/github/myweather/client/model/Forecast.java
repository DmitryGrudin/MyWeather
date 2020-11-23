package com.github.myweather.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Forecast {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("date")
    @Expose
    private Long date;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("high")
    @Expose
    private Integer high;
    @SerializedName("low")
    @Expose
    private Integer low;
    @SerializedName("text")
    @Expose
    private String text;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @NotNull
    @Override
    public String toString() {
        return "Forecast{" +
                "code=" + code +
                ", date=" + date +
                ", day='" + day + '\'' +
                ", high=" + high +
                ", low=" + low +
                ", text='" + text + '\'' +
                '}';
    }
}