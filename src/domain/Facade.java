package domain;

import db.Conn;
import db.Dal;
import model.Building;
import model.User;
import sun.font.BidiUtils;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Niki on 2016-11-02.
 *
 * @author Niki
 */
public class Facade {

    private static Facade facade;

    public Facade() {
    }

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

    public List<Building> getBuildings() {
        Dal dal = new Dal(Conn.get());
        return dal.getBuildings();
    }

    public List<Building> getBuildings(int count) {
        Dal dal = new Dal(Conn.get());
        return dal.getBuildings(count);
    }

    public boolean updateBuilding(Building b) {
        Dal dal = new Dal(Conn.get());
        return dal.updateBuilding(b);
    }

    public int insertBuilding(Building b) {
        Dal dal = new Dal(Conn.get());
        return dal.insertBuilding(b);
    }

    public User getUser(int id) {
        Dal dal = new Dal(Conn.get());
        return dal.getUser(id);
    }

    public User getUserLogin(String email, String password) {
        Dal dal = new Dal(Conn.get());
        return dal.getUserLogin(email, password);
    }

    public List<User> getUsers() {
        Dal dal = new Dal(Conn.get());
        return dal.getUsers();
    }

    public List<User> getUsers(int count) {
        Dal dal = new Dal(Conn.get());
        return dal.getUsers(count);
    }

}
