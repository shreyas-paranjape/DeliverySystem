package in.co.foodamigo.foodapp.util;

import android.os.Parcel;

import org.parceler.Parcels;

import in.co.foodamigo.foodapp.domain.product.supply.Supplier;

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
