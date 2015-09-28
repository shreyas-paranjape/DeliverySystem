package in.co.foodamigo.monitoringapp.module.common.controller;

import android.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import in.co.foodamigo.monitoringapp.module.catalogue.view.app.ProductFragment;
import in.co.foodamigo.monitoringapp.module.common.model.Item;
import in.co.foodamigo.monitoringapp.module.common.model.ItemGroup;
import in.co.foodamigo.monitoringapp.module.order.view.app.OrderFragment;


public class MonitoringDrawerController implements DrawerController {

    private final List<ItemGroup> drawerItemGroups;
    private final List<Item> drawerItems;

    public MonitoringDrawerController() {
        drawerItemGroups = new ArrayList<>();
        drawerItems = new ArrayList<>();
        prepareListData();
    }

    @Override
    public Fragment getFragment(int group, int child) {
        return drawerItemGroups.get(group)
                .getItems().get(child)
                .getDisplayFragment();
    }

    @Override
    public Fragment getFragment(int position) {
        return drawerItems
                .get(position)
                .getDisplayFragment();
    }

    @Override
    public List<Item> getDrawerItems() {
        return drawerItems;
    }

    @Override
    public List<ItemGroup> getDrawerItemGroups() {
        return drawerItemGroups;
    }

    private void prepareListData() {

        drawerItems.add(new Item("Product") {
            @Override
            public Fragment getDisplayFragment() {
                return new ProductFragment();
            }
        });
        drawerItems.add(new Item("Profile") {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        drawerItems.add(new Item("Orders") {
            @Override
            public Fragment getDisplayFragment() {
                return new OrderFragment();
            }
        });
    }
}
