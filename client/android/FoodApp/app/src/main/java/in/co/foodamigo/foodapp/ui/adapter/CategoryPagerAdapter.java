package in.co.foodamigo.foodapp.ui.adapter;

import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.List;

import in.co.foodamigo.foodapp.domain.product.ProductCategory;
import in.co.foodamigo.foodapp.ui.component.fragment.CategoryFragment;
import io.realm.Realm;
import io.realm.RealmQuery;


public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    private final List<ProductCategory> rootCategories;

    public CategoryPagerAdapter(android.app.FragmentManager fm) {
        super(fm);
        RealmQuery<ProductCategory> query = Realm.getDefaultInstance().where(ProductCategory.class);
        query.isNull("subCategories");
        rootCategories = query.findAll();
    }

    @Override
    public android.app.Fragment getItem(int position) {
        return new CategoryFragment();
    }

    @Override
    public int getCount() {
        return rootCategories.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return rootCategories.get(position).getName();
    }
}