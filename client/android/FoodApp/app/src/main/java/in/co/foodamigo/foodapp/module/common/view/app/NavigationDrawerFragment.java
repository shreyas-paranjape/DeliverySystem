package in.co.foodamigo.foodapp.module.common.view.app;

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
import android.widget.AdapterView;
import android.widget.ListView;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.common.controller.DrawerController;
import in.co.foodamigo.foodapp.module.common.view.widget.NavigationDrawerListAdapter;

/**
 * "https://developer.android.com/design/patterns/navigation-drawer.html#Interaction"
 */
public class NavigationDrawerFragment extends Fragment {

    private DrawerController drawerController;
    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;

    private ListView mDrawerListView;
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
        mDrawerListView = (ListView) v.findViewById(R.id.elv_drawer);
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

    private void initListView(ListView mDrawerListView) {
        mDrawerListView.setAdapter(
                new NavigationDrawerListAdapter(
                        getActivity(),
                        R.layout.item_drawer_link,
                        drawerController.getDrawerItems()));
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
    }


    private void selectItem(int position) {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        EventBus.getDefault().post(new DrawerItemClickedEvent(position));
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
        private final int position;

        public DrawerItemClickedEvent(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
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
