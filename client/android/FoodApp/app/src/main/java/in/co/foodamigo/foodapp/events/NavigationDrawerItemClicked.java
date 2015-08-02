package in.co.foodamigo.foodapp.events;


public class NavigationDrawerItemClicked {
    private final int group;
    private final int child;

    public NavigationDrawerItemClicked(int child, int group) {
        this.child = child;
        this.group = group;
    }

    public int getGroup() {
        return group;
    }

    public int getChild() {
        return child;
    }

}
