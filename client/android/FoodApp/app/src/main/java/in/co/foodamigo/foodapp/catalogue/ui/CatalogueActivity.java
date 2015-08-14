package in.co.foodamigo.foodapp.catalogue.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import common.infra.network.NetworkManager;
import common.module.common.infra.NetworkConnectedEvent;
import common.module.common.infra.NetworkErrorEvent;
import common.module.common.ui.AbstractDrawerActivity;
import common.module.common.ui.NoNetworkFragment;
import common.module.drawer.infra.DrawerItemClickedEvent;
import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.catalogue.infra.CatalogueRefreshedEvent;
import in.co.foodamigo.foodapp.catalogue.infra.ProductClient;
import in.co.foodamigo.foodapp.profile.infra.ShowCartEvent;

public class CatalogueActivity extends AbstractDrawerActivity {

    private static final String TAG = CatalogueActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bind view
       /* Button chkOut = (Button) findViewById(R.id.btn_checkout);
        chkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceContent(new CartFragment(), true);
            }
        });*/

        //check network status and connectivity
        if (new NetworkManager(this).isConnected()) {
            // Refresh data
            ProductClient.fetchAndSaveProductCatalogue(getApplicationContext());
        } else {
            replaceContent(new NoNetworkFragment());
        }
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
        return R.layout.activity_home;
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

    private class EventManager {

        public void onEvent(DrawerItemClickedEvent event) {
            if (!isDestroyed()) {
                replaceContent(
                        drawerController.getFragment(event.getGroup(), event.getChild()),
                        false);
            }
        }

        public void onEvent(ShowCartEvent event) {
            replaceContent(new OrderMenuFragment());
        }

        public void onEvent(CatalogueRefreshedEvent event) {
            Log.i(TAG, "Showing Menu");
            if (!isDestroyed()) {
                replaceContent(new OrderMenuFragment());
                setupDrawer();
                setupToolbar();
            }
        }

        public void onEvent(NetworkConnectedEvent event) {
            if (!isDestroyed()) {
                ProductClient.fetchAndSaveProductCatalogue(getApplicationContext());
            }
        }

        public void onEvent(NetworkErrorEvent event) {
            if (!isDestroyed()) {
                replaceContent(new NoNetworkFragment());
            }
        }
    }
}