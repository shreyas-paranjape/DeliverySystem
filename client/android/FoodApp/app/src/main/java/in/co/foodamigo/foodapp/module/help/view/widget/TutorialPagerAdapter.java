package in.co.foodamigo.foodapp.module.help.view.widget;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import in.co.foodamigo.foodapp.module.help.view.app.InitTutorialPageFragment;
import in.co.foodamigo.foodapp.module.help.view.model.Tutorial;

public class TutorialPagerAdapter extends FragmentPagerAdapter {

    public TutorialPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return InitTutorialPageFragment.newInstance(getTutorialForPage(position));
    }

    @Override
    public int getCount() {
        return 5;
    }

    private Tutorial getTutorialForPage(int position) {
        switch (position) {
            case 0:
                return new Tutorial(
                        "Food Amigo",
                        "Welcome to your Friendly Neighbourhood Food Delivery app",
                        "file:///android_asset/tut 3.JPG");
            case 1:
                return new Tutorial(
                        "Top Restaurants, Mouthwatering Dishes",
                        "Our curated menu presents to you some classic dishes from iconic restaurants in your city, just a click away",
                        "file:///android_asset/tut 2.JPG");
            case 2:
                return new Tutorial(
                        "Easy ordering, payment on delivery",
                        "Just order your food with this easy-to-use app and pay only once hot & yummy food gets delivered to your doorstep",
                        "file:///android_asset/tut 1.JPG");
            case 3:
                return new Tutorial(
                        "Those hunger pangs wont last long",
                        "Live timer shows you the estimated time left for your delivery. If we reach later than guaranteed time, you get 20% off.",
                        "file:///android_asset/tut 4.jpg");
            case 4:
                return new Tutorial(
                        "24x7, 365 days a year",
                        "We know hunger can strike anytime. So log in anytime, any day to see what dishes we have available",
                        "file:///android_asset/tut 5.JPG", true);


        }
        return new Tutorial();
    }
}
