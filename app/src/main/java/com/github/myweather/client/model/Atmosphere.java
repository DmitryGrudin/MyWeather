package com.github.myweather.client.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Atmosphere implements Parcelable {

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

    protected Atmosphere(Parcel in) {
        if (in.readByte() == 0) {
            humidity = null;
        } else {
            humidity = in.readInt();
        }
        if (in.readByte() == 0) {
            pressure = null;
        } else {
            pressure = in.readFloat();
        }
        if (in.readByte() == 0) {
            rising = null;
        } else {
            rising = in.readInt();
        }
        if (in.readByte() == 0) {
            visibility = null;
        } else {
            visibility = in.readInt();
        }
    }

    public static final Creator<Atmosphere> CREATOR = new Creator<Atmosphere>() {
        @Override
        public Atmosphere createFromParcel(Parcel in) {
            return new Atmosphere(in);
        }

        @Override
        public Atmosphere[] newArray(int size) {
            return new Atmosphere[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (humidity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(humidity);
        }
        if (pressure == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(pressure);
        }
        if (rising == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(rising);
        }
        if (visibility == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(visibility);
        }
    }
}