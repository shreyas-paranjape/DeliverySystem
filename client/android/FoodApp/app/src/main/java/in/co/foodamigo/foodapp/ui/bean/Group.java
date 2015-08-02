package in.co.foodamigo.foodapp.ui.bean;

public class Group {
    private final int id;
    private final String name;

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
