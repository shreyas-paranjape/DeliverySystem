package in.co.foodamigo.foodapp.module.profile.view.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import in.co.foodamigo.foodapp.BR;

public class CustomerViewModel extends BaseObservable {

    private String mobileNumber;
    private String userName;
    private String address;


    @Bindable
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        notifyPropertyChanged(BR.mobileNumber);
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddresses(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }
}
