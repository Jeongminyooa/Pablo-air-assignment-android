package com.kusitms.assignmentandroid;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kusitms.assignmentandroid.databinding.RecyclerItemOrderBinding;
import com.kusitms.assignmentandroid.dto.ItemVO;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.VH> {

    private ArrayList<ItemVO> items;

    public OrderAdapter(ArrayList<ItemVO> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_order, parent, false);
        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        // 아이템 값 연결 -> view holder
        holder.bindItem(items.get(position));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {
        RecyclerItemOrderBinding recyclerItemOrderBinding;

        public VH(@NonNull View itemView) {
            super(itemView);
            // 이미 만들어진 itemView와 binding
            recyclerItemOrderBinding = RecyclerItemOrderBinding.bind(itemView);
        }

        void bindItem(ItemVO item) {
            recyclerItemOrderBinding.tvStoreName.setText(item.getStoreName());
            recyclerItemOrderBinding.tvStatus.setText(item.getOrderStatus());
        }
    }
}
