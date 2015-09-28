package in.co.foodamigo.monitoringapp.module.catalogue.infra.util;


import android.os.Parcel;

import org.parceler.Parcels;

import in.co.foodamigo.monitoringapp.module.catalogue.model.ProductCategory;
import in.co.foodamigo.monitoringapp.util.RealmListParcelConverter;


public class CatListParcelConverter extends RealmListParcelConverter<ProductCategory> {

    @Override
    public void itemToParcel(ProductCategory input, Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public ProductCategory itemFromParcel(Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(ProductCategory.class.getClassLoader()));
    }
}