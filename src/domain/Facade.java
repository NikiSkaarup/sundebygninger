package domain;

import db.Conn;
import db.DAL;
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
        DAL dal = new DAL(Conn.get());
        return dal.getBuilding(id);
    }

    public List<Building> getBuildings(int orgId) {
        DAL dal = new DAL(Conn.get());
        Org org = new Org();
        org.setId(orgId);
        return dal.getBuildings(org);
    }

    public List<Building> getBuildings(int orgId, int count) {
        DAL dal = new DAL(Conn.get());
        Org org = new Org();
        org.setId(orgId);
        return dal.getBuildings(org, count);
    }

    public int insertBuilding(Building b) {
        DAL dal = new DAL(Conn.get());
        return dal.insertBuilding(b);
    }

    public boolean updateBuilding(Building b) {
        DAL dal = new DAL(Conn.get());
        return dal.updateBuilding(b);
    }

    public User getUser(int id) {
        DAL dal = new DAL(Conn.get());
        return dal.getUser(id);
    }

    /**
     * get user by Email and Password in order to login
     * @param e email
     * @param p password
     * @return user
     */
    public User getUserLogin(String e, String p) {
        DAL dal = new DAL(Conn.get());
        return dal.getUserLogin(e, p);
    }

    public List<User> getUsers() {
        DAL dal = new DAL(Conn.get());
        return dal.getUsers();
    }

    public List<User> getUsers(int orgId) {
        DAL dal = new DAL(Conn.get());
        Org org = new Org();
        org.setId(orgId);
        return dal.getUsers(org);
    }

    public List<User> getUsers(int orgId, int count) {
        DAL dal = new DAL(Conn.get());
        Org org = new Org();
        org.setId(orgId);
        return dal.getUsers(org, count);
    }

    public int insertUser(User u) {
        DAL dal = new DAL(Conn.get());
        return dal.insertUser(u);
    }

    public boolean updateUser(User u) {
        DAL dal = new DAL(Conn.get());
        return dal.updateUser(u);
    }

}
