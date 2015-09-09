package in.co.foodamigo.restaurantapp;

import in.co.foodamigo.restaurantapp.util.FontsOverride;
import io.realm.Realm;

public class RestaurantApp extends RealmApp {

    @Override
    public void onCreate() {
        super.onCreate();
        createDemoData();
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Raleway-Thin.ttf");
    }

    private void createDemoData() {
        Realm objectStore = Realm.getDefaultInstance();
        objectStore.beginTransaction();

        objectStore.commitTransaction();
    }

}
