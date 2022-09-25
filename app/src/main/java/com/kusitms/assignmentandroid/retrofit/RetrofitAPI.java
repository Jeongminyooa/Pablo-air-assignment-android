package com.kusitms.assignmentandroid.retrofit;

import com.kusitms.assignmentandroid.retrofit.dto.LoginResult;
import com.kusitms.assignmentandroid.retrofit.dto.SerialNumberResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/login/oauth/kakao")
    Call<LoginResult> getLoginUser(@Query("accessToken") String accessToken);

    @GET("/serialNumber")
    Call<SerialNumberResult> getSerialNumber();
}
