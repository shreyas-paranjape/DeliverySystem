package in.co.foodamigo.foodapp.module.catalogue.view.app;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import common.module.common.view.app.NetworkFragment;
import common.module.drawer.view.app.NavigationDrawerFragment;
import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.catalogue.infra.net.ProductClient;
import in.co.foodamigo.foodapp.module.order.view.app.CartFragment;
import in.co.foodamigo.foodapp.module.order.view.app.OrderStatusFragment;
import in.co.foodamigo.foodapp.view.app.HomeActivity;

public class CatalogueActivity extends HomeActivity {

    private static final String TAG = CatalogueActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle(getResources().getString(R.string.title_Catalogue));
        //check network status and connectivity
        /*if (new NetworkManager(this).isConnected()) {
            // Refresh data
            new ProductClient(CatalogueActivity.this).fetchAndSaveProductCatalogue();
        } else {
            replaceContent(new NetworkFragment());
        }*/
    }

    @Override
    protected boolean confirmExit() {
        return false;
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
        return new MenuFragment();
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.container;
    }

    private class EventManager {

        public void onEvent(NavigationDrawerFragment.DrawerItemClickedEvent event) {
            if (!isDestroyed()) {
                replaceContent(
                        getDrawerController().getFragment(event.getGroup(), event.getChild()),
                        false);
            }
        }

        public void onEvent(CartFragment.ShowCartEvent event) {
            if (!isDestroyed()) {
                //replaceContent(new CartFragment());
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
    }


    public static class CatalogueRefreshedEvent {
    }


}