package com.kusitms.assignmentandroid.retrofit;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    private static final String PREFIX = "Bearer ";
    private String accessToken;

    public AuthenticationInterceptor (String accessToken) {
        this.accessToken = accessToken;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        // http 통신에 사용하는 Request 객체 생성
        Request original = chain.request();

        // header에 authorization 옵션 추가
        Request.Builder builder = original.newBuilder()
                .header("Authorization", PREFIX + accessToken);

        Request request = builder.build();

        return chain.proceed(request);
    }
}
