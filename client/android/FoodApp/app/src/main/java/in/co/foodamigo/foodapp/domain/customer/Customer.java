package in.co.foodamigo.foodapp.domain.customer;

import in.co.foodamigo.foodapp.domain.common.Address;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Customer extends RealmObject implements Serializable {

    private static final long serialVersionUID = 1;

    private long id;
    private String lastName;
    private String firstName;
    private String phone;
    private String email;
    private String userName;
    private String password;
    private RealmList<Address> addresses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RealmList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(RealmList<Address> addresses) {
        this.addresses = addresses;
    }
}
