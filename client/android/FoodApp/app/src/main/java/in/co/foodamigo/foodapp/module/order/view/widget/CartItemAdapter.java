package in.co.foodamigo.foodapp.module.order.view.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import in.co.foodamigo.foodapp.databinding.ItemOrderCurrentBinding;
import in.co.foodamigo.foodapp.module.order.model.OrderItem;

public class CartItemAdapter extends ArrayAdapter<OrderItem> {

    protected final LayoutInflater inflater;

    public CartItemAdapter(Context context, int resource, List<OrderItem> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemOrderCurrentBinding rootBinding =
                ItemOrderCurrentBinding.inflate(inflater, parent, false);
        rootBinding.setOrderItem(getItem(position));
        return rootBinding.getRoot();
    }
}
