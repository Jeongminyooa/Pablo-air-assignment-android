package com.kusitms.assignmentandroid.retrofit;

import com.kusitms.assignmentandroid.dto.LoginResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/login/oauth/kakao")
    Call<LoginResult> getLoginUser(@Query("accessToken") String accessToken);

}
