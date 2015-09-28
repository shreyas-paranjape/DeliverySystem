package in.co.foodamigo.foodapp.module.order.view.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.databinding.ItemOrderCurrentBinding;
import in.co.foodamigo.foodapp.module.catalogue.model.Product;
import in.co.foodamigo.foodapp.module.order.model.OrderItem;
import in.co.foodamigo.foodapp.module.order.view.app.CartFragment;

public class CartItemAdapter extends ArrayAdapter<OrderItem> {

    protected final LayoutInflater inflater;

    public CartItemAdapter(Context context, int resource, List<OrderItem> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ItemOrderCurrentBinding rootBinding =
                ItemOrderCurrentBinding.inflate(inflater, parent, false);
        rootBinding.setOrderItem(getItem(position));
        LinearLayout root = (LinearLayout) rootBinding.getRoot();
        rootBinding.btnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyItem(getItem(position).getProduct(), CartFragment.CartAction.ADD);
            }
        });

        rootBinding.btnRemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyItem(getItem(position).getProduct(), CartFragment.CartAction.REMOVE);
            }
        });
        return root;
    }

    private void modifyItem(Product product, CartFragment.CartAction action) {
        EventBus.getDefault().post(
                new CartFragment.ModifyCartEvent(product, action));
    }
}
