package com.github.myweather.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Condition {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("date")
    @Expose
    private Long date;
    @SerializedName("temperature")
    @Expose
    private Integer temperature;
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

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
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
        return "Condition{" +
                "code=" + code +
                ", date=" + date +
                ", temperature=" + temperature +
                ", text='" + text + '\'' +
                '}';
    }
}