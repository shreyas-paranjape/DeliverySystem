package com.tbd.foodapp.ui.component.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbd.foodapp.databinding.FragmentAddressBinding;

public class AddressFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentAddressBinding binding = FragmentAddressBinding.inflate(inflater);
        return binding.getRoot();
    }
}
