package in.co.foodamigo.foodapp.profile.domain;

import java.io.Serializable;

import in.co.foodamigo.foodapp.entity.Address;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Customer extends RealmObject implements Serializable {

    private static final long serialVersionUID = 1;

    @PrimaryKey
    private long id;

    private String mobileNumber;
    private String userName;
    private RealmList<Address> addresses;

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

    public void setAddresses(RealmList<Address> addresses) {
        this.addresses = addresses;
    }
}
