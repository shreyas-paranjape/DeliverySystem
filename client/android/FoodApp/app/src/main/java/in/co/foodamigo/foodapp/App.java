package in.co.foodamigo.foodapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    public static final String TAG = App.class.toString();

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
        //experimentWithRealm();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

   /* private void experimentWithRealm() {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        for (int i = 0; i < 5; i++) {
            getProduct(i, getProductCategory(i, getRootCategory(i, null)));
        }
        realm.commitTransaction();

        Log.i(TAG, "Products :" + Arrays.toString(realm.allObjects(Product.class).toArray()));
        Log.i(TAG, "Product Categories :" + Arrays.toString(
                realm.allObjects(ProductCategory.class).toArray()));
    }

    private Product getProduct(int index, ProductCategory category) {
        Product product = Realm.getDefaultInstance().createObject(Product.class);
        product.setName("product : " + index);
        //product.setProductCategory(category);
        return product;
    }

    private ProductCategory getRootCategory(int index, ProductCategory parent) {
        ProductCategory category = Realm.getDefaultInstance().createObject(ProductCategory.class);
        category.setName("root " + index);
        category.setParent(parent);
        return category;
    }

    private ProductCategory getProductCategory(int index, ProductCategory parent) {
        ProductCategory category = Realm.getDefaultInstance().createObject(ProductCategory.class);
        category.setName("product category " + index);
        category.setParent(parent);
        return category;
    }*/


}
