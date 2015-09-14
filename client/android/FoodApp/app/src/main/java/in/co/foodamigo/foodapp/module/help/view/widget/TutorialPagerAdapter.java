package in.co.foodamigo.foodapp.module.help.view.widget;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.Random;

import in.co.foodamigo.foodapp.module.help.view.app.InitTutorialPageFragment;

public class TutorialPagerAdapter extends FragmentPagerAdapter {

    private int pagerCount = 5;
    private Random random = new Random();


    public TutorialPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return InitTutorialPageFragment.newInstance(0xff000000 | random.nextInt(0x00ffffff));
    }

    @Override
    public int getCount() {
        return pagerCount;
    }
}
