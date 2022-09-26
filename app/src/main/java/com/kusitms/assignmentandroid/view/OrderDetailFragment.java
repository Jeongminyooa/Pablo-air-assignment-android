package com.kusitms.assignmentandroid.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kusitms.assignmentandroid.adapter.OrderDetailAdapter;
import com.kusitms.assignmentandroid.adapter.OrderItemAdapter;
import com.kusitms.assignmentandroid.databinding.FragmentOrderDetailBinding;
import com.kusitms.assignmentandroid.retrofit.RetrofitAPI;
import com.kusitms.assignmentandroid.retrofit.RetrofitClient;
import com.kusitms.assignmentandroid.retrofit.dto.ApiResponse;
import com.kusitms.assignmentandroid.retrofit.dto.OrderDetailVO;
import com.kusitms.assignmentandroid.retrofit.dto.OrderItemVO;
import com.kusitms.assignmentandroid.utils.PrefsHelper;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderDetailFragment extends Fragment {

    private static final String TAG = "OrderDetailFragment";

    private FragmentOrderDetailBinding binding;
    private ArrayList<OrderDetailVO> items;
    private OrderDetailAdapter orderAdapter;

    private RetrofitAPI retrofitAPI;

    public OrderDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items = new ArrayList<>();

        loadOrderItemResult();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderDetailBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }

    private void loadOrderItemResult() {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();

        if(retrofitClient != null) {
            retrofitAPI = RetrofitClient.getRetrofitAPI(PrefsHelper.read("token", ""));
            retrofitAPI.getOrderDetailList().enqueue(new Callback<ApiResponse<ArrayList<OrderDetailVO>>>() {
                @Override
                public void onResponse(Call<ApiResponse<ArrayList<OrderDetailVO>>> call, Response<ApiResponse<ArrayList<OrderDetailVO>>> response) {
                    ApiResponse<ArrayList<OrderDetailVO>> result = response.body();

                    if(!response.isSuccessful()) {
                        try {
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.d(TAG, result.toString() + result.getData().size());

                    items = result.getData();

                    // 어댑터 생성
                    orderAdapter = new OrderDetailAdapter(getContext(), items);
                    binding.recyclerViewOrder.setAdapter(orderAdapter);
                }

                @Override
                public void onFailure(Call<ApiResponse<ArrayList<OrderDetailVO>>> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
        }
    }
}