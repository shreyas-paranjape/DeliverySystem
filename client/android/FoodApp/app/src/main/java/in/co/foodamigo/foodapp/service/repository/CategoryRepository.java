package in.co.foodamigo.foodapp.service.repository;

import android.content.Context;

import java.util.List;

import in.co.foodamigo.foodapp.domain.product.ProductCategory;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class CategoryRepository {

    public static List<ProductCategory> getCategoriesWithProducts(Context context) {
        final Realm realm = Realm.getInstance(context);
        RealmQuery<ProductCategory> query = realm.where(ProductCategory.class);
        query.isNotNull("products");
        final RealmResults<ProductCategory> result = query.findAll();

        return null;
    }

    public static List<ProductCategory> getFirstLevelCategories(Context context) {
        final Realm realm = Realm.getInstance(context);
        final RealmQuery<ProductCategory> query = realm.where(ProductCategory.class);
        final RealmResults<ProductCategory> result = query.findAll();
        return null;
    }
}
