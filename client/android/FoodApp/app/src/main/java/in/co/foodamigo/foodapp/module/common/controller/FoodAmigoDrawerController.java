package in.co.foodamigo.foodapp.module.common.controller;

import android.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import in.co.foodamigo.foodapp.module.common.model.Item;
import in.co.foodamigo.foodapp.module.common.model.ItemGroup;
import in.co.foodamigo.foodapp.module.catalogue.view.app.MenuFragment;


public class FoodAmigoDrawerController implements DrawerController {

    private final List<ItemGroup> drawerItemGroups;

    public FoodAmigoDrawerController() {
        drawerItemGroups = new ArrayList<>();
        prepareListData();
    }

    @Override
    public Fragment getFragment(int group, int child) {
        return drawerItemGroups.get(group)
                .getItems().get(child)
                .getDisplayFragment();
    }

    @Override
    public List<ItemGroup> getDrawerItemGroups() {
        return drawerItemGroups;
    }

    private void prepareListData() {

        ItemGroup home = new ItemGroup("Home www");
        home.getItems().add(new Item("Today's menu") {
            @Override
            public Fragment getDisplayFragment() {
                return new MenuFragment();
            }
        });

        ItemGroup orderInfo = new ItemGroup("order info");
        orderInfo.getItems().add(new Item("Past orders") {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        orderInfo.getItems().add(new Item("Order status") {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });

        ItemGroup needHelp = new ItemGroup("Need Help?");
        needHelp.getItems().add(new Item("Call us") {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        needHelp.getItems().add(new Item("Email us") {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });

        ItemGroup terms = new ItemGroup("Terms and conditions");
        terms.getItems().add(new Item("Like this common.app") {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        terms.getItems().add(new Item("Write a review") {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        terms.getItems().add(new Item("Rate us") {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });

        drawerItemGroups.add(home);
        drawerItemGroups.add(orderInfo);
        drawerItemGroups.add(needHelp);
        drawerItemGroups.add(terms);
    }
}
