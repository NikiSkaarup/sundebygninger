package model;

/**
 * Created by Niki on 2016-12-02.
 *
 * @author Niki
 */
public class Link {
    private String name;
    private String path;

    public Link() {
    }

    public Link(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
