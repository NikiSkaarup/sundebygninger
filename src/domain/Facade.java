package domain;

import db.*;
import exceptions.PolygonException;
import model.*;

import java.util.List;

/**
 * Created by Niki on 2016-11-02.
 *
 * @author Niki
 */
public class Facade {

    private static Facade facade;

    private Facade() {
    }

    public static Facade getFacade() {
        if (facade == null) {
            facade = new Facade();
        }
        return facade;
    }

    public Building getBuilding(int id) {
        BuildingMapper mapper = new BuildingMapper(Conn.get());
        return mapper.getBuilding(id);
    }

    public List<Building> getBuildings(int orgId) {
        BuildingMapper mapper = new BuildingMapper(Conn.get());
        Org org = new Org();
        org.setId(orgId);
        return mapper.getBuildings(org);
    }

    public List<Building> getBuildings(int orgId, int count) {
        BuildingMapper mapper = new BuildingMapper(Conn.get());
        Org org = new Org();
        org.setId(orgId);
        return mapper.getBuildings(org, count);
    }

    public List<Building> getBuildings(Org org) {
        BuildingMapper mapper = new BuildingMapper(Conn.get());
        return mapper.getBuildings(org);
    }

    public List<Building> getBuildings(Org org, int count) {
        BuildingMapper mapper = new BuildingMapper(Conn.get());
        return mapper.getBuildings(org, count);
    }

    public int insertBuilding(Building b) {
        BuildingMapper mapper = new BuildingMapper(Conn.get());
        return mapper.insertBuilding(b);
    }

    public boolean updateBuilding(Building b) {
        BuildingMapper mapper = new BuildingMapper(Conn.get());
        return mapper.updateBuilding(b);
    }

    public User getUser(int id) {
        UserMapper mapper = new UserMapper(Conn.get());
        return mapper.getUser(id);
    }

    public User getUser(User user) {
        UserMapper mapper = new UserMapper(Conn.get());
        return mapper.getUser(user.getId());
    }

    /**
     * get user by Email and Password in order to login
     *
     * @param e email
     * @param p password
     * @return user
     */
    public User getUserLogin(String e, String p) {
        UserMapper mapper = new UserMapper(Conn.get());
        return mapper.getUserLogin(e, p);
    }

    public List<User> getUsers() {
        UserMapper mapper = new UserMapper(Conn.get());
        return mapper.getUsers();
    }

    public List<User> getUsers(int orgId) {
        UserMapper mapper = new UserMapper(Conn.get());
        Org org = new Org();
        org.setId(orgId);
        return mapper.getUsers(org);
    }

    public List<User> getUsers(int orgId, int count) {
        UserMapper mapper = new UserMapper(Conn.get());
        Org org = new Org();
        org.setId(orgId);
        return mapper.getUsers(org, count);
    }

    public List<User> getUsers(Org org) {
        UserMapper mapper = new UserMapper(Conn.get());
        return mapper.getUsers(org);
    }

    public List<User> getUsers(Org org, int count) {
        UserMapper mapper = new UserMapper(Conn.get());
        return mapper.getUsers(org, count);
    }

    public int insertUser(User u) {
        UserMapper mapper = new UserMapper(Conn.get());
        return mapper.insertUser(u);
    }

    public boolean updateUser(User u) {
        UserMapper mapper = new UserMapper(Conn.get());
        return mapper.updateUser(u);
    }

    public Image getImage(int id) {
        ImageMapper mapper = new ImageMapper(Conn.get());
        return mapper.getImage(id);
    }

    public List<Image> getImages() {
        ImageMapper mapper = new ImageMapper(Conn.get());
        return mapper.getImages();
    }

    public List<Image> getImages(int bId) {
        ImageMapper mapper = new ImageMapper(Conn.get());
        Building b = new Building();
        b.setId(bId);
        return mapper.getImages(b);
    }

    public List<Image> getImages(int bId, int count) {
        ImageMapper mapper = new ImageMapper(Conn.get());
        Building b = new Building();
        b.setId(bId);
        return mapper.getImages(b, count);
    }

    public List<Image> getImages(Building b) {
        ImageMapper mapper = new ImageMapper(Conn.get());
        return mapper.getImages(b);
    }

    public List<Image> getImages(Building b, int count) {
        ImageMapper mapper = new ImageMapper(Conn.get());
        return mapper.getImages(b, count);
    }

    public int insertImage(Image i) {
        ImageMapper mapper = new ImageMapper(Conn.get());
        return mapper.insertImage(i);
    }

    public boolean updateImage(Image i) {
        ImageMapper mapper = new ImageMapper(Conn.get());
        return mapper.updateImage(i);
    }

    public Document getDocument(int id) throws PolygonException {
        DocumentMapper mapper = new DocumentMapper(Conn.get());
        return mapper.getDocument(id);
    }

    public List<Document> getDocuments() throws PolygonException {
        DocumentMapper mapper = new DocumentMapper(Conn.get());
        return mapper.getDocuments();
    }

    public List<Document> getDocuments(int bId) throws PolygonException {
        DocumentMapper mapper = new DocumentMapper(Conn.get());
        Building b = new Building();
        b.setId(bId);
        return mapper.getDocuments(b);
    }

    public List<Document> getDocuments(int bId, int count) throws
            PolygonException {
        DocumentMapper mapper = new DocumentMapper(Conn.get());
        Building b = new Building();
        b.setId(bId);
        return mapper.getDocuments(b, count);
    }

    public List<Document> getDocuments(Building b) throws PolygonException {
        DocumentMapper mapper = new DocumentMapper(Conn.get());
        return mapper.getDocuments(b);
    }

    public List<Document> getDocuments(Building b, int count) throws
            PolygonException {
        DocumentMapper mapper = new DocumentMapper(Conn.get());
        return mapper.getDocuments(b, count);
    }

    public int insertDocument(Document d) throws PolygonException{
        DocumentMapper mapper = new DocumentMapper(Conn.get());
        return mapper.insertDocument(d);
    }

    public boolean updateDocument(Document d) throws PolygonException{
        DocumentMapper mapper = new DocumentMapper(Conn.get());
        return mapper.updateDocument(d);
    }

    public File getFile(int id) {
        FileMapper mapper = new FileMapper(Conn.get());
        return mapper.getFile(id);
    }

    public List<File> getFiles() {
        FileMapper mapper = new FileMapper(Conn.get());
        return mapper.getFiles();
    }

    public List<File> getFiles(int bId) {
        FileMapper mapper = new FileMapper(Conn.get());
        Building b = new Building();
        b.setId(bId);
        return mapper.getFiles(b);
    }

    public List<File> getFiles(int bId, int count) {
        FileMapper mapper = new FileMapper(Conn.get());
        Building b = new Building();
        b.setId(bId);
        return mapper.getFiles(b, count);
    }

    public List<File> getFiles(Building b) {
        FileMapper mapper = new FileMapper(Conn.get());
        return mapper.getFiles(b);
    }

    public List<File> getFiles(Building b, int count) {
        FileMapper mapper = new FileMapper(Conn.get());
        return mapper.getFiles(b, count);
    }

    public int insertFile(File f) {
        FileMapper mapper = new FileMapper(Conn.get());
        return mapper.insertFile(f);
    }

    public boolean updateFile(File f) {
        FileMapper mapper = new FileMapper(Conn.get());
        return mapper.updateFile(f);
    }

    public Report getReport(int id) {
        ReportMapper mapper = new ReportMapper(Conn.get());
        return mapper.getReport(id);
    }

    public List<Report> getReports() {
        ReportMapper mapper = new ReportMapper(Conn.get());
        return mapper.getReports();
    }

    public List<Report> getReports(int bId) {
        ReportMapper mapper = new ReportMapper(Conn.get());
        Building b = new Building();
        b.setId(bId);
        return mapper.getReports(b);
    }

    public List<Report> getReports(int bId, int count) {
        ReportMapper mapper = new ReportMapper(Conn.get());
        Building b = new Building();
        b.setId(bId);
        return mapper.getReports(b, count);
    }

    public List<Report> getReports(Building b) {
        ReportMapper mapper = new ReportMapper(Conn.get());
        return mapper.getReports(b);
    }

    public List<Report> getReports(Building b, int count) {
        ReportMapper mapper = new ReportMapper(Conn.get());
        return mapper.getReports(b, count);
    }

    public int insertReport(Report r) {
        ReportMapper mapper = new ReportMapper(Conn.get());
        return mapper.insertReport(r);
    }

    public boolean updateReport(Report r) {
        ReportMapper mapper = new ReportMapper(Conn.get());
        return mapper.updateReport(r);
    }

    public Request getRequest(int id) {
        RequestMapper mapper = new RequestMapper(Conn.get());
        return mapper.getRequest(id);
    }

    public List<Request> getRequests() {
        RequestMapper mapper = new RequestMapper(Conn.get());
        return mapper.getRequests();
    }

    public List<Request> getRequests(int bId) {
        RequestMapper mapper = new RequestMapper(Conn.get());
        Building b = new Building();
        b.setId(bId);
        return mapper.getRequests(b);
    }

    public List<Request> getRequests(int bId, int count) {
        RequestMapper mapper = new RequestMapper(Conn.get());
        Building b = new Building();
        b.setId(bId);
        return mapper.getRequests(b, count);
    }

    public List<Request> getRequests(Building b) {
        RequestMapper mapper = new RequestMapper(Conn.get());
        return mapper.getRequests(b);
    }

    public List<Request> getRequests(Building b, int count) {
        RequestMapper mapper = new RequestMapper(Conn.get());
        return mapper.getRequests(b, count);
    }

    public int insertRequest(Request r) {
        RequestMapper mapper = new RequestMapper(Conn.get());
        return mapper.insertRequest(r);
    }

    public boolean updateRequest(Request r) {
        RequestMapper mapper = new RequestMapper(Conn.get());
        return mapper.updateRequest(r);
    }

    public List<ServiceType> getServiceTypes() {
        ServiceTypeMapper mapper = new ServiceTypeMapper(Conn.get());
        return mapper.getServiceTypes();
    }
}
