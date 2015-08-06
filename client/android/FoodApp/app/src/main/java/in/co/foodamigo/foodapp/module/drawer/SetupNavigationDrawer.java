package in.co.foodamigo.foodapp.module.drawer;


import android.support.v4.widget.DrawerLayout;

public class SetupNavigationDrawer {

    private final DrawerController controller;
    private final int fragmentId;
    private final DrawerLayout drawerLayout;

    public SetupNavigationDrawer(DrawerController controller,
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
