package com.kusitms.assignmentandroid.view;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.kusitms.assignmentandroid.R;
import com.kusitms.assignmentandroid.databinding.FragmentMypageBinding;
import com.kusitms.assignmentandroid.retrofit.RetrofitAPI;
import com.kusitms.assignmentandroid.retrofit.RetrofitClient;
import com.kusitms.assignmentandroid.retrofit.dto.ApiResponse;
import com.kusitms.assignmentandroid.retrofit.dto.SerialNumberResult;
import com.kusitms.assignmentandroid.utils.PrefsHelper;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MypageFragment extends Fragment {

    private static final String TAG = "MypageFragment";
    private FragmentMypageBinding binding;

    private RetrofitAPI retrofitAPI;

    private String serialNumber;


    public MypageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMypageBinding.inflate(getLayoutInflater());

        String name = PrefsHelper.read("nickName", "");
        binding.tvNickname.setText(name);

        loadSerialNumber();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void loadSerialNumber() {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();

        if (retrofitClient != null) {
            retrofitAPI = RetrofitClient.getRetrofitAPI(PrefsHelper.read("token", ""));
            retrofitAPI.getSerialNumber().enqueue(new Callback<ApiResponse<String>>() {
                @Override
                public void onResponse(Call<ApiResponse<String>> call, Response<ApiResponse<String>> response) {
                    ApiResponse<String> result = response.body();

                    Log.d(TAG, result.toString());

                    if(!response.isSuccessful()) {
                        try {
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    serialNumber = result.getData();

                    binding.ivQRCode.setImageBitmap(getQRImage());
                }

                @Override
                public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
        }
    }
    public Bitmap getQRImage() {
        MultiFormatWriter writer = new MultiFormatWriter();

        try {
            // 바코드 생성
            Log.d(TAG, serialNumber);

            BitMatrix matrix = writer.encode(serialNumber, BarcodeFormat.QR_CODE,
                    350, 350);
            // 바코드 엔코더 생성
            BarcodeEncoder encoder = new BarcodeEncoder();

            return encoder.createBitmap(matrix);
        } catch(WriterException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}