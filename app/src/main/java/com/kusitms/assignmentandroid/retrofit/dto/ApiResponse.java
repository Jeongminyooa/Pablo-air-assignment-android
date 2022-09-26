package com.kusitms.assignmentandroid.retrofit.dto;

import com.google.gson.annotations.SerializedName;

public class ApiResponse<T> {
    @SerializedName("statusCode")
    private int statusCode;

    @SerializedName("responseMessage")
    private String responseMessage;

    @SerializedName("data")
    private T data;

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public T getData() {
        return data;
    }

    public String toString() {
        return "status code : " + getStatusCode() +
                "\nresponse message : " + getResponseMessage();
    }
}
