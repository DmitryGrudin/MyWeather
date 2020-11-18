package com.github.myweather.retrofit;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class YahooInterceptor  implements Interceptor {
    private final String appId;

    public YahooInterceptor(String appId) {
        this.appId = appId;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request request = originalRequest.newBuilder()
                .header("X-Yahoo-App-Id", appId)
                .method(originalRequest.method(), originalRequest.body())
                .build();
        return chain.proceed(request);
    }
}
