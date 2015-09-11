package in.co.foodamigo.foodapp.infra.persist;

import io.realm.Realm;
import io.realm.RealmObject;

public class RealmManager {

    public static void persist(final RealmObject dataObject) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(dataObject);
            }
        });
    }
}
