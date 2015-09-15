package in.co.foodamigo.monitoringapp.module.profile.infra.util;

import android.os.Parcel;

import org.parceler.Parcels;

import in.co.foodamigo.monitoringapp.module.catalogue.model.Product;
import in.co.foodamigo.monitoringapp.module.common.model.Address;
import in.co.foodamigo.monitoringapp.util.RealmListParcelConverter;


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
