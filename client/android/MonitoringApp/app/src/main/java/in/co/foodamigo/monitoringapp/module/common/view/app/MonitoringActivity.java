package in.co.foodamigo.monitoringapp.module.common.view.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.monitoringapp.R;
import in.co.foodamigo.monitoringapp.module.catalogue.view.app.ProductFragment;
import in.co.foodamigo.monitoringapp.module.common.controller.DrawerController;
import in.co.foodamigo.monitoringapp.module.common.controller.MonitoringDrawerController;

public class MonitoringActivity extends AppCompatActivity {

    DrawerController drawerController = new MonitoringDrawerController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(new EventManager());
        setContentView(R.layout.activity_monitoring);
        setupToolbar();
        setupDrawer();
        Fragment fragment = getInitialFragment();
        if (fragment != null) {
            replaceContent(fragment, false);
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MonitoringActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).create().show();
    }


    protected void replaceContent(Fragment newFragment, boolean animate) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (animate) {
            ft.setCustomAnimations(R.animator.slide_up, R.animator.slide_down);
        }
        ft.replace(getFragmentContainerId(), newFragment).commit();
    }

    protected void replaceContent(Fragment newFragment) {
        replaceContent(newFragment, false);
    }

    protected void setupDrawer() {
        final NavigationDrawerFragment.SetupDrawerEvent setupDrawer =
                new NavigationDrawerFragment.SetupDrawerEvent(drawerController, R.id.navigation_drawer,
                        (DrawerLayout) findViewById(R.id.drawer_layout));
        EventBus.getDefault().post(setupDrawer);
    }

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }


    public int getFragmentContainerId() {
        return R.id.container;
    }

    public Fragment getInitialFragment() {
        return new ProductFragment();
    }


    public class EventManager {

        public void onEvent(NavigationDrawerFragment.DrawerItemClickedEvent event) {
            if (!isDestroyed()) {
                replaceContent(drawerController.getFragment(event.getPosition()));
            }
        }

    }
}
