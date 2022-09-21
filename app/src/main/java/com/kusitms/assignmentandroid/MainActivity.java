package com.kusitms.assignmentandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.kusitms.assignmentandroid.databinding.ActivityMainBinding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);


        // 리스너 등록
        SettingListener();
        // 처음 시작할 탭 설정
        binding.bottomNavigationView.setSelectedItemId(R.id.tab_home);
    }

    private void SettingListener() {
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.tab_home:
                        Log.d(TAG, "tab_home");
                        getSupportFragmentManager().beginTransaction().replace(R.id.lyHome, new HomeFragment())
                                .commit();
                        return true;
                    case R.id.tab_order:
                        getSupportFragmentManager().beginTransaction().replace(R.id.lyHome, new OrderFragment())
                                .commit();
                        return true;
                }
                return false;
            }
        });
    }
}