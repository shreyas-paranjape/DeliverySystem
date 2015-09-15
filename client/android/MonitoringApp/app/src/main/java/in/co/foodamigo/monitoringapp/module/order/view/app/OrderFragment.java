package in.co.foodamigo.monitoringapp.module.order.view.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import in.co.foodamigo.monitoringapp.R;
import in.co.foodamigo.monitoringapp.module.order.model.Order;
import in.co.foodamigo.monitoringapp.module.order.view.widget.OrderAdapter;

public class OrderFragment extends Fragment {

    private List<Order> orderList;

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        orderList = new ArrayList<>();
        createDummyOrders();
    }

    private void createDummyOrders() {
        orderList.add(getDummyOrder());
        orderList.add(getDummyOrder());
        orderList.add(getDummyOrder());
        orderList.add(getDummyOrder());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recycler = (RecyclerView) inflater.inflate(
                R.layout.fragment_order, container, false);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(new OrderAdapter(getActivity(), orderList));
        return recycler;
    }

    public Order getDummyOrder() {
        Order o = new Order();
        o.setId(new Random().nextLong());
        return o;
    }
}
