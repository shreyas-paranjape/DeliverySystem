package in.co.foodamigo.foodapp.module.order.view.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import common.module.common.ui.AbstractDrawerActivity;
import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.databinding.FragmentOrderConfirmBinding;
import in.co.foodamigo.foodapp.databinding.FragmentOrderStatusBinding;
import in.co.foodamigo.foodapp.module.profile.view.app.ProfileActivity;

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
                replaceContent(new ProfileActivity.DeliveryDetailsFragment());
            }
        }
    }


    public static class OrderConfirmFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            FragmentOrderConfirmBinding binding = FragmentOrderConfirmBinding.inflate(inflater);
            binding.btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmOrder();
                }
            });
            return binding.getRoot();
        }

        private void confirmOrder() {
            EventBus.getDefault().post(new ConfirmOrderEvent());
        }


    }

    public static class OrderStatusFragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            FragmentOrderStatusBinding binding = FragmentOrderStatusBinding.inflate(inflater);
            return binding.getRoot();
        }
    }

    public static class PastOrdersFragment extends Fragment {
    }

    public static class ConfirmOrderEvent {
    }

    public static class PlaceOrderEvent {
    }
}
