package in.co.foodamigo.foodapp.ui.component.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.events.Event;
import in.co.foodamigo.foodapp.events.NavigationDrawerItemClicked;
import in.co.foodamigo.foodapp.events.SetupNavigationDrawer;
import in.co.foodamigo.foodapp.service.rest.ProductClient;
import in.co.foodamigo.foodapp.ui.component.fragment.CartFragment;
import in.co.foodamigo.foodapp.ui.component.fragment.OrderMenuFragment;
import in.co.foodamigo.foodapp.ui.controller.DrawerController;

public class Home extends AppCompatActivity {

    private static final String TAG = Home.class.getName();
    private DrawerController drawerController;
    private EventManager eventManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button chkOut = (Button) findViewById(R.id.btn_checkout);
        chkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceContent(new CartFragment(), true);
            }
        });
        drawerController = new DrawerController(this);
        eventManager = new EventManager();
        setupToolbar();
        setupDrawer();
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


        EventManager() {
            EventBus.getDefault().register(this);
        }

        public void onEvent(NavigationDrawerItemClicked event) {
            replaceContent(drawerController.getFragment(event.getGroup(), event.getChild()), false);
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


//private CharSequence mTitle;
//public static final String AUTHORITY = "in.co.foodamigo.foodapp.provider";
//public static final String ACCOUNT_TYPE = "foodamigo.co.in";
//public static final String ACCOUNT = "FoodAmigo";
//Account mAccount;


// mTitle = getTitle();
// mAccount = CreateSyncAccount(this);

//public static Account CreateSyncAccount(Context context) {
// Create the account type and default account
//Account newAccount = new Account(
// ACCOUNT, ACCOUNT_TYPE);
// Get an instance of the Android account manager
//AccountManager accountManager =
// (AccountManager) context.getSystemService(
//ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
//if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
// } else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
//}
//return null;
//}