package com.kusitms.assignmentandroid;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kusitms.assignmentandroid.databinding.FragmentOrderBinding;
import com.kusitms.assignmentandroid.dto.ItemVO;

import java.util.ArrayList;


public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;
    private ArrayList<ItemVO> items;
    private OrderAdapter orderAdapter;

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items = new ArrayList<>();
        items.add(new ItemVO("세븐일레븐 여주점", "배송 완료"));
        items.add(new ItemVO("세븐일레븐 송도점", "배송중"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(inflater);

        // 어댑터 생성
        orderAdapter = new OrderAdapter(items);
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