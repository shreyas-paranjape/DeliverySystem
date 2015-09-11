package in.co.foodamigo.foodapp.module.common.controller;


import android.app.Fragment;

import java.util.List;

import in.co.foodamigo.foodapp.module.common.model.Item;
import in.co.foodamigo.foodapp.module.common.model.ItemGroup;

public interface DrawerController {

    List<ItemGroup> getDrawerItemGroups();

    Fragment getFragment(int group, int child);

    Fragment getFragment(int position);

    List<Item> getDrawerItems();
}
