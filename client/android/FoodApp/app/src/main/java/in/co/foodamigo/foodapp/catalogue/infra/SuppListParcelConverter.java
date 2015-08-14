package in.co.foodamigo.foodapp.catalogue.infra;

import android.os.Parcel;

import org.parceler.Parcels;

import common.util.RealmListParcelConverter;
import in.co.foodamigo.foodapp.catalogue.domain.Supplier;

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
