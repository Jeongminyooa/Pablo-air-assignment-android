package com.kusitms.assignmentandroid.dto;



public class ItemVO {
    String storeName;
    String orderStatus;

    public String getStoreName() {
        return storeName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public ItemVO(String storeName, String orderStatus) {
        this.storeName = storeName;
        this.orderStatus = orderStatus;
    }
}
