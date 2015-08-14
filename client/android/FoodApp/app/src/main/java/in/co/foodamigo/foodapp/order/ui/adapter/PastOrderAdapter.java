package in.co.foodamigo.foodapp.order.ui.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import common.module.common.ui.adapter.AbstractExpandableListAdapter;
import in.co.foodamigo.foodapp.databinding.GroupPastOrderBinding;
import in.co.foodamigo.foodapp.databinding.ItemPastOrderBinding;

import java.util.HashMap;

import in.co.foodamigo.foodapp.order.domain.Order;
import in.co.foodamigo.foodapp.order.domain.OrderItem;
import io.realm.Realm;
import io.realm.RealmResults;

public class PastOrderAdapter extends AbstractExpandableListAdapter<Order, OrderItem> {

    public PastOrderAdapter(Context context) {
        super(context);
        RealmResults<Order> allOrders = Realm.getDefaultInstance().allObjects(Order.class);
        groupList = allOrders.subList(0, allOrders.size());
        childMapping = new HashMap<>();
        for (Order order : groupList) {
            childMapping.put(order, order.getOrderItems());
        }
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupPastOrderBinding binding = GroupPastOrderBinding.inflate(inflater);
        binding.setOrder((Order) getGroup(groupPosition));
        convertView = binding.getRoot();
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemPastOrderBinding binding = ItemPastOrderBinding.inflate(inflater);
        binding.setOrderItem((OrderItem) getChild(groupPosition, childPosition));
        convertView = binding.getRoot();
        return convertView;
    }

}
