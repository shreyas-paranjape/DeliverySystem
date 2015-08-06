package in.co.foodamigo.foodapp.module.catalogue.infra;

import android.os.Parcel;

import org.parceler.Parcels;

import in.co.foodamigo.foodapp.module.catalogue.domain.Product;
import in.co.foodamigo.foodapp.util.RealmListParcelConverter;


public class ProdListParcelConverter extends RealmListParcelConverter<Product> {

    @Override
    public void itemToParcel(Product input, Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public Product itemFromParcel(Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(Product.class.getClassLoader()));
    }
}
