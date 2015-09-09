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

import in.co.foodamigo.foodapp.databinding.FragmentProfileBinding;
import in.co.foodamigo.foodapp.util.GpsUtil;

public class ProfileFragment extends Fragment {

    private static final String TAG = ProfileFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentProfileBinding viewBinding = FragmentProfileBinding.inflate(inflater);
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
        return viewBinding.getRoot();
    }

    private void makeUseOfNewLocation(Location location) {
        Log.i(TAG, "Current location : " +
                location.getLatitude() + " : " +
                location.getLongitude());

    }
}
