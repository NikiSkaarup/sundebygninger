package model;

import java.util.List;

/**
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
public class Org {
    private int id;
    private String name;
    private String phone;
    private List<User> users;
    private List<Building> buildings;

    public Org() {
    }

    public Org(int id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List< User> getUsers() {
        return users;
    }

    public void setUsers(List< User> users) {
        this.users = users;
    }

    public List< Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List< Building> buildings) {
        this.buildings = buildings;
    }
}
