package in.co.foodamigo.foodapp.module.profile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.co.foodamigo.foodapp.events.Event;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.databinding.FragmentMobileBinding;

public class MobileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentMobileBinding binding = FragmentMobileBinding.inflate(inflater);
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new Event.MobileNumberEntered());
            }
        });
        return binding.getRoot();
    }
}
