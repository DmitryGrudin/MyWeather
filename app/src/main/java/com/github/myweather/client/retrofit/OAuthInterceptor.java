package com.github.myweather.client.retrofit;

import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.GenericUrl;

import org.jetbrains.annotations.NotNull;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.security.GeneralSecurityException;

class OAuthInterceptor implements Interceptor {

    private final String clientSharedSecret;
    private final String consumerKey;

    public OAuthInterceptor(String clientSharedSecret, String consumerKey) {
        this.clientSharedSecret = clientSharedSecret;
        this.consumerKey = consumerKey;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        GenericUrl requestUrl = new GenericUrl(originalRequest.url().toString());

        OAuthHmacSigner signer = new OAuthHmacSigner();
        signer.clientSharedSecret = clientSharedSecret;
        OAuthParameters oauthParams = new OAuthParameters();
        oauthParams.consumerKey = consumerKey;
        oauthParams.signer = signer;
        oauthParams.version = "1.0";
        oauthParams.computeNonce();
        oauthParams.computeTimestamp();

        try {
            oauthParams.computeSignature("GET", requestUrl);
            Request compressedRequest = originalRequest.newBuilder()
                    .header("Authorization", oauthParams.getAuthorizationHeader())
                    .method(originalRequest.method(), originalRequest.body())
                    .build();
            return chain.proceed(compressedRequest);
        } catch (GeneralSecurityException ignored) {
        }

        return chain.proceed(originalRequest);
    }
}