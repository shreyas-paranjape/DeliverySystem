package com.tbd.foodapp.ui.component.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbd.foodapp.databinding.FragmentDeliveryDetailsBinding;
import com.tbd.foodapp.ui.events.Event;

import de.greenrobot.event.EventBus;

public class DeliveryDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentDeliveryDetailsBinding binding = FragmentDeliveryDetailsBinding.inflate(inflater);
        binding.btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
        return binding.getRoot();
    }

    public void placeOrder() {
        EventBus.getDefault().post(new Event.PlaceOrder());
    }


}
