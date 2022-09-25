package com.kusitms.assignmentandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kusitms.assignmentandroid.databinding.ActivityOrderBinding;

public class OrderActivity extends AppCompatActivity {

    private ActivityOrderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}