package in.co.foodamigo.foodapp.domain.product.supply;

import org.parceler.Parcel;

import in.co.foodamigo.foodapp.domain.common.Address;
import io.realm.RealmObject;
import io.realm.SupplierRealmProxy;

@Parcel(implementations = {SupplierRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Supplier.class})
public class Supplier extends RealmObject {

    private long id;
    private String name;
    private Address address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
