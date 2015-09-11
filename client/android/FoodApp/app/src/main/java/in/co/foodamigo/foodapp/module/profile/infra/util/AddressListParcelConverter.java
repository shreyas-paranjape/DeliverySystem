package in.co.foodamigo.foodapp.module.profile.infra.util;

import android.os.Parcel;

import org.parceler.Parcels;

import in.co.foodamigo.foodapp.module.catalogue.model.Product;
import in.co.foodamigo.foodapp.module.common.model.Address;
import in.co.foodamigo.foodapp.util.RealmListParcelConverter;

public class AddressListParcelConverter extends RealmListParcelConverter<Address> {

    @Override
    public void itemToParcel(Address input, Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public Address itemFromParcel(Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Product.class.getClassLoader()));
    }
}
