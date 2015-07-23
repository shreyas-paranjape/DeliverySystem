package com.tbd.foodapp.ui.component.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbd.foodapp.databinding.FragmentOrderConfirmBinding;
import com.tbd.foodapp.app.events.Event;

import de.greenrobot.event.EventBus;

public class OrderConfirmFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentOrderConfirmBinding binding = FragmentOrderConfirmBinding.inflate(inflater);
        binding.btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOrder();
            }
        });
        return binding.getRoot();
    }

    private void confirmOrder() {
        EventBus.getDefault().post(new Event.ConfirmOrder());
    }


}
