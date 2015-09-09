package in.co.foodamigo.foodapp.module.order.view.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.databinding.FragmentOrderConfirmBinding;
import in.co.foodamigo.foodapp.module.order.model.Order;
import in.co.foodamigo.foodapp.module.order.model.OrderManagerImpl;
import in.co.foodamigo.foodapp.module.order.view.widget.CartItemAdapter;

public class OrderConfirmFragment extends Fragment {

    private Order order;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        order = new OrderManagerImpl().getOrderById(args.getLong("order_id"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentOrderConfirmBinding binding = FragmentOrderConfirmBinding.inflate(inflater);
        binding.btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOrder();
            }
        });
        binding.lvOrderItems.setAdapter(
                new CartItemAdapter(
                        getActivity(),
                        R.layout.item_order_current,
                        order.getOrderItems()));
        return binding.getRoot();
    }

    private void confirmOrder() {
        EventBus.getDefault().post(new ConfirmOrderEvent());
    }

    public static class ConfirmOrderEvent {
    }

}