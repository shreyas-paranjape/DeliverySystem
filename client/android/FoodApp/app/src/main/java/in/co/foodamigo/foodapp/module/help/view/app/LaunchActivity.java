package in.co.foodamigo.foodapp.module.help.view.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import in.co.foodamigo.foodapp.R;
import in.co.foodamigo.foodapp.module.help.view.widget.TutorialPagerAdapter;
import me.relex.circleindicator.CircleIndicator;

public class LaunchActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        ViewPager defaultViewpager = (ViewPager) findViewById(R.id.viewpager_default);
        CircleIndicator defaultIndicator = (CircleIndicator) findViewById(R.id.indicator_default);
        TutorialPagerAdapter adapter = new TutorialPagerAdapter(getFragmentManager());
        defaultViewpager.setAdapter(adapter);
        defaultIndicator.setViewPager(defaultViewpager);

    }
}
