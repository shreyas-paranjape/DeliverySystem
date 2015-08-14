package common.module.drawer.ui;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import common.R;
import common.module.drawer.domain.DrawerController;
import common.module.drawer.infra.DrawerItemClickedEvent;
import common.module.drawer.infra.SetupDrawerEvent;
import common.module.drawer.ui.adapter.NavigationDrawerAdapter;
import de.greenrobot.event.EventBus;

/**
 * "https://developer.android.com/design/patterns/navigation-drawer.html#Interaction"
 */
public class NavigationDrawerFragment extends Fragment {

    //private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private DrawerController drawerController;
    ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;
    private ExpandableListView mDrawerListView;

    public NavigationDrawerFragment() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(
                R.layout.fragment_navigation_drawer, container, false);
        mDrawerListView = (ExpandableListView) v.findViewById(R.id.elv_drawer);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item)
                || super.onOptionsItemSelected(item);
    }

    public void onEvent(SetupDrawerEvent event) {
        if (getActivity() != null) {
            mFragmentContainerView = getActivity().findViewById(event.getFragmentId());
            mDrawerLayout = event.getDrawerLayout();
            drawerController = event.getController();
            initListView(mDrawerListView);
            mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
            mDrawerToggle = getActionBarDrawerToggle();
            mDrawerLayout.post(new Runnable() {
                @Override
                public void run() {
                    mDrawerToggle.syncState();
                }
            });
            mDrawerLayout.setDrawerListener(mDrawerToggle);
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void initListView(ExpandableListView mDrawerListView) {
        mDrawerListView.setAdapter(
                new NavigationDrawerAdapter(getActivity(), drawerController.getDrawerItemGroups()));
        for (int i = 0; i < drawerController.getDrawerItemGroups().size(); i++) {
            mDrawerListView.expandGroup(i);
        }
        mDrawerListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });
        mDrawerListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                selectItem(groupPosition, childPosition);
                return true;
            }
        });
    }


    private void selectItem(int group, int child) {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        EventBus.getDefault().post(new DrawerItemClickedEvent(group, child));
    }

    @NonNull
    private ActionBarDrawerToggle getActionBarDrawerToggle() {
        return new ActionBarDrawerToggle(
                getActivity(),
                mDrawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }
                getActivity().invalidateOptionsMenu();
            }
        };
    }
}
