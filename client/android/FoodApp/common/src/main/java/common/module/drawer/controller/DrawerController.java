package common.module.drawer.controller;


import android.app.Fragment;

import java.util.List;

import common.module.drawer.model.ItemGroup;

public interface DrawerController {

    List<ItemGroup> getDrawerItemGroups();

    Fragment getFragment(int group, int child);
}
