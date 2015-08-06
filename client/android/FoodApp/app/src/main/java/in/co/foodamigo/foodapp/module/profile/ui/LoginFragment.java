package in.co.foodamigo.foodapp.module.profile.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.SignInButton;

import in.co.foodamigo.foodapp.R;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.foodapp.module.profile.infra.SigninEvent;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        SignInButton signIn = (SignInButton) v.findViewById(R.id.btnSignIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SigninEvent());
            }
        });
        return v;
    }
}
