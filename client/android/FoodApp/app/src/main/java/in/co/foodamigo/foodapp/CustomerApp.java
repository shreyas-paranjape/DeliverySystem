package in.co.foodamigo.foodapp;

import in.co.foodamigo.foodapp.module.catalogue.model.Product;
import in.co.foodamigo.foodapp.module.catalogue.model.ProductCategory;
import in.co.foodamigo.foodapp.module.catalogue.model.Supplier;
import in.co.foodamigo.foodapp.util.FontsOverride;
import io.realm.Realm;

public class CustomerApp extends RealmApp {

    @Override
    public void onCreate() {
        super.onCreate();
        createDemoData();
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Raleway-Thin.ttf");
    }

    private void createDemoData() {
        Realm objectStore = Realm.getDefaultInstance();
        objectStore.beginTransaction();

        Supplier awesomeRest = objectStore.createObject(Supplier.class);
        awesomeRest.setId(1);
        awesomeRest.setName("My fancy rest");

        Product chickenBiryani = objectStore.createObject(Product.class);
        chickenBiryani.setId(1);
        chickenBiryani.setName("Chicken biryani");
        chickenBiryani.setDescription("Popular indian dish which is a mix of rice and well marinated " +
                "chicken which is infused with indian spices.");
        chickenBiryani.setRate(220);
        chickenBiryani.setDish_url("file:///android_asset/chick_bir.JPG");
        chickenBiryani.setProductSupplier(awesomeRest);

        Product butterChicken = objectStore.createObject(Product.class);
        butterChicken.setId(2);
        butterChicken.setName("Butter chicken");
        butterChicken.setDescription("North indian dish has a base of tomato puree and cream. " +
                "Ground cashews are added to enhance the flavour and texture");
        butterChicken.setRate(220);
        butterChicken.setDish_url("file:///android_asset/but_chick.jpg");
        butterChicken.setProductSupplier(awesomeRest);

        Product vindalo = objectStore.createObject(Product.class);
        vindalo.setId(3);
        vindalo.setName("Chicken Vindalo");
        vindalo.setDescription("Goan traditional dish with chicken cooked in " +
                "a spicy gravy using 13 different spices");
        vindalo.setRate(100);
        vindalo.setProductSupplier(awesomeRest);
        vindalo.setDish_url("file:///android_asset/1.jpg");

        Product xacuti = objectStore.createObject(Product.class);
        xacuti.setId(4);
        xacuti.setName("Chicken Xacuti");
        xacuti.setDescription("Goan traditional dish with chicken cooked in " +
                "a spicy gravy using 13 different spices");
        xacuti.setRate(500);
        xacuti.setProductSupplier(awesomeRest);
        xacuti.setDish_url("file:///android_asset/2.jpg");

        Product curry = objectStore.createObject(Product.class);
        curry.setId(5);
        curry.setName("Mutton Curry");
        curry.setDescription("Goan traditional dish with chicken cooked in " +
                "a spicy gravy using 13 different spices");
        curry.setRate(1000);
        curry.setProductSupplier(awesomeRest);
        curry.setDish_url("file:///android_asset/3.jpg");

        String[] categories = new String[]{"Snacks", "Goan", "Oriental", "Rice", "Breads", "Dessert"};
        ProductCategory food = objectStore.createObject(ProductCategory.class);
        food.setName("food");
        food.setId(1);
        for (int i = 0; i < categories.length; i++) {
            ProductCategory prodCat = objectStore.createObject(ProductCategory.class);
            prodCat.setId(i + 2);
            prodCat.setName(categories[i]);
            if(i == 0){
                prodCat.getProducts().add(chickenBiryani);
                prodCat.getProducts().add(butterChicken);
            }else{
                prodCat.getProducts().add(vindalo);
                prodCat.getProducts().add(curry);
            }

            //prodCat.getProducts().add(curry);
            food.getSubCategories().add(prodCat);
        }

        objectStore.commitTransaction();
    }

}