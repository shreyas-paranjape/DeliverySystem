package in.co.foodamigo.foodapp.module.drawer.infra;


public class DrawerItemClickedEvent {
    private final int group;
    private final int child;

    public DrawerItemClickedEvent(int child, int group) {
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
