package in.co.foodamigo.restaurantapp.module.order.view.app;

import android.app.Fragment;

import in.co.foodamigo.restaurantapp.module.common.view.app.AbstractDrawerActivity;

public class OrderActivity extends AbstractDrawerActivity {

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
        return 0;
    }

    @Override
    protected Object getEventManager() {
        return null;
    }

    @Override
    protected Fragment getInitialFragment() {
        return null;
    }

    @Override
    protected int getFragmentContainerId() {
        return 0;
    }

    @Override
    protected boolean needsDrawer() {
        return true;
    }
}
