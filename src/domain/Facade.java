package domain;

import db.Conn;
import db.Dal;
import model.Building;
import model.Org;
import model.User;

import java.util.List;

/**
 * Created by Niki on 2016-11-02.
 *
 * @author Niki
 */
public class Facade {

    private static Facade facade;

    public Facade getFacade() {
        if (facade == null) {
            facade = new Facade();
        }
        return facade;
    }

    public Building getBuilding(int id) {
        Dal dal = new Dal(Conn.get());
        return dal.getBuilding(id);
    }

    public List<Building> getBuildings(Org org) {
        Dal dal = new Dal(Conn.get());
        return dal.getBuildings(org);
    }

    public List<Building> getBuildings(Org org, int count) {
        Dal dal = new Dal(Conn.get());
        return dal.getBuildings(org, count);
    }

    public int insertBuilding(Building b) {
        Dal dal = new Dal(Conn.get());
        return dal.insertBuilding(b);
    }

    public boolean updateBuilding(Building b) {
        Dal dal = new Dal(Conn.get());
        return dal.updateBuilding(b);
    }

    public User getUser(int id) {
        Dal dal = new Dal(Conn.get());
        return dal.getUser(id);
    }

    /**
     * get user by Email and Password in order to login
     * @param e email
     * @param p password
     * @return user
     */
    public User getUserLogin(String e, String p) {
        Dal dal = new Dal(Conn.get());
        return dal.getUserLogin(e, p);
    }

    public List<User> getUsers() {
        Dal dal = new Dal(Conn.get());
        return dal.getUsers();
    }

    public List<User> getUsers(int count) {
        Dal dal = new Dal(Conn.get());
        return dal.getUsers(count);
    }

    public List<User> getUsers(Org org) {
        Dal dal = new Dal(Conn.get());
        return dal.getUsers(org);
    }

    public List<User> getUsers(Org org, int count) {
        Dal dal = new Dal(Conn.get());
        return dal.getUsers(org, count);
    }

    public int insertUser(User u) {
        Dal dal = new Dal(Conn.get());
        return dal.insertUser(u);
    }

    public boolean updateUser(User u) {
        Dal dal = new Dal(Conn.get());
        return dal.updateUser(u);
    }

}
