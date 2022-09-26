package com.kusitms.assignmentandroid.retrofit.dto;


import com.google.gson.annotations.SerializedName;

public class OrderDetailVO {
    @SerializedName("order_id")
    private Long orderId;

    @SerializedName("storeName")
    private String storeName;

    @SerializedName("order_status")
    private boolean orderStatus;

    public Long getOrderId() {
        return orderId;
    }
    public String getStoreName() {
        return storeName;
    }

    public boolean getOrderStatus() {
        return orderStatus;
    }


    public OrderDetailVO(String storeName, boolean orderStatus) {
        this.storeName = storeName;
        this.orderStatus = orderStatus;
    }
}
