package com.kusitms.assignmentandroid.retrofit;

import com.kusitms.assignmentandroid.retrofit.dto.ApiResponse;
import com.kusitms.assignmentandroid.retrofit.dto.LoginResult;
import com.kusitms.assignmentandroid.retrofit.dto.OrderDetailVO;
import com.kusitms.assignmentandroid.retrofit.dto.OrderItemVO;
import com.kusitms.assignmentandroid.retrofit.dto.OrderRequest;
import com.kusitms.assignmentandroid.retrofit.dto.SerialNumberResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/login/oauth/kakao")
    Call<LoginResult> getLoginUser(@Query("accessToken") String accessToken);

    @GET("/serialNumber")
    Call<ApiResponse<String>> getSerialNumber();

    @GET("/items")
    Call<ApiResponse<ArrayList<OrderItemVO>>> getItems();

    @POST("/order")
    Call<ApiResponse<Long>> postOrder(@Body OrderRequest req);

    @GET("/order")
    Call<ApiResponse<ArrayList<OrderDetailVO>>> getOrderDetailList();

}
