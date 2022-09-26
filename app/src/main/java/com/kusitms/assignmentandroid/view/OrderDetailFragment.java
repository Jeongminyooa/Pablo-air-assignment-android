package com.kusitms.assignmentandroid.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kusitms.assignmentandroid.adapter.OrderDetailAdapter;
import com.kusitms.assignmentandroid.databinding.FragmentOrderDetailBinding;
import com.kusitms.assignmentandroid.retrofit.dto.OrderDetailVO;

import java.util.ArrayList;


public class OrderDetailFragment extends Fragment {

    private FragmentOrderDetailBinding binding;
    private ArrayList<OrderDetailVO> items;
    private OrderDetailAdapter orderAdapter;

    public OrderDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items = new ArrayList<>();
        items.add(new OrderDetailVO("세븐일레븐 여주점", "배송 완료"));
        items.add(new OrderDetailVO("세븐일레븐 송도점", "배송중"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderDetailBinding.inflate(inflater);

        // 어댑터 생성
        orderAdapter = new OrderDetailAdapter(items);
        binding.recyclerViewOrder.setAdapter(orderAdapter);

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
}