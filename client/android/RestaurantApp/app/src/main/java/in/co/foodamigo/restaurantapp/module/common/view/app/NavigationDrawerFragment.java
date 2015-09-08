package in.co.foodamigo.restaurantapp.module.common.view.app;

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

import de.greenrobot.event.EventBus;
import in.co.foodamigo.restaurantapp.R;
import in.co.foodamigo.restaurantapp.module.common.controller.DrawerController;
import in.co.foodamigo.restaurantapp.module.common.view.widget.NavigationDrawerAdapter;

/**
 * "https://developer.android.com/design/patterns/navigation-drawer.html#Interaction"
 */
public class NavigationDrawerFragment extends Fragment {

    private DrawerController drawerController;
    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;

    private ExpandableListView mDrawerListView;
    ActionBarDrawerToggle mDrawerToggle;

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
            mDrawerLayout.setDrawerShadow(R.mipmap.drawer_shadow, GravityCompat.START);
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

    public static class DrawerItemClickedEvent {
        private final int group;
        private final int child;

        public DrawerItemClickedEvent(int child, int group) {
            this.child = child;
            this.group = group;
        }

        public int getGroup() {
            return group;
        }

        public int getChild() {
            return child;
        }

    }

    public static class SetupDrawerEvent {

        private final DrawerController controller;
        private final int fragmentId;
        private final DrawerLayout drawerLayout;

        public SetupDrawerEvent(DrawerController controller,
                                int fragmentId, DrawerLayout drawerLayout) {
            this.controller = controller;
            this.fragmentId = fragmentId;
            this.drawerLayout = drawerLayout;
        }

        public int getFragmentId() {
            return fragmentId;
        }


        public DrawerLayout getDrawerLayout() {
            return drawerLayout;
        }

        public DrawerController getController() {
            return controller;
        }

    }
}
