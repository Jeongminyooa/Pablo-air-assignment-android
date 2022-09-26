package com.kusitms.assignmentandroid.retrofit.dto;

import com.google.gson.annotations.SerializedName;

public class OrderItemVO {
    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private String price;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
