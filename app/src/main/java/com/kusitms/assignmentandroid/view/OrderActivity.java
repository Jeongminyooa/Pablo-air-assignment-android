package com.kusitms.assignmentandroid.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.kusitms.assignmentandroid.adapter.OrderItemAdapter;
import com.kusitms.assignmentandroid.databinding.ActivityOrderBinding;
import com.kusitms.assignmentandroid.retrofit.RetrofitAPI;
import com.kusitms.assignmentandroid.retrofit.RetrofitClient;
import com.kusitms.assignmentandroid.retrofit.dto.ApiResponse;
import com.kusitms.assignmentandroid.retrofit.dto.OrderItemVO;
import com.kusitms.assignmentandroid.retrofit.dto.OrderRequest;
import com.kusitms.assignmentandroid.utils.PrefsHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity implements OrderItemAdapter.OnItemClick {

    private static final String TAG = "OrderActivity";
    private ActivityOrderBinding binding;

    private OrderItemAdapter orderItemAdapter;
    private ArrayList<OrderItemVO> items;
    // HashMap 형태로 item count 저장
    private HashMap<Long, Integer> itemCountMap;

    private RetrofitAPI retrofitAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        items = new ArrayList<>();
        itemCountMap = new HashMap<>();

        binding.tvTotalPrice.setText("0");

        binding.btnOrder.setOnClickListener(v -> {
            // 통신 (주문하기)
            postOrderItem();
        });

        binding.btnOrderCancel.setOnClickListener(v -> {
            finish();
        });

        // 통신 (아이템 가져오기)
        loadOrderItemResult();

    }

    // 주문 아이템 가져오기 통신
    private void loadOrderItemResult() {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();

        if(retrofitClient != null) {
            retrofitAPI = RetrofitClient.getRetrofitAPI(PrefsHelper.read("token", ""));
            retrofitAPI.getItems().enqueue(new Callback<ApiResponse<ArrayList<OrderItemVO>>>() {
                @Override
                public void onResponse(Call<ApiResponse<ArrayList<OrderItemVO>>> call, Response<ApiResponse<ArrayList<OrderItemVO>>> response) {
                    ApiResponse<ArrayList<OrderItemVO>> result = response.body();

                    if(!response.isSuccessful()) {
                        try {
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.d(TAG, result.toString());

                    items = result.getData();

                    orderItemAdapter = new OrderItemAdapter(OrderActivity.this, items);
                    binding.recyclerOrderItem.setAdapter(orderItemAdapter);

                }

                @Override
                public void onFailure(Call<ApiResponse<ArrayList<OrderItemVO>>> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
        }
    }

    private void postOrderItem() {

        List<OrderRequest.OrderItemRequest> orders = new ArrayList<>();
        for(Map.Entry<Long, Integer> elem : itemCountMap.entrySet()){
            if(elem.getValue() != 0) {
                orders.add(new OrderRequest.OrderItemRequest(elem.getKey(), elem.getValue()));
            }
        }

        RetrofitClient retrofitClient = RetrofitClient.getInstance();

        OrderRequest req = new OrderRequest("세븐일레븐", orders);

        if(retrofitClient != null) {
            retrofitAPI = RetrofitClient.getRetrofitAPI(PrefsHelper.read("token", ""));
            retrofitAPI.postOrder(req).enqueue(new Callback<ApiResponse<Long>>() {
                @Override
                public void onResponse(Call<ApiResponse<Long>> call, Response<ApiResponse<Long>> response) {
                    ApiResponse<Long> result = response.body();

                    if(!response.isSuccessful()) {
                        try {
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.d(TAG, result.toString());

                    Toast.makeText(OrderActivity.this, "성공적으로 주문이 완료되었습니다. 주문 내역에서 배송 상태를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<ApiResponse<Long>> call, Throwable t) {
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

    // adapter 데이터로 total 계산
    @Override
    public void onCalculateTotalPrice(int price, boolean isPlus) {
        int presentPrice = Integer.parseInt(binding.tvTotalPrice.getText().toString());
        String result = "0";

        if(isPlus) {
            result = String.valueOf(presentPrice + price);
        } else {
            result = String.valueOf(presentPrice - price);
        }

        Log.d(TAG, result);
        binding.tvTotalPrice.setText(result);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClickOrderItemCount(Long itemId, int count) {
        if(itemCountMap.containsKey(itemId)) {
            itemCountMap.replace(itemId, count);
        } else {
            itemCountMap.put(itemId, count);
        }

        Log.d(TAG, itemId +  " : " + count + "개");
    }

}