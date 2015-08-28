package in.co.foodamigo.foodapp.order.ui;

import android.app.Fragment;
import android.os.Bundle;

import common.module.common.ui.AbstractDrawerActivity;
import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.order.infra.ConfirmOrderEvent;
import in.co.foodamigo.foodapp.order.infra.PlaceOrderEvent;
import in.co.foodamigo.foodapp.profile.ui.DeliveryDetailsFragment;

public class PlaceOrderActivity extends AbstractDrawerActivity {

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

        public void onEvent(PlaceOrderEvent event) {
            if (!isDestroyed()) {
                replaceContent(new OrderStatusFragment());
            }
        }

        public void onEvent(ConfirmOrderEvent event) {
            if (!isDestroyed()) {
                replaceContent(new DeliveryDetailsFragment());
            }
        }
    }


}
