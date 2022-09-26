package com.kusitms.assignmentandroid.adapter;

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

    public OrderItemAdapter(ArrayList<OrderItemVO> items) {
        this.items = items;
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

            Log.d("뿌잉", item.getName());
            binding.tvOrderStoreName.setText(item.getName());
            binding.tvOrderPrice.setText(item.getPrice());

            int count = Integer.parseInt(binding.tvCnt.getText().toString());
            // 수량
            binding.btnMinusCnt.setOnClickListener(v -> {
                if(count == 0) {
                   return;
                }
                binding.tvCnt.setText(String.valueOf(count - 1));
            });

            binding.btnPlusCnt.setOnClickListener(v -> {
                if(count > 100) {
                    return;
                }
                binding.tvCnt.setText(String.valueOf(count + 1));
            });
        }
    }
}
