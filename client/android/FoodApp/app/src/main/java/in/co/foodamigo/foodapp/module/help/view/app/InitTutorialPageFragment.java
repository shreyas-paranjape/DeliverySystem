package in.co.foodamigo.foodapp.module.help.view.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.co.foodamigo.foodapp.R;

public class InitTutorialPageFragment extends Fragment {

    private static final String ARG_COLOR = "color";
    private int mColor;

    public static InitTutorialPageFragment newInstance(int param1) {
        InitTutorialPageFragment fragment = new InitTutorialPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLOR, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public InitTutorialPageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColor = getArguments().getInt(ARG_COLOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_init_info, container, false);
        v.setBackgroundColor(mColor);
        return v;
    }
}
