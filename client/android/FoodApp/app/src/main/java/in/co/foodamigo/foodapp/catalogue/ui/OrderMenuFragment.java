package in.co.foodamigo.foodapp.catalogue.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.catalogue.ui.adapter.CategoryPagerAdapter;
import in.co.foodamigo.foodapp.module.common.ui.view.tabstrip.SlidingTabStripView;

public class OrderMenuFragment extends Fragment {

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