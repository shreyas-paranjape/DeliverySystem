package in.co.foodamigo.foodapp.module.catalogue.view.widget;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentStatePagerAdapter;

import org.parceler.Parcels;

import java.util.List;

import in.co.foodamigo.foodapp.module.catalogue.model.ProductCategory;
import in.co.foodamigo.foodapp.module.catalogue.view.app.CategoryFragment;
import io.realm.Realm;
import io.realm.RealmQuery;


public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    private final List<ProductCategory> rootCategories;

    public CategoryPagerAdapter(android.app.FragmentManager fm) {
        super(fm);
        RealmQuery<ProductCategory> query = Realm.getDefaultInstance().where(ProductCategory.class);
        query.equalTo("name", "food");
        rootCategories = query.findAll().get(0).getSubCategories();
    }

    @Override
    public android.app.Fragment getItem(int position) {
        return getCategoryFragment(position);
    }

    @Override
    public int getCount() {
        return rootCategories != null ? rootCategories.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return rootCategories != null ? rootCategories.get(position).getName() : "";
    }

    @NonNull
    private CategoryFragment getCategoryFragment(int position) {
        CategoryFragment fragment = new CategoryFragment();
        if (rootCategories != null && position < rootCategories.size()) {
            Bundle args = new Bundle();
            args.putParcelable("parent", Parcels.wrap(ProductCategory.class, rootCategories.get(position)));
            fragment.setArguments(args);
        }
        return fragment;
    }

}