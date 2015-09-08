package in.co.foodamigo.foodapp.module.catalogue.view.app;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.catalogue.infra.net.ProductClient;
import in.co.foodamigo.foodapp.module.common.view.app.AbstractDrawerActivity;
import in.co.foodamigo.foodapp.module.common.view.app.NavigationDrawerFragment;
import in.co.foodamigo.foodapp.module.common.view.app.NetworkFragment;
import in.co.foodamigo.foodapp.module.order.view.app.CartFragment;
import in.co.foodamigo.foodapp.module.order.view.app.OrderStatusFragment;
import in.co.foodamigo.foodapp.module.order.view.app.OrderActivity;
import in.co.foodamigo.foodapp.module.profile.view.app.ProfileFragment;

public class CatalogueActivity extends AbstractDrawerActivity {

    private static final String TAG = CatalogueActivity.class.getName();

    private SlidingUpPanelLayout cartHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle("");
        cartHolder = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        //setTitle(getResources().getString(R.string.title_Catalogue));
        //check network status and connectivity
        /*if (new NetworkManager(this).isConnected()) {
            // Refresh data
            new ProductClient(CatalogueActivity.this).fetchAndSaveProductCatalogue();
        } else {
            replaceContent(new NetworkFragment());
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.global, menu);
        return true;

    }

    @Override
    protected boolean confirmExit() {
        return true;
    }

    @Override
    protected boolean hasParent() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_catalogue;
    }

    @Override
    protected Object getEventManager() {
        return new EventManager();
    }

    @Override
    protected Fragment getInitialFragment() {
        return new ProfileFragment();
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.container;
    }

    @Override
    protected boolean needsDrawer() {
        return true;
    }

    private class EventManager {

        public void onEvent(NavigationDrawerFragment.DrawerItemClickedEvent event) {
            if (!isDestroyed()) {
                replaceContent(
                        getDrawerController().getFragment(event.getGroup(), event.getChild()),
                        false);
            }
        }

        public void onEvent(MenuFragment.ShowOrderStatusEvent event) {
            if (!isDestroyed()) {
                replaceContent(new OrderStatusFragment());
            }
        }

        public void onEvent(CatalogueRefreshedEvent event) {
            Log.i(TAG, "Showing Menu");
            if (!isDestroyed()) {
                replaceContent(new MenuFragment());
                setupDrawer();
                setupToolbar();
            }
        }

        public void onEvent(NetworkFragment.NetworkConnectedEvent event) {
            if (!isDestroyed()) {
                new ProductClient(CatalogueActivity.this).fetchAndSaveProductCatalogue();
            }
        }

        public void onEvent(NetworkFragment.NetworkErrorEvent event) {
            if (!isDestroyed()) {
                replaceContent(new NetworkFragment());
            }
        }

        public void onEvent(CartFragment.CartModifiedEvent event) {
            if (!isDestroyed()) {
                if (event.getCartSize() == 0) {
                    hideCartPanel();
                } else {
                    showCartPanel();
                }
            }
        }

        public void onEvent(CartFragment.CheckOutEvent event) {
            Intent startActivityIntent = new Intent(CatalogueActivity.this, OrderActivity.class);
            Bundle order = new Bundle();
            order.putLong("order_id", event.getOrderId());
            startActivityIntent.putExtra("order", order);
            startActivity(startActivityIntent);
        }
    }

    private void showCartPanel() {
        cartHolder.setPanelHeight(100);
    }

    private void hideCartPanel() {
        cartHolder.setPanelHeight(0);
    }

    public static class CatalogueRefreshedEvent {
    }

}