package com.kusitms.assignmentandroid.retrofit.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderRequest {
    @SerializedName("storeName")
    private String storeName;

    @SerializedName("orders")
    private List<OrderItemRequest> orders;

    public OrderRequest(String storeName, List<OrderItemRequest> orders) {
        this.storeName = storeName;
        this.orders = orders;
    }

    public static class OrderItemRequest {
        private Long id;
        private int count;

        public OrderItemRequest(Long id, int count) {
            this.id = id;
            this.count = count;
        }
    }

}
