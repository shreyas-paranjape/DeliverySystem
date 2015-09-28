package in.co.foodamigo.monitoringapp.module.common.model;

import android.app.Fragment;

public abstract class Item {

    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public abstract Fragment getDisplayFragment();

    public String getName() {
        return name;
    }
}
