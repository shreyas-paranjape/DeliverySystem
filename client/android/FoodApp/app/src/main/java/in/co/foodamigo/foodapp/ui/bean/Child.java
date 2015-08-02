package in.co.foodamigo.foodapp.ui.bean;

import android.app.Fragment;

public abstract class Child {

    private final int id;
    private final String name;

    public Child(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract Fragment getDisplayFragment();
}
