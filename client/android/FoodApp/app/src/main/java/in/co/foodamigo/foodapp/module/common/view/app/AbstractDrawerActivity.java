package in.co.foodamigo.foodapp.module.common.view.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.common.controller.DrawerController;
import in.co.foodamigo.foodapp.module.common.controller.FoodAmigoDrawerController;

public abstract class AbstractDrawerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(getEventManager());
        setContentView(getLayoutID());
        setupToolbar();
        if (needsDrawer()) {
            setupDrawer();
        }
        Fragment fragment = getInitialFragment();
        if (fragment != null) {
            replaceContent(fragment, false);
        }
    }

    @Override
    public void onBackPressed() {
        if (confirmExit()) {
            new AlertDialog.Builder(this)
                    .setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            AbstractDrawerActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    }).create().show();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (hasParent()) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    navigateToParent();
                   /* new AlertDialog.Builder(this)
                            .setMessage(R.string.dialog_order_cancel_message)
                            .setTitle(R.string.dialog_order_cancel_title)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            }).create().show();*/
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateToParent() {
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
            TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(upIntent)
                    .startActivities();
        } else {
            NavUtils.navigateUpTo(this, upIntent);
        }
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
                new NavigationDrawerFragment.SetupDrawerEvent(getDrawerController(), R.id.navigation_drawer,
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

    abstract protected boolean confirmExit();

    abstract protected boolean hasParent();

    abstract protected int getLayoutID();

    abstract protected Object getEventManager();

    abstract protected Fragment getInitialFragment();

    abstract protected int getFragmentContainerId();

    abstract protected boolean needsDrawer();

    protected DrawerController getDrawerController() {
        return new FoodAmigoDrawerController();
    }

}
