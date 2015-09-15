package in.co.foodamigo.foodapp.module.help.view.model;

import java.io.Serializable;

public class Tutorial implements Serializable {

    private String title;
    private String description;
    private String url;
    private boolean showHome = false;

    public Tutorial() {
    }

    public Tutorial(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public Tutorial(String title, String description, String url, boolean showHome) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.showHome = showHome;
    }

    public boolean isShowHome() {
        return showHome;
    }

    public void setShowHome(boolean showHome) {
        this.showHome = showHome;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
