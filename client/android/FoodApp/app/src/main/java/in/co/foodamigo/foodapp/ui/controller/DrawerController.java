package in.co.foodamigo.foodapp.ui.controller;

import android.app.Fragment;
import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DrawerController {

    private final Context context;
    private final List<String> groupList;
    private final Map<String, List<String>> childMapping;

    public DrawerController(Context context) {
        this.context = context;
        groupList = new ArrayList<>();
        childMapping = new HashMap<>();
        prepareListData();
    }

    public List<String> getGroupList() {
        return groupList;
    }

    public Map<String, List<String>> getChildMapping() {
        return childMapping;
    }

    public Fragment getFragment(int group, int child) {
        return null;
    }

    private void prepareListData() {

        groupList.add("Order info");
        groupList.add("Need help?");
        groupList.add("Terms and conditions");

        List<String> orderChildren = new ArrayList<>();
        orderChildren.add("Past order");
        orderChildren.add("Order status");

        List<String> helpChildren = new ArrayList<>();
        helpChildren.add("Call us");
        helpChildren.add("Email us");

        List<String> termChildren = new ArrayList<>();
        termChildren.add("Like this app");
        termChildren.add("Write a review");
        termChildren.add("Rate us");

        childMapping.put("Order info", orderChildren);
        childMapping.put("Need help?", helpChildren);
        childMapping.put("Terms and conditions", termChildren);
    }
}
