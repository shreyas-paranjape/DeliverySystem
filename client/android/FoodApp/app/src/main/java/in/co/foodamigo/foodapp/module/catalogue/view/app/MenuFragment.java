package in.co.foodamigo.foodapp.module.catalogue.view.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import common.module.common.view.widget.SlidingTabStripView;
import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.catalogue.view.widget.CategoryAdapter;
import in.co.foodamigo.foodapp.module.catalogue.view.widget.CategoryPagerAdapter;

public class MenuFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        /*ViewGroup orderStatusBox = (ViewGroup) rootView.findViewById(R.id.fl_cartContainer);
        orderStatusBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ShowOrderStatusEvent());
            }
        });*/
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

    public static class ShowOrderStatusEvent {
    }

    public void onEvent(CategoryAdapter.AddProductToCartEvent event) {

    }

}
