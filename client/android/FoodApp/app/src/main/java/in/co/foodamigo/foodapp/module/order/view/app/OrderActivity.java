package in.co.foodamigo.foodapp.module.order.view.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.common.view.app.AbstractDrawerActivity;

public class OrderActivity extends AbstractDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(" ");
        setOrderIdAndReplace(new OrderConfirmFragment());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean confirmExit() {
        return false;
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
                OrderStatusFragment orderStatusFragment = new OrderStatusFragment();
                orderStatusFragment.setArguments(getIntent().getExtras().getBundle("order"));
                replaceContent(orderStatusFragment);
            }
        }

        public void onEvent(OrderConfirmFragment.ConfirmOrderEvent event) {
            if (!isDestroyed()) {
                setOrderIdAndReplace(new OrderStatusFragment());
            }
        }
    }

    private void setOrderIdAndReplace(Fragment fragment) {
        fragment.setArguments(getIntent().getExtras().getBundle("order"));
        replaceContent(fragment);
    }

}