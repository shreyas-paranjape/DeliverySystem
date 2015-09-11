package in.co.foodamigo.foodapp.module.order.view.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.order.model.OrderManager;
import in.co.foodamigo.foodapp.module.order.model.OrderManagerImpl;
import in.co.foodamigo.foodapp.module.order.view.widget.OrderHistoryAdapter;

public class OrderHistoryFragment extends Fragment {

    private final OrderManager orderManager = new OrderManagerImpl();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ExpandableListView root =
                (ExpandableListView) inflater.inflate(
                        R.layout.fragment_order_history, container, false);
        root.setAdapter(new OrderHistoryAdapter(getActivity(), orderManager.getOrders()));
        return root;
    }
}
