package in.co.foodamigo.foodapp.module.common.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.catalogue.model.Product;
import in.co.foodamigo.foodapp.module.order.model.OrderItem;
import in.co.foodamigo.foodapp.module.order.view.app.CartFragment;

public class QuantityPicker extends LinearLayout {

    private final TextView tv_quantity;
    private Product product;
    private int quantity;

    public QuantityPicker(Context context) {
        this(context, null);
    }

    public QuantityPicker(Context context, int dummy, OrderItem orderItem) {
        this(context, null);
        this.product = orderItem.getProduct();
        this.quantity = orderItem.getQuantity();
    }

    public QuantityPicker(Context context, AttributeSet attrs) {
        super(context);
        setOrientation(LinearLayout.HORIZONTAL);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_quantity_picker, this, true);

        final Button btnAdd = (Button) findViewById(R.id.btn_add_quantity);
        tv_quantity = (TextView) findViewById(R.id.tv_quantity);
        final Button btnRemove = (Button) findViewById(R.id.btn_remove_quantity);
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        modifyItem(product, CartFragment.CartAction.ADD);
                    }
                }
        );

        btnRemove.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        modifyItem(product, CartFragment.CartAction.REMOVE);
                    }
                }
        );

    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    private void modifyItem(Product product, CartFragment.CartAction action) {
        EventBus.getDefault().post(
                new CartFragment.ModifyCartEvent(product, action));
    }


}
