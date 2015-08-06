package in.co.foodamigo.foodapp.module.order;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.co.foodamigo.foodapp.events.Event;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.databinding.FragmentOrderConfirmBinding;

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
