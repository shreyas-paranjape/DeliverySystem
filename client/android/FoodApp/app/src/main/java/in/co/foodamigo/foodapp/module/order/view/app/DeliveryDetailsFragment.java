package in.co.foodamigo.foodapp.module.order.view.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.databinding.FragmentDeliveryDetailsBinding;

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
        EventBus.getDefault().post(new PlaceOrderEvent());
    }

    public static class PlaceOrderEvent {
    }

}