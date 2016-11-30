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

    public int insertUser(User u, String password) throws PolygonException {
        UserMapper mapper = new UserMapper(conn);
        return mapper.insertUser(u, password);
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

    public List<Org> getOrgs(int limit) throws PolygonException {
        OrgMapper mapper = new OrgMapper(conn);
        return mapper.getOrgs(limit);
    }

    public int insertOrg(Org u) throws PolygonException {
        OrgMapper mapper = new OrgMapper(conn);
        return mapper.insertOrg(u);
    }

    public boolean updateOrg(Org u) throws PolygonException {
        OrgMapper mapper = new OrgMapper(conn);
        return mapper.updateOrg(u);
    }

    public File getFile(int id) throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        return mapper.getFile(id);
    }

    public File getFileOfType(int id, FileType ft) throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        return mapper.getFile(id, ft);
    }

    public File getFileDocument(int id) throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        return mapper.getFile(id, new FileType(1));
    }

    public File getFileImage(int id) throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        return mapper.getFile(id, new FileType(2));
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

    public List<File> getFilesDocuments(Building b) throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        return mapper.getFilesOfFileType(b, new FileType(1));
    }

    public List<File> getFilesImages(Building b) throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        return mapper.getFilesOfFileType(b, new FileType(2));
    }

    public List<File> getFiles(FileType ft) throws PolygonException {
        FileMapper mapper = new FileMapper(conn);
        return mapper.getFiles(ft);
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

    public List<FileType> getFileTypes() throws PolygonException {
        FileTypeMapper mapper = new FileTypeMapper(conn);
        return mapper.getFileTypes();
    }

    public List<Report> getReportsByEmployee(int id, int limit)
            throws PolygonException {
        ReportMapper mapper = new ReportMapper(conn);
        return mapper.getReportsByEmployee(id, limit);
    }

    public List<Report> getReportsLimit(int limit)
            throws PolygonException {
        ReportMapper mapper = new ReportMapper(conn);
        return mapper.getReportsLimit(limit);
    }

    public List<Request> getRequestsUnaccepted(int limit)
            throws PolygonException {
        RequestMapper mapper = new RequestMapper(conn);
        return mapper.getRequestsUnaccepted(limit);
    }

    public List<Request> getRequestsAcceptedByEmployee(int id, int limit)
            throws PolygonException {
        RequestMapper mapper = new RequestMapper(conn);
        return mapper.getRequestsAcceptedByEmployee(id, limit);
    }

    public List<Request> getRequestsLimit(int limit)
            throws PolygonException {
        RequestMapper mapper = new RequestMapper(conn);
        return mapper.getRequestsLimit(limit);
    }


    public List<User> getUsersLimit(int limit)
            throws PolygonException {
        UserMapper mapper = new UserMapper(conn);
        return mapper.getUsersLimit(limit);
    }

    public List<Building> getBuildingsLimit(int limit)
            throws PolygonException {
        BuildingMapper mapper = new BuildingMapper(conn);
        return mapper.getBuildingsLimit(limit);
    }
}
