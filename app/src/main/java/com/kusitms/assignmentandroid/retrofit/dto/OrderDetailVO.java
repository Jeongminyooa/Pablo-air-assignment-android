package com.kusitms.assignmentandroid.retrofit.dto;


import com.google.gson.annotations.SerializedName;

public class OrderDetailVO {

    @SerializedName("id")
    private String storeName;
    @SerializedName("id")
    private String orderStatus;

    public String getStoreName() {
        return storeName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public OrderDetailVO(String storeName, String orderStatus) {
        this.storeName = storeName;
        this.orderStatus = orderStatus;
    }
}
