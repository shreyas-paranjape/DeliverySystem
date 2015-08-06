package in.co.foodamigo.foodapp.module.drawer.infra;


import android.support.v4.widget.DrawerLayout;

import in.co.foodamigo.foodapp.module.drawer.domain.DrawerController;

public class SetupDrawerEvent {

    private final DrawerController controller;
    private final int fragmentId;
    private final DrawerLayout drawerLayout;

    public SetupDrawerEvent(DrawerController controller,
                            int fragmentId, DrawerLayout drawerLayout) {
        this.controller = controller;
        this.fragmentId = fragmentId;
        this.drawerLayout = drawerLayout;
    }

    public int getFragmentId() {
        return fragmentId;
    }


    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public DrawerController getController() {
        return controller;
    }

}
