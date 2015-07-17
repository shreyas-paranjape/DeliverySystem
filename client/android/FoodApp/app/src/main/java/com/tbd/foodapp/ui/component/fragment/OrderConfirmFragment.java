package com.tbd.foodapp.ui.component.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbd.foodapp.databinding.FragmentOrderConfirmBinding;

public class OrderConfirmFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentOrderConfirmBinding binding = FragmentOrderConfirmBinding.inflate(inflater);
        return binding.getRoot();
    }
}
