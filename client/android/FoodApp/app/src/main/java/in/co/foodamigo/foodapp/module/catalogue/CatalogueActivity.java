package in.co.foodamigo.foodapp.module.catalogue;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.events.Event;
import in.co.foodamigo.foodapp.module.drawer.NavigationDrawerItemClicked;
import in.co.foodamigo.foodapp.module.drawer.SetupNavigationDrawer;
import in.co.foodamigo.foodapp.module.profile.CartFragment;
import in.co.foodamigo.foodapp.module.drawer.DrawerController;

public class CatalogueActivity extends AppCompatActivity {

    private static final String TAG = CatalogueActivity.class.getName();
    private final DrawerController drawerController = new DrawerController();
    private int notification_id = 1989;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Register Event Manager
        EventBus.getDefault().register(new EventManager());


        // Bind view
        setContentView(R.layout.activity_home);
        Button chkOut = (Button) findViewById(R.id.btn_checkout);
        chkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceContent(new CartFragment(), true);
            }
        });

        setupToolbar();
        setupDrawer();

        RemoteViews statusContent = new RemoteViews(getPackageName(), R.layout.view_order_status);
        final NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContent(statusContent);
        final NotificationManager notificationmanager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationmanager.notify(notification_id, builder.build());

        // Refresh data
        ProductClient.fetchAndSaveProductCatalogue(getApplicationContext());
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle(R.string.app_name);
        }
    }

    private void replaceContent(Fragment newFragment, boolean animate) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (animate) {
            ft.setCustomAnimations(R.animator.slide_up, R.animator.slide_down);
        }
        ft.replace(R.id.container, newFragment).commit();
    }

    private void setupDrawer() {
        final SetupNavigationDrawer setupDrawer =
                new SetupNavigationDrawer(drawerController, R.id.navigation_drawer,
                        (DrawerLayout) findViewById(R.id.drawer_layout));
        EventBus.getDefault().post(setupDrawer);
    }

    private class EventManager {

        public void onEvent(NavigationDrawerItemClicked event) {
            replaceContent(drawerController.getFragment(event.getGroup(),
                    event.getChild()), false);
        }

        public void onEvent(Event.ShowCart event) {
            replaceContent(new OrderMenuFragment(), false);
        }

        public void onEvent(Event.CatalogueRefreshed event) {
            Log.i(TAG, "Showing Menu");
            replaceContent(new OrderMenuFragment(), false);
        }
    }
}