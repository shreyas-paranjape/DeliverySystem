package in.co.foodamigo.monitoringapp.module.profile.model;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.io.Serializable;

import in.co.foodamigo.monitoringapp.module.common.model.Address;
import in.co.foodamigo.monitoringapp.module.profile.infra.util.AddressListParcelConverter;
import io.realm.CustomerRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel(implementations = {CustomerRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Customer.class})
public class Customer extends RealmObject implements Serializable {

    private static final long serialVersionUID = 1;

    @PrimaryKey
    private long id;

    private String mobileNumber;
    private String userName;
    private RealmList<Address> addresses = new RealmList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RealmList<Address> getAddresses() {
        return addresses;
    }

    @ParcelPropertyConverter(AddressListParcelConverter.class)
    public void setAddresses(RealmList<Address> addresses) {
        this.addresses = addresses;
    }
}
