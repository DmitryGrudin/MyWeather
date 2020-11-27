package com.github.myweather.utils;

import androidx.databinding.BindingAdapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

public class BindingAdapters {

    @BindingAdapter({"srcUrl"})
    public static void loadImage(ImageView view, String src) {
        Glide.with(view.getContext())
                .load(src)
                .into(view);
    }

    @BindingAdapter({"dateUnixTime"})
    public static void showDate(TextView view, Long dateUnixTime) {
        if (dateUnixTime != null) {
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
            df.setTimeZone(TimeZone.getDefault());
            view.setText(df.format(new Date(dateUnixTime * 1000L)));
        } else {
            view.setText("");
        }
    }
}
