package in.co.foodamigo.foodapp.module.profile.view.app;

import android.app.Fragment;
import android.app.ProgressDialog;
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
import in.co.foodamigo.foodapp.infra.persist.RealmManager;
import in.co.foodamigo.foodapp.module.common.model.Address;
import in.co.foodamigo.foodapp.module.profile.model.Customer;
import in.co.foodamigo.foodapp.util.GpsUtil;

public class ProfileFragment extends Fragment {

    private static final String TAG = ProfileFragment.class.getName();
    private FragmentProfileBinding viewBinding;
    private final Customer customer = new Customer();

    public ProfileFragment() {
        customer.setId(new Random().nextLong());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding = FragmentProfileBinding.inflate(inflater);
        final ProgressDialog dialog = createWaitingForLocationDialogue();
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
                                dialog.dismiss();
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

                        //dialog.show();
                        // new Handler().postDelayed(new Runnable() {
                        // @Override
                        //public void run() {
                        //  dialog.dismiss();
                        // }
                        //  }, 10000);
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
        saveCustomer();
        Log.i(TAG, "Customer : " +
                "name : " + customer.getUserName() +
                "mobileNumber : " + customer.getMobileNumber() +
                "Address : " + customer.getAddresses().size());
    }

    private void saveCustomer() {
        customer.setUserName(viewBinding.etUsername.getText().toString());
        customer.setMobileNumber(viewBinding.etMobileNumber.getText().toString());
        Address newAddress;
        if (customer.getAddresses().size() == 1) {
            newAddress = customer.getAddresses().get(0);
            newAddress.setLineOne(viewBinding.etAddress.getText().toString());
        } else {
            newAddress = new Address(viewBinding.etAddress.getText().toString());
            customer.getAddresses().add(newAddress);
        }
        RealmManager.persist(customer);
    }


    private void makeUseOfNewLocation(Location location) {
        customer.getAddresses().add(
                new Address(location.getLatitude(), location.getLongitude()));
        Log.i(TAG, "Current location : " +
                location.getLatitude() + " : " +
                location.getLongitude());
    }

    private ProgressDialog createWaitingForLocationDialogue() {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Waiting for location. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
