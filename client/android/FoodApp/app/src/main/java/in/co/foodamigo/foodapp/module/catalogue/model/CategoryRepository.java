package in.co.foodamigo.foodapp.module.catalogue.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class CategoryRepository {



    public static List<ProductCategory> getParentCategories(Context context) {
        final Realm realm = Realm.getInstance(context);
        RealmQuery<ProductCategory> query = realm.where(ProductCategory.class);
        query.equalTo("name", "food");
        final RealmResults<ProductCategory> result = query.findAll();
        if (result != null && result.size() > 0) {
            return query.findAll().get(0).getSubCategories();
        }
        return new ArrayList<>();
    }

    public static List<ProductCategory> getLeafCategories(Context context) {
        final Realm realm = Realm.getInstance(context);
        final RealmQuery<ProductCategory> query = realm.where(ProductCategory.class);
        final RealmResults<ProductCategory> result = query.findAll();
        return null;
    }
}
