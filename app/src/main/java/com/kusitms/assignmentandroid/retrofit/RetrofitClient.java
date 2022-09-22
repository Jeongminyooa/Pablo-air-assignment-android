package com.kusitms.assignmentandroid.retrofit;

import android.text.TextUtils;

import com.kusitms.assignmentandroid.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// singleton
public class RetrofitClient {
    private static RetrofitClient instance = null;
    private static RetrofitAPI retrofitAPI;
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private final static String BASE_URL = BuildConfig.BASE_URL;

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit;

    private RetrofitClient() {
        retrofit = builder.build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }

    public static RetrofitClient getInstance() {
        if(instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static RetrofitAPI getRetrofitAPI(String token) {
        if(!TextUtils.isEmpty(token)) {
            authenticationService(token);
        }
        return retrofitAPI;
    }

    public static void authenticationService(String token) {
        if (!TextUtils.isEmpty(token)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(token);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
                retrofitAPI = retrofit.create(RetrofitAPI.class);
            }
        }
    }
}
