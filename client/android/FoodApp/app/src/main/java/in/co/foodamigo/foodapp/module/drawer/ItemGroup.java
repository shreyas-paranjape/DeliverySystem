package in.co.foodamigo.foodapp.module.drawer;

import java.util.ArrayList;
import java.util.List;

public class ItemGroup {
    private final String name;
    private final List<Item> items;

    public ItemGroup(String name) {
        this.name = name;
        items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }
}
