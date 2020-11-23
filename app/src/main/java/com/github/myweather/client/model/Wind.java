package com.github.myweather.client.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class Wind implements Parcelable {

    @SerializedName("chill")
    @Expose
    private Integer chill;
    @SerializedName("direction")
    @Expose
    private Integer direction;
    @SerializedName("speed")
    @Expose
    private Float speed;

    protected Wind(Parcel in) {
        if (in.readByte() == 0) {
            chill = null;
        } else {
            chill = in.readInt();
        }
        if (in.readByte() == 0) {
            direction = null;
        } else {
            direction = in.readInt();
        }
        if (in.readByte() == 0) {
            speed = null;
        } else {
            speed = in.readFloat();
        }
    }

    public static final Creator<Wind> CREATOR = new Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        @Override
        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };

    public Integer getChill() {
        return chill;
    }

    public void setChill(Integer chill) {
        this.chill = chill;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    @NotNull
    @Override
    public String toString() {
        return "Wind{" +
                "chill=" + chill +
                ", direction=" + direction +
                ", speed=" + speed +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (chill == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(chill);
        }
        if (direction == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(direction);
        }
        if (speed == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(speed);
        }
    }
}