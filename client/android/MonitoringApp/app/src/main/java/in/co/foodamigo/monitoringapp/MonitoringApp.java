package in.co.foodamigo.monitoringapp;

import in.co.foodamigo.monitoringapp.infra.app.RealmApp;
import in.co.foodamigo.monitoringapp.module.catalogue.model.Product;
import in.co.foodamigo.monitoringapp.module.catalogue.model.ProductCategory;
import in.co.foodamigo.monitoringapp.module.catalogue.model.Supplier;
import in.co.foodamigo.monitoringapp.util.FontsOverride;
import io.realm.Realm;

public class MonitoringApp extends RealmApp {

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

        Product prawnCurry = objectStore.createObject(Product.class);
        prawnCurry.setId(1);
        prawnCurry.setName("Prawn Curry");
        prawnCurry.setDescription("Traditional Goan curry has prawn cooked In a perfect blend of 13 different spices");
        prawnCurry.setRate(80);
        prawnCurry.setProductSupplier(awesomeRest);
        prawnCurry.setDish_url("file:///android_asset/Prawn curry.jpg");


        Product chickenBiryani = objectStore.createObject(Product.class);
        chickenBiryani.setId(2);
        chickenBiryani.setName("Chicken biryani");
        chickenBiryani.setDescription("Traditional Awadhi delicacy made with basmati rice, chicken, spices and onion");
        chickenBiryani.setRate(160);
        chickenBiryani.setDish_url("file:///android_asset/chick bir.JPG");
        chickenBiryani.setProductSupplier(awesomeRest);

        Product palakPaneer = objectStore.createObject(Product.class);
        palakPaneer.setId(3);
        palakPaneer.setName("Palak panner");
        palakPaneer.setDescription("A much loved vegetarian dish with curd cheese in a thick, mild spinach puree");
        palakPaneer.setRate(150);
        palakPaneer.setDish_url("file:///android_asset/palak paneer.JPG");
        palakPaneer.setProductSupplier(awesomeRest);

        Product jeeraRice = objectStore.createObject(Product.class);
        jeeraRice.setId(4);
        jeeraRice.setName("Jeera rice");
        jeeraRice.setDescription("Basmati rice cooked with cumin seeds; tastes great with most Indian curries");
        jeeraRice.setRate(100);
        jeeraRice.setDish_url("file:///android_asset/jeera rice.jpg");
        jeeraRice.setProductSupplier(awesomeRest);


        Product butterChicken = objectStore.createObject(Product.class);
        butterChicken.setId(5);
        butterChicken.setName("Butter chicken");
        butterChicken.setDescription("Tandoori chicken in creamy gravy with sour and sweet undertones; a real treat");
        butterChicken.setRate(180);
        butterChicken.setDish_url("file:///android_asset/but chick.jpg");
        butterChicken.setProductSupplier(awesomeRest);

        Product butterChickenSand = objectStore.createObject(Product.class);
        butterChickenSand.setId(6);
        butterChickenSand.setName("Butter chicken sandwich");
        butterChickenSand.setDescription("A whole new twist to Butter chicken by putting it between perfectly toasted bread");
        butterChickenSand.setRate(120);
        butterChickenSand.setProductSupplier(awesomeRest);
        butterChickenSand.setDish_url("file:///android_asset/but chick sand.jpg");


        Product lahoriChicken = objectStore.createObject(Product.class);
        lahoriChicken.setId(7);
        lahoriChicken.setName("Lahori chicken");
        lahoriChicken.setDescription("Chicken cooked in a rich spicy gravy with a heavy dose of ginger and garlic");
        lahoriChicken.setRate(180);
        lahoriChicken.setProductSupplier(awesomeRest);
        lahoriChicken.setDish_url("file:///android_asset/Lahori Chicken.jpg");


        Product chicSukha = objectStore.createObject(Product.class);
        chicSukha.setId(8);
        chicSukha.setName("Chicken Sukha");
        chicSukha.setDescription("Goan traditional dish with chicken");
        chicSukha.setRate(120);
        chicSukha.setProductSupplier(awesomeRest);
        chicSukha.setDish_url("file:///android_asset/chic sukha.jpg");

        Product dalTadka = objectStore.createObject(Product.class);
        dalTadka.setId(9);
        dalTadka.setName("Dal tadka");
        dalTadka.setDescription("Quintessential Indian yellow lentil curry; mild gravy infused with pan-fried spices");
        dalTadka.setRate(100);
        dalTadka.setProductSupplier(awesomeRest);
        dalTadka.setDish_url("file:///android_asset/dal tadka.jpg");

        Product raraMutton = objectStore.createObject(Product.class);
        raraMutton.setId(10);
        raraMutton.setName("Rara mutton");
        raraMutton.setDescription("succulent pieces of lamb cooked with mutton mince and a myriad of spices");
        raraMutton.setRate(200);
        raraMutton.setProductSupplier(awesomeRest);
        raraMutton.setDish_url("file:///android_asset/Rara Mutton.JPG");


        String[] categories = new String[]{"Snacks", "Goan", "Desi", "Oriental", "Rice", "Breads", "Dessert"};

        ProductCategory food = objectStore.createObject(ProductCategory.class);
        food.setName("food");
        food.setId(1);

        for (int i = 0; i < categories.length; i++) {
            ProductCategory prodCat = objectStore.createObject(ProductCategory.class);
            prodCat.setId(i + 2);
            prodCat.setName(categories[i]);
            prodCat.getProducts().add(prawnCurry);
            prodCat.getProducts().add(palakPaneer);
            prodCat.getProducts().add(jeeraRice);
            prodCat.getProducts().add(butterChicken);
            prodCat.getProducts().add(butterChickenSand);
            prodCat.getProducts().add(raraMutton);
            prodCat.getProducts().add(chickenBiryani);
            prodCat.getProducts().add(lahoriChicken);
            prodCat.getProducts().add(chicSukha);
            prodCat.getProducts().add(dalTadka);
            food.getSubCategories().add(prodCat);
        }

        objectStore.commitTransaction();
    }

}