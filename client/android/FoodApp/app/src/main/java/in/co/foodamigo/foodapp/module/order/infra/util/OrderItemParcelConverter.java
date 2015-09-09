package in.co.foodamigo.foodapp.module.order.infra.util;

import android.os.Parcel;

import org.parceler.Parcels;

import in.co.foodamigo.foodapp.module.order.model.OrderItem;
import in.co.foodamigo.foodapp.util.RealmListParcelConverter;

public class OrderItemParcelConverter extends RealmListParcelConverter<OrderItem> {

    @Override
    public void itemToParcel(OrderItem input, Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public OrderItem itemFromParcel(Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(OrderItem.class.getClassLoader()));
    }

}
