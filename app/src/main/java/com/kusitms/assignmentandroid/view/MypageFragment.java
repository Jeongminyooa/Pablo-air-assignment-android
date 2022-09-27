package com.kusitms.assignmentandroid.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kusitms.assignmentandroid.databinding.FragmentMypageBinding;
import com.kusitms.assignmentandroid.utils.PrefsHelper;

public class MypageFragment extends Fragment {

    private FragmentMypageBinding binding;

    public MypageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMypageBinding.inflate(getLayoutInflater());

        String name = PrefsHelper.read("nickName", "");
        binding.tvNickname.setText(name);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}