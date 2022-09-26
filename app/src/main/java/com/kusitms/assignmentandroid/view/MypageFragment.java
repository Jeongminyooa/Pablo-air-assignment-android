package com.kusitms.assignmentandroid.view;

import static com.kusitms.assignmentandroid.utils.QRCodeHelper.getQRImage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kusitms.assignmentandroid.databinding.FragmentMypageBinding;
import com.kusitms.assignmentandroid.retrofit.RetrofitAPI;
import com.kusitms.assignmentandroid.retrofit.RetrofitClient;
import com.kusitms.assignmentandroid.retrofit.dto.ApiResponse;
import com.kusitms.assignmentandroid.utils.PrefsHelper;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MypageFragment extends Fragment {

    private static final String TAG = "MypageFragment";
    private FragmentMypageBinding binding;

    private RetrofitAPI retrofitAPI;


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

                    if(!response.isSuccessful()) {
                        try {
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.d(TAG, result.toString());

                    binding.ivQRCode.setImageBitmap(getQRImage(result.getData()));
                }

                @Override
                public void onFailure(Call<ApiResponse<String>> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}