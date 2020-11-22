package com.github.myweather.retrofit;

import com.github.myweather.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private ServiceGenerator() {
    }

    @NotNull
    public static <S> S createService(Class<S> serviceClass) {
        OAuthInterceptor oauthInterceptor = new OAuthInterceptor(Constants.CLIENT_SECRET, Constants.CLIENT_ID);
        if (!httpClient.interceptors().contains(oauthInterceptor)) {
            httpClient.addInterceptor(oauthInterceptor);
        }

        YahooInterceptor yahooInterceptor = new YahooInterceptor(Constants.APP_ID);
        if (!httpClient.interceptors().contains(yahooInterceptor)) {
            httpClient.addInterceptor(yahooInterceptor);
        }

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(Constants.API_BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                        .client(httpClient.build())
                                        .build();

        return retrofit.create(serviceClass);
    }
}
