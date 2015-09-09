package in.co.foodamigo.foodapp.module.order.view.app;

import android.app.Fragment;
import android.os.Bundle;

import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.common.view.app.AbstractDrawerActivity;

public class OrderActivity extends AbstractDrawerActivity {

    private static final String TAG = OrderActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(" ");
        Fragment orderConfirm = new OrderConfirmFragment();
        orderConfirm.setArguments(getIntent().getExtras().getBundle("order"));
        replaceContent(orderConfirm);
    }

    @Override
    protected boolean confirmExit() {
        return true;
    }

    @Override
    protected boolean hasParent() {
        return true;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_place_order;
    }

    @Override
    protected Object getEventManager() {
        return new EventManager();
    }

    @Override
    protected Fragment getInitialFragment() {
        return null;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.container;
    }

    @Override
    protected boolean needsDrawer() {
        return false;
    }

    private class EventManager {

        public void onEvent(DeliveryDetailsFragment.PlaceOrderEvent event) {
            if (!isDestroyed()) {
                replaceContent(new OrderStatusFragment());
            }
        }

        public void onEvent(OrderConfirmFragment.ConfirmOrderEvent event) {
            if (!isDestroyed()) {
                replaceContent(new DeliveryDetailsFragment());
            }
        }
    }


}