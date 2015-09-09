package in.co.foodamigo.foodapp.module.catalogue.model;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;

public class CategoryRepository {

    public static List<ProductCategory> getCategories() {
        RealmQuery<ProductCategory> query = Realm.getDefaultInstance().where(ProductCategory.class);
        query.equalTo("name", "food");
        return query.findFirst().getSubCategories();
    }
}
