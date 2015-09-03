package in.co.foodamigo.foodapp.module.order.view.app;

import android.app.Fragment;
import android.os.Bundle;

import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.view.app.HomeActivity;

public class PlaceOrderActivity extends HomeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        return new OrderConfirmFragment();
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.container;
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