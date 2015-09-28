package in.co.foodamigo.monitoringapp.module.common.model;

import org.parceler.Parcel;

import io.realm.AddressRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel(implementations = {AddressRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Address.class})
public class Address extends RealmObject {

    @PrimaryKey
    private long id;

    private String lineOne;
    private double latitude;
    private double longitude;

    public Address() {
    }

    public Address(String lineOne) {
        this.lineOne = lineOne;
    }

    public Address(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Address(String lineOne, double longitude, double latitude) {
        this.lineOne = lineOne;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLineOne() {
        return lineOne;
    }

    public void setLineOne(String lineOne) {
        this.lineOne = lineOne;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
