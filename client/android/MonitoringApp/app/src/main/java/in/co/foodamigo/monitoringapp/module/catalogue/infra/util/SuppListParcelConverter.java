package in.co.foodamigo.monitoringapp.module.catalogue.infra.util;

import android.os.Parcel;

import org.parceler.Parcels;

import in.co.foodamigo.monitoringapp.module.catalogue.model.Supplier;
import in.co.foodamigo.monitoringapp.util.RealmListParcelConverter;


public class SuppListParcelConverter extends RealmListParcelConverter<Supplier> {

    @Override
    public void itemToParcel(Supplier input, Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public Supplier itemFromParcel(Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Supplier.class.getClassLoader()));
    }
}
