package com.kusitms.assignmentandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kusitms.assignmentandroid.R;
import com.kusitms.assignmentandroid.databinding.RecyclerItemOrderDetatilBinding;
import com.kusitms.assignmentandroid.retrofit.dto.OrderDetailVO;

import java.util.ArrayList;

// 주문 내역 어댑터
public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {

    private ArrayList<OrderDetailVO> items;

    public OrderDetailAdapter(ArrayList<OrderDetailVO> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_order_detatil, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 아이템 값 연결 -> view holder
        holder.bindItem(items.get(position));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerItemOrderDetatilBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 이미 만들어진 itemView와 binding
            binding = RecyclerItemOrderDetatilBinding.bind(itemView);
        }

        void bindItem(OrderDetailVO item) {
            binding.tvOrderDetailStoreName.setText(item.getStoreName());
            binding.tvOrderDetailStatus.setText(item.getOrderStatus());
        }
    }
}
