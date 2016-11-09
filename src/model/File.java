package model;

/**
 * Created by Niki on 2016-11-09.
 *
 * @author Niki
 */
public class File {
    private int id;
    private String name;
    private String path;
    private Building building;

    public File() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
