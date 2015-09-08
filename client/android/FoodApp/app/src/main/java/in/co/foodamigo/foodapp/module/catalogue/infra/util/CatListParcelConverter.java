package in.co.foodamigo.foodapp.module.catalogue.infra.util;


import android.os.Parcel;

import org.parceler.Parcels;

import in.co.foodamigo.foodapp.util.RealmListParcelConverter;
import in.co.foodamigo.foodapp.module.catalogue.model.ProductCategory;

public class CatListParcelConverter  extends RealmListParcelConverter<ProductCategory> {

    @Override
    public void itemToParcel(ProductCategory input, Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public ProductCategory itemFromParcel(Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(ProductCategory.class.getClassLoader()));
    }
}