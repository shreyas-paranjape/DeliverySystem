package in.co.foodamigo.foodapp.module.order.view.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.databinding.FragmentCartBinding;
import in.co.foodamigo.foodapp.module.catalogue.model.Product;
import in.co.foodamigo.foodapp.module.order.model.Order;
import in.co.foodamigo.foodapp.module.order.model.OrderRepository;
import in.co.foodamigo.foodapp.module.order.view.model.OrderViewModelManagerImpl;
import in.co.foodamigo.foodapp.module.order.view.widget.CartItemAdapter;

public class CartFragment extends Fragment {

    private final OrderViewModelManagerImpl orderManager = new OrderViewModelManagerImpl();
    private ArrayAdapter cartItemsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        cartItemsAdapter = new CartItemAdapter(
                getActivity(),
                R.layout.item_order_current,
                orderManager.getOrder().getOrderItems());
        FragmentCartBinding rootBinding = FragmentCartBinding.inflate(inflater);
        rootBinding.lvCartItems.setAdapter(cartItemsAdapter);
        rootBinding.setOrder(orderManager.getOrder());
        rootBinding.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = OrderRepository.createOrderAndSave(orderManager.getOrder());
                EventBus.getDefault().post(new CheckOutEvent(order.getId()));
            }
        });
        return rootBinding.getRoot();
    }

    public void onEvent(ModifyCartEvent event) {
        switch (event.getAction()) {
            case ADD:
                orderManager.modifyItem(event.getProduct(), 1);
                break;
            case REMOVE:
                orderManager.modifyItem(event.getProduct(), -1);
                break;
        }
        cartItemsAdapter.notifyDataSetChanged();
        EventBus.getDefault().post(new CartModifiedEvent(orderManager.cartSize()));
    }


    public static class CartModifiedEvent {
        private final int cartSize;

        public CartModifiedEvent(int cartSize) {
            this.cartSize = cartSize;
        }

        public int getCartSize() {
            return cartSize;
        }
    }

    public enum CartAction {
        ADD() {},
        REMOVE() {}
    }

    public static class ModifyCartEvent {
        private final Product product;
        private final CartAction action;

        public ModifyCartEvent(Product product, CartAction action) {
            this.product = product;
            this.action = action;
        }

        public Product getProduct() {
            return product;
        }

        public CartAction getAction() {
            return action;
        }
    }

    public static class CheckOutEvent {
        private final long orderId;

        public CheckOutEvent(long orderId) {
            this.orderId = orderId;
        }

        public long getOrderId() {
            return orderId;
        }
    }
}