package model;

/**
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
public class Role {
    private int id;
    private String name;

    public Role() {
    }

    public Role(int id) {
        this.id = id;
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
}
