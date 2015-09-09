package in.co.foodamigo.restaurantapp.module.common.controller;


import android.app.Fragment;

import java.util.List;

import in.co.foodamigo.restaurantapp.module.common.model.ItemGroup;


public interface DrawerController {

    List<ItemGroup> getDrawerItemGroups();

    Fragment getFragment(int group, int child);
}
