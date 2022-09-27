package com.kusitms.assignmentandroid.adapter;

import static com.kusitms.assignmentandroid.utils.QRCodeUtil.getQRImage;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.kusitms.assignmentandroid.R;
import com.kusitms.assignmentandroid.databinding.RecyclerItemOrderDetatilBinding;
import com.kusitms.assignmentandroid.retrofit.RetrofitAPI;
import com.kusitms.assignmentandroid.retrofit.RetrofitClient;
import com.kusitms.assignmentandroid.retrofit.dto.ApiResponse;
import com.kusitms.assignmentandroid.retrofit.dto.OrderDetailVO;
import com.kusitms.assignmentandroid.utils.AES256Util;
import com.kusitms.assignmentandroid.utils.PrefsHelper;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 주문 내역 어댑터
public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {

    private static final String TAG = "OrderDetailAdapter";
    private ArrayList<OrderDetailVO> items;
    private Context context;

    private RetrofitAPI retrofitAPI;

    private AES256Util aes256Util;

    public OrderDetailAdapter(Context context, ArrayList<OrderDetailVO> items) {
        this.context = context;
        this.items = items;

        aes256Util = new AES256Util();
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

            if(item.getOrderStatus()) {
                // 물품 가져감
                binding.tvOrderDetailStatus.setText("배송 완료");

                // 물품이 도착했으므로 버튼 비활성화
                binding.btnOrderDetail.setBackgroundResource(R.drawable.style_cancel_button);
                binding.btnOrderDetail.setTextColor(Color.BLACK);
                binding.btnOrderDetail.setClickable(false);
            } else {
                // 물품 도착함
                binding.tvOrderDetailStatus.setText("물품 도착");
            }

            binding.btnOrderDetail.setOnClickListener(v -> {
                // 나의 QR 확인이 가능한 다이얼로그 출력
                loadSerialNumber(item.getOrderId());
            });
        }
    }

    public void loadSerialNumber(Long orderId) {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();

        if (retrofitClient != null) {
            retrofitAPI = RetrofitClient.getRetrofitAPI(PrefsHelper.read("token", ""));
            retrofitAPI.getSerialNumber(orderId).enqueue(new Callback<ApiResponse<String>>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                    ApiResponse<String> result = response.body();

                    if(!response.isSuccessful()) {
                        try {
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    Log.d(TAG, result.toString());

                    // 복호화 - 암호화
                    String serialNumber = aes256Util.transferKey(result.getData());

                    // QR 다이얼로그
                    AlertDialog dig = new AlertDialog.Builder(context)
                            .setTitle("QR 인증")
                            .setMessage("배송지 카메라에 QR을 인식해주세요.")
                            .setPositiveButton("확인", null)
                            .create();

                    ImageView iv = new ImageView(context);
                    iv.setImageBitmap(getQRImage(serialNumber));
                    dig.setView(iv);

                    dig.show();
                }

                @Override
                public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
        }
    }
}
