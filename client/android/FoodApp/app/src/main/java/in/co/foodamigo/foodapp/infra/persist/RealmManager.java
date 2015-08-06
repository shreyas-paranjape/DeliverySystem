package in.co.foodamigo.foodapp.infra.persist;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmObject;

public class RealmManager {

    public static void persist(final Context context, final RealmObject dataObject) {
        Realm.getInstance(context).executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(dataObject);
            }
        });
    }
}
