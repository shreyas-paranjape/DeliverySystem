package in.co.foodamigo.foodapp.module.catalogue.view.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import common.infra.network.NetworkManager;
import common.module.common.infra.NetworkConnectedEvent;
import common.module.common.infra.NetworkErrorEvent;
import common.module.common.ui.AbstractDrawerActivity;
import common.module.common.ui.NoNetworkFragment;
import common.module.common.ui.view.SlidingTabStripView;
import common.module.drawer.infra.DrawerItemClickedEvent;
import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.catalogue.infra.net.ProductClient;
import in.co.foodamigo.foodapp.module.catalogue.model.ProductCategory;
import in.co.foodamigo.foodapp.module.catalogue.view.widget.CategoryAdapter;
import in.co.foodamigo.foodapp.module.catalogue.view.widget.CategoryPagerAdapter;
import in.co.foodamigo.foodapp.module.profile.view.app.ProfileActivity;

public class CatalogueActivity extends AbstractDrawerActivity {

    private static final String TAG = CatalogueActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bind view
       /* Button chkOut = (Button) findViewById(R.id.btn_checkout);
        chkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceContent(new CartFragment(), true);
            }
        });*/

        //check network status and connectivity
        if (new NetworkManager(this).isConnected()) {
            // Refresh data
            new ProductClient(CatalogueActivity.this).fetchAndSaveProductCatalogue();
        } else {
            replaceContent(new NoNetworkFragment());
        }
    }

    @Override
    protected boolean confirmExit() {
        return false;
    }

    @Override
    protected boolean hasParent() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_home;
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

    private class EventManager {

        public void onEvent(DrawerItemClickedEvent event) {
            if (!isDestroyed()) {
                replaceContent(
                        drawerController.getFragment(event.getGroup(), event.getChild()),
                        false);
            }
        }

        public void onEvent(ProfileActivity.ShowCartEvent event) {
            replaceContent(new OrderMenuFragment());
        }

        public void onEvent(CatalogueRefreshedEvent event) {
            Log.i(TAG, "Showing Menu");
            if (!isDestroyed()) {
                replaceContent(new OrderMenuFragment());
                setupDrawer();
                setupToolbar();
            }
        }

        public void onEvent(NetworkConnectedEvent event) {
            if (!isDestroyed()) {
                new ProductClient(CatalogueActivity.this).fetchAndSaveProductCatalogue();
            }
        }

        public void onEvent(NetworkErrorEvent event) {
            if (!isDestroyed()) {
                replaceContent(new NoNetworkFragment());
            }
        }
    }

    public static class CategoryFragment extends Fragment {

        private ProductCategory parent;

        @Override
        public void setArguments(Bundle args) {
            super.setArguments(args);
            parent = Parcels.unwrap(args.getParcelable("parent"));
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_category, container, false);
            initListView(rootView);
            return rootView;
        }

        private void initListView(View rootView) {
            RecyclerView productsRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_category);
            productsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            productsRecyclerView.setAdapter(new CategoryAdapter(getActivity(), parent));

            /*final ExpandableListView listView =
                    (ExpandableListView) rootView.findViewById(R.id.elv_category);
            CategoryAdapter adapter = new CategoryAdapter(getActivity(), parent);
            listView.setAdapter(adapter);
            listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                int previousItem = -1;

                @Override
                public void onGroupExpand(int groupPosition) {
                    if (groupPosition != previousItem)
                        listView.collapseGroup(previousItem);
                    previousItem = groupPosition;
                }
            });*/
        }
    }

    public static class OrderMenuFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_order, container, false);
            initView(rootView);
            return rootView;
        }

        private void initView(View rootView) {
            ViewPager mPager = initPager(rootView);
            initTabs(rootView, mPager);
        }

        private void initTabs(View rootView, ViewPager mPager) {
            SlidingTabStripView mSlidingTabStripView = (SlidingTabStripView) rootView.findViewById(R.id.tabs);
            mSlidingTabStripView.setViewPager(mPager);
            mSlidingTabStripView.setCustomTabColorizer(new SlidingTabStripView.TabColorizer() {
                @Override
                public int getIndicatorColor(int position) {
                    return getResources().getColor(R.color.second_color);
                }

                @Override
                public int getDividerColor(int position) {
                    return getResources().getColor(R.color.second_color);
                }
            });
        }

        @NonNull
        private ViewPager initPager(View rootView) {
            PagerAdapter mPagerAdapter = new CategoryPagerAdapter(getActivity().getFragmentManager());
            ViewPager mPager = (ViewPager) rootView.findViewById(R.id.pager);
            mPager.setAdapter(mPagerAdapter);
            mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    getActivity().invalidateOptionsMenu();
                }
            });
            return mPager;
        }

    }

    public static class CatalogueRefreshedEvent {
    }
}