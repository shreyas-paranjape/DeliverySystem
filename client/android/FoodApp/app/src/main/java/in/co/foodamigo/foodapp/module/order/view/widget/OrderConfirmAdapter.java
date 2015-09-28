

package in.co.foodamigo.foodapp.module.order.view.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.List;

import in.co.foodamigo.foodapp.databinding.ItemOrderConfirmBinding;
import in.co.foodamigo.foodapp.module.order.model.OrderItem;

public class OrderConfirmAdapter extends ArrayAdapter<OrderItem> {

    protected final LayoutInflater inflater;

    public OrderConfirmAdapter(Context context, int resource, List<OrderItem> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ItemOrderConfirmBinding rootBinding =
                ItemOrderConfirmBinding.inflate(inflater, parent, false);
        rootBinding.setOrderItem(getItem(position));
        LinearLayout root = (LinearLayout) rootBinding.getRoot();
        return root;
    }

}

