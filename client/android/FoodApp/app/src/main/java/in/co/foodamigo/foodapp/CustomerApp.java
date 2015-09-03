package in.co.foodamigo.foodapp;

import common.app.RealmApp;
import in.co.foodamigo.foodapp.module.catalogue.model.Product;
import in.co.foodamigo.foodapp.module.catalogue.model.ProductCategory;
import io.realm.Realm;

public class CustomerApp extends RealmApp {

    @Override
    public void onCreate() {
        super.onCreate();
        createDemoData();
    }

    private void createDemoData() {
        Realm objectStore = Realm.getDefaultInstance();
        objectStore.beginTransaction();

        // GOAN
        ProductCategory goan = objectStore.createObject(ProductCategory.class);
        goan.setId(1);
        goan.setName("goan");

        Product vindalo = objectStore.createObject(Product.class);
        vindalo.setId(1);
        vindalo.setName("Vindalo");
        vindalo.setRate(100);

        Product xacuti = objectStore.createObject(Product.class);
        xacuti.setId(2);
        xacuti.setName("Xacuti");
        xacuti.setRate(100);

        goan.getProducts().add(vindalo);
        goan.getProducts().add(xacuti);

        ProductCategory oriental = objectStore.createObject(ProductCategory.class);
        oriental.setId(2);
        oriental.setName("oriental");

        ProductCategory food = objectStore.createObject(ProductCategory.class);
        food.setId(3);
        food.setName("food");

        food.getSubCategories().add(goan);
        food.getSubCategories().add(oriental);

        objectStore.commitTransaction();
    }

}