package model;

/**
 * Created by Niki on 2016-11-16.
 *
 * @author Niki
 */
public class FileType {
    private int id;
    private String name;

    public FileType() {
    }

    public FileType(int id) {
        this.id = id;
    }

    public FileType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
