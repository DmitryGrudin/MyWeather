package com.github.myweather.client.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class CurrentObservation implements Parcelable {
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("atmosphere")
    @Expose
    private Atmosphere atmosphere;
    @SerializedName("astronomy")
    @Expose
    private Astronomy astronomy;
    @SerializedName("condition")
    @Expose
    private Condition condition;
    @SerializedName("pubDate")
    @Expose
    private Long pubDate;

    protected CurrentObservation(Parcel in) {
        wind = in.readParcelable(Wind.class.getClassLoader());
        atmosphere = in.readParcelable(Atmosphere.class.getClassLoader());
        astronomy = in.readParcelable(Astronomy.class.getClassLoader());
        condition = in.readParcelable(Condition.class.getClassLoader());
        if (in.readByte() == 0) {
            pubDate = null;
        } else {
            pubDate = in.readLong();
        }
    }

    public static final Creator<CurrentObservation> CREATOR = new Creator<CurrentObservation>() {
        @Override
        public CurrentObservation createFromParcel(Parcel in) {
            return new CurrentObservation(in);
        }

        @Override
        public CurrentObservation[] newArray(int size) {
            return new CurrentObservation[size];
        }
    };

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Long getPubDate() {
        return pubDate;
    }

    public void setPubDate(Long pubDate) {
        this.pubDate = pubDate;
    }

    @NotNull
    @Override
    public String toString() {
        return "CurrentObservation{" +
                "wind=" + wind +
                ", atmosphere=" + atmosphere +
                ", astronomy=" + astronomy +
                ", condition=" + condition +
                ", pubDate=" + pubDate +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(wind, i);
        parcel.writeParcelable(atmosphere, i);
        parcel.writeParcelable(astronomy, i);
        parcel.writeParcelable(condition, i);
        if (pubDate == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(pubDate);
        }
    }
}
