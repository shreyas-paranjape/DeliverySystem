package in.co.foodamigo.foodapp.component.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.drawer.DrawerController;
import in.co.foodamigo.foodapp.module.drawer.SetupNavigationDrawer;

public abstract class HomeActivity extends AppCompatActivity {

    protected final DrawerController drawerController = new DrawerController();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    protected void replaceContent(int container_id, Fragment newFragment, boolean animate) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (animate) {
            ft.setCustomAnimations(R.animator.slide_up, R.animator.slide_down);
        }
        ft.replace(container_id, newFragment).commit();
    }

    protected void setupDrawer() {
        final SetupNavigationDrawer setupDrawer =
                new SetupNavigationDrawer(drawerController, R.id.navigation_drawer,
                        (DrawerLayout) findViewById(R.id.drawer_layout));
        EventBus.getDefault().post(setupDrawer);
    }

}
