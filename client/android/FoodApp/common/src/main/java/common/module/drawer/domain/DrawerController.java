package common.module.drawer.domain;

import android.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class DrawerController {

    private final List<ItemGroup> drawerItemGroups;

    public DrawerController() {
        drawerItemGroups = new ArrayList<>();
        prepareListData();
    }

    public Fragment getFragment(int group, int child) {
        return drawerItemGroups.get(group)
                .getItems().get(child)
                .getDisplayFragment();
    }

    public List<ItemGroup> getDrawerItemGroups() {
        return drawerItemGroups;
    }

    private void prepareListData() {

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

        drawerItemGroups.add(orderInfo);
        drawerItemGroups.add(needHelp);
        drawerItemGroups.add(terms);

        /*groupList.add("Order info");
        groupList.add("Need help?");
        groupList.add("Terms and conditions");

        List<String> orderChildren = new ArrayList<>();
        orderChildren.add("Past order");
        orderChildren.add("Order status");

        List<String> helpChildren = new ArrayList<>();
        helpChildren.add("Call us");
        helpChildren.add("Email us");

        List<String> termChildren = new ArrayList<>();
        termChildren.add("Like this common.app");
        termChildren.add("Write a review");
        termChildren.add("Rate us");

        childMapping.put("Order info", orderChildren);
        childMapping.put("Need help?", helpChildren);
        childMapping.put("Terms and conditions", termChildren);*/
    }
}
