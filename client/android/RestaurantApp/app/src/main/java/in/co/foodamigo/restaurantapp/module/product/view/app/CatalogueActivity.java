package in.co.foodamigo.restaurantapp.module.product.view.app;

import android.app.Fragment;

import in.co.foodamigo.restaurantapp.R;
import in.co.foodamigo.restaurantapp.module.common.view.app.AbstractDrawerActivity;
import in.co.foodamigo.restaurantapp.module.common.view.app.NavigationDrawerFragment;

public class CatalogueActivity extends AbstractDrawerActivity {
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
        return null;
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

            }
        }
    }
}
