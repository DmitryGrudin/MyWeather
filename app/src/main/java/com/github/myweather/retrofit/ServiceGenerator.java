package com.github.myweather.retrofit;

import com.google.api.client.auth.oauth.OAuthParameters;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    // No need to instantiate this class.
    private ServiceGenerator() {
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, final OAuthParameters oauthParams) {
        OAuthInterceptor interceptor =  new OAuthInterceptor(oauthParams);

        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor);
        }

        Retrofit.Builder builder = new Retrofit.Builder()
                                        .baseUrl(baseUrl)
                                        .addConverterFactory(GsonConverterFactory.create());
        builder.client(httpClient.build());
        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }
}
