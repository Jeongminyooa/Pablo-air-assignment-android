package com.kusitms.assignmentandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.kusitms.assignmentandroid.adapter.OrderItemAdapter;
import com.kusitms.assignmentandroid.databinding.ActivityOrderBinding;
import com.kusitms.assignmentandroid.retrofit.RetrofitAPI;
import com.kusitms.assignmentandroid.retrofit.RetrofitClient;
import com.kusitms.assignmentandroid.retrofit.dto.ApiResponse;
import com.kusitms.assignmentandroid.retrofit.dto.LoginResult;
import com.kusitms.assignmentandroid.retrofit.dto.OrderItemVO;
import com.kusitms.assignmentandroid.utils.PrefsHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    private static final String TAG = "OrderActivity";
    private ActivityOrderBinding binding;

    private OrderItemAdapter orderItemAdapter;
    private ArrayList<OrderItemVO> items;

    private RetrofitAPI retrofitAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        items = new ArrayList<>();

        // 통신
        loadOrderItemResult();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.recyclerOrderItem.setAdapter(orderItemAdapter);
    }

    // 주문 아이템 가져오기 통신
    private void loadOrderItemResult() {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();

        if(retrofitClient != null) {
            retrofitAPI = RetrofitClient.getRetrofitAPI(null);
            retrofitAPI.getItems().enqueue(new Callback<ApiResponse<ArrayList<OrderItemVO>>>() {
                @Override
                public void onResponse(Call<ApiResponse<ArrayList<OrderItemVO>>> call, Response<ApiResponse<ArrayList<OrderItemVO>>> response) {
                    ApiResponse<ArrayList<OrderItemVO>> result = response.body();
                    Log.d(TAG, result.toString());

                    items = result.getData();

                    orderItemAdapter = new OrderItemAdapter(items);
                }

                @Override
                public void onFailure(Call<ApiResponse<ArrayList<OrderItemVO>>> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}