package domain;

import db.Conn;
import db.DAL;
import model.*;

import java.util.List;

/**
 * Created by Niki on 2016-11-02.
 *
 * @author Niki
 */
public class Facade {

    private static Facade facade;

    public static Facade getFacade() {
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

    public List<Building> getBuildings(Org org) {
        DAL dal = new DAL(Conn.get());
        return dal.getBuildings(org);
    }

    public List<Building> getBuildings(Org org, int count) {
        DAL dal = new DAL(Conn.get());
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

    public List<User> getUsers(Org org) {
        DAL dal = new DAL(Conn.get());
        return dal.getUsers(org);
    }

    public List<User> getUsers(Org org, int count) {
        DAL dal = new DAL(Conn.get());
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

    public Image getImage(int id) {
        DAL dal = new DAL(Conn.get());
        return dal.getImage(id);
    }

    public List<Image> getImages() {
        DAL dal = new DAL(Conn.get());
        return dal.getImages();
    }

    public List<Image> getImages(int buildingId) {
        DAL dal = new DAL(Conn.get());
        Building b = new Building();
        b.setId(buildingId);
        return dal.getImages(b);
    }

    public List<Image> getImages(int buildingId, int count) {
        DAL dal = new DAL(Conn.get());
        Building b = new Building();
        b.setId(buildingId);
        return dal.getImages(b, count);
    }

    public List<Image> getImages(Building b) {
        DAL dal = new DAL(Conn.get());
        return dal.getImages(b);
    }

    public List<Image> getImages(Building b, int count) {
        DAL dal = new DAL(Conn.get());
        return dal.getImages(b, count);
    }

    public int insertImage(Image i) {
        DAL dal = new DAL(Conn.get());
        return dal.insertImage(i);
    }

    public boolean updateImage(Image i) {
        DAL dal = new DAL(Conn.get());
        return dal.updateImage(i);
    }

    public Document getDocument(int id) {
        DAL dal = new DAL(Conn.get());
        return dal.getDocument(id);
    }

    public List<Document> getDocuments() {
        DAL dal = new DAL(Conn.get());
        return dal.getDocuments();
    }

    public List<Document> getDocuments(int buildingId) {
        DAL dal = new DAL(Conn.get());
        Building b = new Building();
        b.setId(buildingId);
        return dal.getDocuments(b);
    }

    public List<Document> getDocuments(int buildingId, int count) {
        DAL dal = new DAL(Conn.get());
        Building b = new Building();
        b.setId(buildingId);
        return dal.getDocuments(b, count);
    }

    public List<Document> getDocuments(Building b) {
        DAL dal = new DAL(Conn.get());
        return dal.getDocuments(b);
    }

    public List<Document> getDocuments(Building b, int count) {
        DAL dal = new DAL(Conn.get());
        return dal.getDocuments(b, count);
    }

    public int insertDocument(Document d) {
        DAL dal = new DAL(Conn.get());
        return dal.insertDocument(d);
    }

    public boolean updateDocument(Document d) {
        DAL dal = new DAL(Conn.get());
        return dal.updateDocument(d);
    }

    public Report getReport(int id) {
        DAL dal = new DAL(Conn.get());
        return dal.getReport(id);
    }

    public List<Report> getReports() {
        DAL dal = new DAL(Conn.get());
        return dal.getReports();
    }

    public List<Report> getReports(int buildingId) {
        DAL dal = new DAL(Conn.get());
        Building b = new Building();
        b.setId(buildingId);
        return dal.getReports(b);
    }

    public List<Report> getReports(int buildingId, int count) {
        DAL dal = new DAL(Conn.get());
        Building b = new Building();
        b.setId(buildingId);
        return dal.getReports(b, count);
    }

    public List<Report> getReports(Building b) {
        DAL dal = new DAL(Conn.get());
        return dal.getReports(b);
    }

    public List<Report> getReports(Building b, int count) {
        DAL dal = new DAL(Conn.get());
        return dal.getReports(b, count);
    }

    public int insertReport(Report d) {
        DAL dal = new DAL(Conn.get());
        return dal.insertReport(d);
    }

    public boolean updateReport(Report d) {
        DAL dal = new DAL(Conn.get());
        return dal.updateReport(d);
    }

}
