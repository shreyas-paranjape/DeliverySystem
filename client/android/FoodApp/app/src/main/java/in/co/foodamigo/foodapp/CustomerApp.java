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
        FontsOverride.setDefaultFont(this, "SERIF", "Raleway-Regular.ttf");
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
        chickenBiryani.setDish_url("file:///android_asset/chick bir.JPG");
        chickenBiryani.setProductSupplier(awesomeRest);

        Product butterChicken = objectStore.createObject(Product.class);
        butterChicken.setId(2);
        butterChicken.setName("Butter chicken");
        butterChicken.setDescription("North indian dish has a base of tomato puree and cream. " +
                "Ground cashews are added to enhance the flavour and texture");
        butterChicken.setRate(220);
        butterChicken.setDish_url("file:///android_asset/but chick.jpg");
        butterChicken.setProductSupplier(awesomeRest);


        Product prawnCurry = objectStore.createObject(Product.class);
        prawnCurry.setId(3);
        prawnCurry.setName("Prawn Curry");
        prawnCurry.setDescription("Goan traditional dish with prawn cooked in " +
                "a spicy gravy using 13 different spices");
        prawnCurry.setRate(1000);
        prawnCurry.setProductSupplier(awesomeRest);
        prawnCurry.setDish_url("file:///android_asset/Prawn curry.jpg");

        Product chicSukha = objectStore.createObject(Product.class);
        chicSukha.setId(4);
        chicSukha.setName("Chicken Sukha");
        chicSukha.setDescription("Goan traditional dish with chicken");
        chicSukha.setRate(1000);
        chicSukha.setProductSupplier(awesomeRest);
        chicSukha.setDish_url("file:///android_asset/chic sukha.jpg");

        Product butterChickenSand = objectStore.createObject(Product.class);
        butterChickenSand.setId(5);
        butterChickenSand.setName("Chicken Sandwich");
        butterChickenSand.setDescription("Goan traditional dish with chicken");
        butterChickenSand.setRate(1000);
        butterChickenSand.setProductSupplier(awesomeRest);
        butterChickenSand.setDish_url("file:///android_asset/but chick sand.jpg");

        String[] categories = new String[]{"Snacks", "Goan", "Oriental", "Rice", "Breads", "Dessert"};

        ProductCategory food = objectStore.createObject(ProductCategory.class);
        food.setName("food");
        food.setId(1);

        for (int i = 0; i < categories.length; i++) {
            ProductCategory prodCat = objectStore.createObject(ProductCategory.class);
            prodCat.setId(i + 2);
            prodCat.setName(categories[i]);
            if (i == 0) {
                prodCat.getProducts().add(chickenBiryani);
                prodCat.getProducts().add(butterChicken);
                prodCat.getProducts().add(prawnCurry);
                prodCat.getProducts().add(chicSukha);
                prodCat.getProducts().add(butterChickenSand);
            } else {
                prodCat.getProducts().add(butterChickenSand);
                prodCat.getProducts().add(chicSukha);
                prodCat.getProducts().add(prawnCurry);
                prodCat.getProducts().add(butterChicken);
                prodCat.getProducts().add(chickenBiryani);
            }
            food.getSubCategories().add(prodCat);
        }

        objectStore.commitTransaction();
    }

}