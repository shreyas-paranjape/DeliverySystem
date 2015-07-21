package com.tbd.foodapp.ui.component.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbd.foodapp.databinding.FragmentOrderStatusBinding;

public class OrderStatusFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentOrderStatusBinding binding = FragmentOrderStatusBinding.inflate(inflater);
        return binding.getRoot();
    }
}
