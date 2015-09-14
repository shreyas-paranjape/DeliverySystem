package in.co.foodamigo.foodapp.module.order.view.app;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.co.foodamigo.foodapp.databinding.FragmentOrderStatusBinding;
import in.co.foodamigo.foodapp.module.order.model.Order;
import in.co.foodamigo.foodapp.module.order.model.OrderManagerImpl;

public class OrderStatusFragment extends Fragment {

    private Order order;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        order = new OrderManagerImpl().getOrderById(args.getLong("order_id"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentOrderStatusBinding rootBinding = FragmentOrderStatusBinding.inflate(inflater);
        rootBinding.setOrder(order);
        return rootBinding.getRoot();
    }

}