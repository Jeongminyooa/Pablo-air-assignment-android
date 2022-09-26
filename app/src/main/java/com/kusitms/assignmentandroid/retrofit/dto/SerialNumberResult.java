package com.kusitms.assignmentandroid.retrofit.dto;

import androidx.annotation.NonNull;

public class SerialNumberResult {

    int statusCode;
    String responseMessage;
    String data;

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public String getData() {
        return data;
    }
}
