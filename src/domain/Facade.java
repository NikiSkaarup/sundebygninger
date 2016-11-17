package domain;

import db.*;
import exceptions.PolygonException;
import model.*;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Niki on 2016-11-02.
 *
 * @author Niki
 */
public class Facade {

    private static Connection conn;
    private static Facade facade;

    private Facade() {
        conn = Conn.get();
    }

    private Facade(Connection conn) {
        Facade.conn = conn;
    }

    public static Facade getFacade() {
        if (facade == null)
            facade = new Facade();
        return facade;
    }

    public static Facade getFacadeWithConn(Connection conn) {
        if (facade == null)
            facade = new Facade(conn);
        return facade;
    }

    public Building getBuilding(int id) throws PolygonException {
        BuildingMapper mapper = new BuildingMapper(conn);
        return mapper.getBuilding(id);
    }

    public List<Building> getBuildings(int orgId) throws PolygonException {
        BuildingMapper mapper = new BuildingMapper(conn);
        Org org = new Org();
        org.setId(orgId);
        return mapper.getBuildings(org);
    }

    public List<Building> getBuildings(int orgId, int count) throws
            PolygonException {
        BuildingMapper mapper = new BuildingMapper(conn);
        Org org = new Org();
        org.setId(orgId);
        return mapper.getBuildings(org, count);
    }

    public List<Building> getBuildings(Org org) throws PolygonException {
        BuildingMapper mapper = new BuildingMapper(conn);
        return mapper.getBuildings(org);
    }

    public List<Building> getBuildings(Org org, int count) throws
            PolygonException {
        BuildingMapper mapper = new BuildingMapper(conn);
        return mapper.getBuildings(org, count);
    }

    public int insertBuilding(Building b) throws PolygonException {
        BuildingMapper mapper = new BuildingMapper(conn);
        return mapper.insertBuilding(b);
    }

    public boolean updateBuilding(Building b) throws PolygonException {
        BuildingMapper mapper = new BuildingMapper(conn);
        return mapper.updateBuilding(b);
    }

    public User getUser(int id) throws PolygonException {
        UserMapper mapper = new UserMapper(conn);
        return mapper.getUser(id);
    }

    public User getUser(User user) throws PolygonException {
        UserMapper mapper = new UserMapper(conn);
        return mapper.getUser(user.getId());
    }

    /**
     * get user by Email and Password in order to login
     *
     * @param e email
     * @param p password
     * @return user
     */
    public User getUserLogin(String e, String p) throws PolygonException {
        UserMapper mapper = new UserMapper(conn);
        return mapper.getUserLogin(e, p);
    }

    public List<User> getUsers() throws PolygonException {
        UserMapper mapper = new UserMapper(conn);
        return mapper.getUsers();
    }

    public List<User> getUsers(int orgId) throws PolygonException {
        UserMapper mapper = new UserMapper(conn);
        Org org = new Org();
        org.setId(orgId);
        return mapper.getUsers(org);
    }

    public List<User> getUsers(int orgId, int count) throws PolygonException {
        UserMapper mapper = new UserMapper(conn);
        Org org = new Org();
        org.setId(orgId);
        return mapper.getUsers(org, count);
    }

    public List<User> getUsers(Org org) throws PolygonException {
        UserMapper mapper = new UserMapper(conn);
        return mapper.getUsers(org);
    }

    public List<User> getUsers(Org org, int count) throws PolygonException {
        UserMapper mapper = new UserMapper(conn);
        return mapper.getUsers(org, count);
    }

    public int insertUser(User u) throws PolygonException {
        UserMapper mapper = new UserMapper(conn);
        return mapper.insertUser(u);
    }

    public boolean updateUser(User u) throws PolygonException {
        UserMapper mapper = new UserMapper(conn);
        return mapper.updateUser(u);
    }

    public Org getOrg(int id) throws PolygonException {
        OrgMapper mapper = new OrgMapper(conn);
        return mapper.getOrg(id);
    }

    public Org getOrg(Org org) throws PolygonException {
        OrgMapper mapper = new OrgMapper(conn);
        return mapper.getOrg(org.getId());
    }

    public List<Org> getOrgs() throws PolygonException {
        OrgMapper mapper = new OrgMapper(conn);
        return mapper.getOrgs();
    }

    public List<Org> getOrgs(int n) throws PolygonException {
        OrgMapper mapper = new OrgMapper(conn);
        return mapper.getOrgs(n);
    }

    public int insertOrg(Org u) throws PolygonException {
        OrgMapper mapper = new OrgMapper(conn);
        return mapper.insertOrg(u);
    }

    public boolean updateOrg(Org u) throws PolygonException {
        OrgMapper mapper = new OrgMapper(conn);
        return mapper.updateOrg(u);
    }

    public Image getImage(int id) throws PolygonException {
        ImageMapper mapper = new ImageMapper(conn);
        return mapper.getImage(id);
    }

    public List<Image> getImages() {
        ImageMapper mapper = new ImageMapper(conn);
        return mapper.getImages();
    }

    public List<Image> getImages(int bId) {
        ImageMapper mapper = new ImageMapper(conn);
        Building b = new Building();
        b.setId(bId);
        return mapper.getImages(b);
    }

    public List<Image> getImages(int bId, int count) {
        ImageMapper mapper = new ImageMapper(conn);
        Building b = new Building();
        b.setId(bId);
        return mapper.getImages(b, count);
    }

    public List<Image> getImages(Building b) {
        ImageMapper mapper = new ImageMapper(conn);
        return mapper.getImages(b);
    }

    public List<Image> getImages(Building b, int count) {
        ImageMapper mapper = new ImageMapper(conn);
        return mapper.getImages(b, count);
    }

    public int insertImage(Image i) {
        ImageMapper mapper = new ImageMapper(conn);
        return mapper.insertImage(i);
    }

    public boolean updateImage(Image i) {
        ImageMapper mapper = new ImageMapper(conn);
        return mapper.updateImage(i);
    }

    public Document getDocument(int id) throws PolygonException {
        DocumentMapper mapper = new DocumentMapper(conn);
        return mapper.getDocument(id);
    }

    public List<Document> getDocuments() throws PolygonException {
        DocumentMapper mapper = new DocumentMapper(conn);
        return mapper.getDocuments();
    }

    public List<Document> getDocuments(int bId) throws PolygonException {
        DocumentMapper mapper = new DocumentMapper(conn);
        Building b = new Building();
        b.setId(bId);
        return mapper.getDocuments(b);
    }

    public List<Document> getDocuments(int bId, int count) throws
            PolygonException {
        DocumentMapper mapper = new DocumentMapper(conn);
        Building b = new Building();
        b.setId(bId);
        return mapper.getDocuments(b, count);
    }

    public List<Document> getDocuments(Building b) throws PolygonException {
        DocumentMapper mapper = new DocumentMapper(conn);
        return mapper.getDocuments(b);
    }

    public List<Document> getDocuments(Building b, int count) throws
            PolygonException {
        DocumentMapper mapper = new DocumentMapper(conn);
        return mapper.getDocuments(b, count);
    }

    public int insertDocument(Document d) throws PolygonException {
        DocumentMapper mapper = new DocumentMapper(conn);
        return mapper.insertDocument(d);
    }

    public boolean updateDocument(Document d) throws PolygonException {
        DocumentMapper mapper = new DocumentMapper(conn);
        return mapper.updateDocument(d);
    }

    public File getFile(int id) throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        return mapper.getFile(id);
    }

    public List<File> getFiles() throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        return mapper.getFiles();
    }

    public List<File> getFiles(int bId) throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        Building b = new Building();
        b.setId(bId);
        return mapper.getFiles(b);
    }

    public List<File> getFiles(int bId, int count) throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        Building b = new Building();
        b.setId(bId);
        return mapper.getFiles(b, count);
    }

    public List<File> getFiles(Building b, int count) throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        return mapper.getFiles(b, count);
    }

    public List<File> getFiles(FileType ft, int count) throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        return mapper.getFiles(ft, count);
    }

    public int insertFile(File f) throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        return mapper.insertFile(f);
    }

    public boolean updateFile(File f) throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        return mapper.updateFile(f);
    }

    public Report getReport(int id) {
        ReportMapper mapper = new ReportMapper(conn);
        return mapper.getReport(id);
    }

    public List<Report> getReports() {
        ReportMapper mapper = new ReportMapper(conn);
        return mapper.getReports();
    }

    public List<Report> getReports(int bId) {
        ReportMapper mapper = new ReportMapper(conn);
        Building b = new Building();
        b.setId(bId);
        return mapper.getReports(b);
    }

    public List<Report> getReports(int bId, int count) {
        ReportMapper mapper = new ReportMapper(conn);
        Building b = new Building();
        b.setId(bId);
        return mapper.getReports(b, count);
    }

    public List<Report> getReports(Building b) {
        ReportMapper mapper = new ReportMapper(conn);
        return mapper.getReports(b);
    }

    public List<Report> getReports(Building b, int count) {
        ReportMapper mapper = new ReportMapper(conn);
        return mapper.getReports(b, count);
    }

    public int insertReport(Report r) {
        ReportMapper mapper = new ReportMapper(conn);
        return mapper.insertReport(r);
    }

    public boolean updateReport(Report r) {
        ReportMapper mapper = new ReportMapper(conn);
        return mapper.updateReport(r);
    }

    public Request getRequest(int id) throws PolygonException {
        RequestMapper mapper = new RequestMapper(conn);
        return mapper.getRequest(id);
    }

    public List<Request> getRequests() throws PolygonException {
        RequestMapper mapper = new RequestMapper(conn);
        return mapper.getRequests();
    }

    public List<Request> getRequests(int bId) throws PolygonException {
        RequestMapper mapper = new RequestMapper(conn);
        Building b = new Building();
        b.setId(bId);
        return mapper.getRequests(b);
    }

    public List<Request> getRequests(int bId, int count) throws
            PolygonException {
        RequestMapper mapper = new RequestMapper(conn);
        Building b = new Building();
        b.setId(bId);
        return mapper.getRequests(b, count);
    }

    public List<Request> getRequests(Building b) throws PolygonException {
        RequestMapper mapper = new RequestMapper(conn);
        return mapper.getRequests(b);
    }

    public List<Request> getRequests(Building b, int count) throws
            PolygonException {
        RequestMapper mapper = new RequestMapper(conn);
        return mapper.getRequests(b, count);
    }

    public int insertRequest(Request r) throws PolygonException {
        RequestMapper mapper = new RequestMapper(conn);
        return mapper.insertRequest(r);
    }

    public boolean updateRequest(Request r) throws PolygonException {
        RequestMapper mapper = new RequestMapper(conn);
        return mapper.updateRequest(r);
    }

    public List<ServiceType> getServiceTypes() throws PolygonException {
        ServiceTypeMapper mapper = new ServiceTypeMapper(conn);
        return mapper.getServiceTypes();
    }
}
