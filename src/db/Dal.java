package db;

import model.Building;
import model.Org;
import model.User;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Niki on 2016-11-02.
 *
 * @author Niki
 */
public class Dal {
    private static Connection conn;

    /**
     * Connect to database, in given connection
     * @param conn
     */
    public Dal(Connection conn) {
        Dal.conn = conn;
    }

    public Building getBuilding(int id) {
        return null;
    }

    public List<Building> getBuildings(Org org) {
        return null;
    }

    public List<Building> getBuildings(Org org, int count) {
        return null;
    }

    public int insertBuilding(Building b) {
        return 0;
    }

    public boolean updateBuilding(Building b) {
        return false;
    }

    public User getUser(int id) {
        return null;
    }

    public User getUserLogin(String email, String password) {
        return null;
    }

    public List<User> getUsers() {
        return null;
    }

    public List<User> getUsers(Org org) {
        return null;
    }

    public List<User> getUsers(Org org, int count) {
        return null;
    }

    public int insertUser(User u) {
        return 0;
    }

    public boolean updateUser(User u) {
        return false;
    }
}
