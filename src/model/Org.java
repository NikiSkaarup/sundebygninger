package model;

import java.util.HashMap;

/**
 * Created by Niki on 2016-10-26.
 *
 * @author Niki
 */
public class Org {
    private int id;
    private String name;
    private String phone;
    private HashMap<Integer,User> users;
    private HashMap<Integer,Building> buildings;

    public Org() {
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

    public HashMap<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<Integer, User> users) {
        this.users = users;
    }

    public HashMap<Integer, Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(HashMap<Integer, Building> buildings) {
        this.buildings = buildings;
    }
}
