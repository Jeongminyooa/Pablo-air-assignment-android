package com.kusitms.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kakao.sdk.user.UserApiClient;
import com.kusitms.assignmentandroid.databinding.ActivityLoginBinding;
import com.kusitms.assignmentandroid.databinding.ActivityMainBinding;
import com.kusitms.assignmentandroid.dto.LoginResult;
import com.kusitms.assignmentandroid.retrofit.RetrofitAPI;
import com.kusitms.assignmentandroid.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "사용자";
    private ActivityLoginBinding binding;

    private RetrofitAPI retrofitAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    login();
                }
                else{
                    accountLogin();
                }
            }
        });
    }
    public void login(){
        UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this,(oAuthToken, error) -> {
            if (error != null) {
                Log.e(TAG, "로그인 실패", error);
            } else if (oAuthToken != null) {
                Log.i(TAG, "로그인 성공(토큰) : " + oAuthToken.getAccessToken());
                // 서버 통신
                loadLoginResult(oAuthToken.getAccessToken());
            }
            return null;
        });
    }
    public void accountLogin(){
        UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this,(oAuthToken, error) -> {
            if (error != null) {
                Log.e(TAG, "로그인 실패", error);
            } else if (oAuthToken != null) {
                Log.i(TAG, "로그인 성공(토큰) : " + oAuthToken.getAccessToken());
                // 서버 통신
                loadLoginResult(oAuthToken.getAccessToken());
            }
            return null;
        });
    }

    private void loadLoginResult(String accessToken) {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();

        if(retrofitClient != null) {
            retrofitAPI = RetrofitClient.getRetrofitAPI(null);
            retrofitAPI.getLoginUser(accessToken).enqueue(new Callback<LoginResult>() {
                @Override
                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                    LoginResult loginResult = response.body();
                    Log.d(TAG, loginResult.toString());

                    PrefsHelper.write("token", loginResult.getAccessToken());
                    PrefsHelper.write("nickName", loginResult.getNickName());

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<LoginResult> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
        }

    }
}