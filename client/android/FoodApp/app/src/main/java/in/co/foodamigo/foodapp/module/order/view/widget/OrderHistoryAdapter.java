package in.co.foodamigo.foodapp.module.order.view.widget;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;

import in.co.foodamigo.foodapp.databinding.GroupPastOrderBinding;
import in.co.foodamigo.foodapp.databinding.ItemPastOrderBinding;
import in.co.foodamigo.foodapp.module.common.view.widget.AbstractExpandableListAdapter;
import in.co.foodamigo.foodapp.module.order.model.Order;
import in.co.foodamigo.foodapp.module.order.model.OrderItem;

public class OrderHistoryAdapter extends AbstractExpandableListAdapter<Order, OrderItem> {

    public OrderHistoryAdapter(Context context, List<Order> orders) {
        super(context);
        this.groupList = orders;
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
