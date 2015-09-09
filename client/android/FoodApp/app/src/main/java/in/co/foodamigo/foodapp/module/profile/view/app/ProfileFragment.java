package in.co.foodamigo.foodapp.module.profile.view.app;

import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import in.co.foodamigo.foodapp.databinding.FragmentProfileBinding;
import in.co.foodamigo.foodapp.module.common.model.Address;
import in.co.foodamigo.foodapp.module.profile.model.Customer;
import in.co.foodamigo.foodapp.util.GpsUtil;
import io.realm.Realm;

public class ProfileFragment extends Fragment {

    private static final String TAG = ProfileFragment.class.getName();
    private FragmentProfileBinding viewBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding = FragmentProfileBinding.inflate(inflater);
        viewBinding.btnUseCurrentLocation.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG, "In use location click");
                        final LocationManager locationManager =
                                (LocationManager) getActivity().getSystemService(
                                        Context.LOCATION_SERVICE);

                        final LocationListener locationListener = new LocationListener() {
                            public void onLocationChanged(Location location) {
                                makeUseOfNewLocation(location);
                                locationManager.removeUpdates(this);
                            }

                            public void onStatusChanged(String provider, int status,
                                                        Bundle extras) {
                            }

                            public void onProviderEnabled(String provider) {
                            }

                            public void onProviderDisabled(String provider) {
                            }
                        };

                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

                        GpsUtil.displayPromptForEnablingGPS(getActivity());

                    }
                }
        );
        viewBinding.btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signup();
                    }
                }
        );
        return viewBinding.getRoot();
    }

    private void signup() {
        Log.i(TAG, "Customer : " +
                "name : " + viewBinding.etUsername.getText() +
                "mobileNumber : " + viewBinding.etMobileNumber.getText() +
                "Address : " + viewBinding.etAddress.getText());
        saveCustomer();
    }

    private void saveCustomer() {
        Realm.getDefaultInstance().beginTransaction();
        Customer customer = Realm.getDefaultInstance().createObject(Customer.class);
        customer.setId(new Random().nextLong());
        customer.setUserName(viewBinding.etUsername.getText().toString());
        customer.setMobileNumber(viewBinding.etMobileNumber.getText().toString());
        Address newAddress = new Address();
        newAddress.setLineOne(viewBinding.etAddress.getText().toString());
        customer.getAddresses().add(newAddress);
        Realm.getDefaultInstance().cancelTransaction();
    }


    private void makeUseOfNewLocation(Location location) {
        Log.i(TAG, "Current location : " +
                location.getLatitude() + " : " +
                location.getLongitude());

    }
}
