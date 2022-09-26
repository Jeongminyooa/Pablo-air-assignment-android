package com.kusitms.assignmentandroid.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kusitms.assignmentandroid.R;
import com.kusitms.assignmentandroid.databinding.RecyclerItemOrderBinding;
import com.kusitms.assignmentandroid.retrofit.dto.OrderItemVO;

import java.util.ArrayList;

// 주문 아이템 어댑터
public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    private ArrayList<OrderItemVO> items;
    private OnItemClick mCallBack;

    public OrderItemAdapter(Context context, ArrayList<OrderItemVO> items) {
        this.items = items;
        this.mCallBack = (OnItemClick) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_order, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerItemOrderBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = RecyclerItemOrderBinding.bind(itemView);
        }

        void bindItem(OrderItemVO item) {
            binding.tvOrderStoreName.setText(item.getName());
            binding.tvOrderPrice.setText(item.getPrice());

            // 수량
            binding.btnMinusCnt.setOnClickListener(v -> {
                int count = Integer.parseInt(binding.tvCnt.getText().toString());
                if(count == 0) {
                   return;
                }
                binding.tvCnt.setText(String.valueOf(count - 1));
                mCallBack.onCalculateTotalPrice(Integer.parseInt(binding.tvOrderPrice.getText().toString()), false);
            });

            binding.btnPlusCnt.setOnClickListener(v -> {
                int count = Integer.parseInt(binding.tvCnt.getText().toString());
                if(count > 100) {
                    return;
                }
                binding.tvCnt.setText(String.valueOf(count + 1));
                mCallBack.onCalculateTotalPrice(Integer.parseInt(binding.tvOrderPrice.getText().toString()), true);
            });
        }
    }

    public interface OnItemClick {
        void onCalculateTotalPrice(int price, boolean isPlus);
    }
}
