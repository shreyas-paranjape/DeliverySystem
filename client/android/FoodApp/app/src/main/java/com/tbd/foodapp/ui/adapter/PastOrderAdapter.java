package com.tbd.foodapp.ui.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.tbd.foodapp.databinding.GroupPastOrderBinding;
import com.tbd.foodapp.databinding.ItemPastOrderBinding;
import com.tbd.foodapp.domain.product.order.Order;
import com.tbd.foodapp.domain.product.order.OrderItem;

import java.util.HashMap;

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
