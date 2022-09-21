package com.kusitms.assignmentandroid.dto;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResult {
    @SerializedName("id")
    private Long userId;

    @Expose
    private String name;

    private String nickName;

    private List<String> role;

    private String accessToken;

    private String refreshToken;

    @NonNull
    @Override
    public String toString() {
        return "LoginResult { " +
                "id=" + userId +
                ", name=" + name +
                ", nickName=" + nickName +
                ", accessToken=" + accessToken +
                ", refreshToken=" + refreshToken + " }";
    }
}
