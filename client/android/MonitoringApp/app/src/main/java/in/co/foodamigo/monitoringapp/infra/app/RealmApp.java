package in.co.foodamigo.monitoringapp.infra.app;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config =
                new RealmConfiguration.Builder(this)
                        //.deleteRealmIfMigrationNeeded()
                        .inMemory()
                        .build();
        Realm.setDefaultConfiguration(config);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Realm.getDefaultInstance().close();
    }
}
