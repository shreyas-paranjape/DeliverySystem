package in.co.foodamigo.foodapp.module.order.view.app;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.co.foodamigo.foodapp.R;

public class CartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        /*LinearLayout cartToolBar = (LinearLayout) v.findViewById(R.id.vg_cartToolBar);
        cartToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ShowCartEvent());
            }
        });*/
        return v;
    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        float displayHeight = size.y;

        Animator animator;
        if (enter) {
            animator = ObjectAnimator.ofFloat(this, "translationY", displayHeight, 0);
        } else {
            animator = ObjectAnimator.ofFloat(this, "translationY", 0, displayHeight);
        }
        animator.setDuration(300);
        return animator;
    }

    public static class ShowCartEvent {
    }
}