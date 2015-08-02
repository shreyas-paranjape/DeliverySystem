package in.co.foodamigo.foodapp.ui.component.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.ui.adapter.CategoryPagerAdapter;
import in.co.foodamigo.foodapp.ui.view.SlidingTabLayout;

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
        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.tabs);
        mSlidingTabLayout.setViewPager(mPager);
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
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

   /* private PagerSlidingTabStrip initTabs(View rootView, ViewPager mPager) {
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabs);
        tabs.setViewPager(mPager);
        return tabs;
    }*/

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