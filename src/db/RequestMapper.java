package db;

import exceptions.PolygonException;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 2016-11-09.
 *
 * @author Niki
 */
public class RequestMapper {

    private static Connection conn;

    public RequestMapper(Connection conn) {
        this.conn = conn;
    }

    public Request getRequest(int id) throws PolygonException {
        String query = "SELECT Request.Id, FkBuildingId, Submission, " +
                "FkReportId, Description, FkServiceTypeId, FkUserId, " +
                "ServiceType.Name, `User`.Name, `User`.Email, `User`.Phone " +
                "FROM Request INNER JOIN ServiceType ON Request" +
                ".FkServiceTypeId = ServiceType.Id LEFT JOIN `User` ON " +
                "Request.FkUserId = `User`.Id WHERE Request.Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return constructRequest(rs);
            else
                throw new PolygonException("No result found with id: " + id);
        } catch (SQLException e) {
            throw new PolygonException("getRequest error: " + e.getMessage());
        }
    }

    public List<Request> getRequests() throws PolygonException {
        return getRequests(null);
    }

    public List<Request> getRequests(Building b) throws PolygonException {
        return getRequests(b, -1);
    }

    public List<Request> getRequests(Building b, int count) throws
            PolygonException {
        String query = "SELECT Request.Id, FkBuildingId, Submission, " +
                "FkReportId, Description, FkServiceTypeId, FkUserId, " +
                "ServiceType.Name, `User`.Name, `User`.Email, `User`.Phone " +
                "FROM Request INNER JOIN ServiceType ON Request" +
                ".FkServiceTypeId = ServiceType.Id LEFT JOIN `User` ON " +
                "Request.FkUserId = `User`.Id";
        if (b != null) {
            query += " WHERE FkBuildingId=?";
            if (count > 0)
                query += " LIMIT ?";
        }
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            if (b != null) {
                stmt.setInt(1, b.getId());
                if (count > 0)
                    stmt.setInt(2, count);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                List<Request> list = new ArrayList<>();
                while (rs.next())
                    list.add(constructRequest(rs));
                return list;
            } catch (PolygonException e) {
                throw new PolygonException("getRequests: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getRequests error: " + e.getMessage());
        }
    }

    public int insertRequest(Request r) throws PolygonException {
        String query = "INSERT INTO `Request` (FkBuildingId, Description, " +
                "FkServiceTypeId) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query,
                                                            Statement
                                                                    .RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, r.getBuilding().getId());
            stmt.setString(2, r.getDescription());
            stmt.setInt(3, r.getServiceType().getId());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next())
                    return rs.getInt(1);
                else
                    throw new PolygonException("insertRequest failed to get " +
                                                       "generated Id");
            } catch (Exception e) {
                throw new PolygonException("insertRequest failed to " +
                                                   "insert document: " + e);
            }
        } catch (SQLException e) {
            throw new PolygonException("insertRequest error: " + e
                    .getMessage());
        }
    }

    public boolean updateRequest(Request r) throws PolygonException {
        String query = "UPDATE `Report` SET `FkBuildingId`=?, FkUserId=?, " +
                "FkBuildingId=? WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, r.getBuilding().getId());
            stmt.setInt(2, r.getUser().getId());
            stmt.setInt(3, r.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PolygonException("updateRequest error: " + e
                    .getMessage());
        }
    }

    private Request constructRequest(ResultSet rs) throws PolygonException {
        try {

            Request c = new Request();
            c.setId(rs.getInt("Request.Id"));
            c.setDescription(rs.getString("Description"));
            c.setSubmission(rs.getTimestamp("Submission"));

            Building b = new Building();
            b.setId(rs.getInt("FkBuildingId"));
            c.setBuilding(b);

            Report r = new Report();
            try {
                r.setId(rs.getInt("FkReportId"));
            } catch (SQLException | NullPointerException e) {
                r.setId(-1);
            }
            c.setReport(r);

            ServiceType st = new ServiceType(rs.getInt("FkServiceTypeId"));
            st.setName(rs.getString("ServiceType.Name"));
            c.setServiceType(st);

            User user = new User();
            user.setId(rs.getInt("FkUserId"));
            try {
                user.setName(rs.getString("User.Name"));
                user.setEmail(rs.getString("User.Email"));
                user.setEmail(rs.getString("User.Phone"));
            } catch (SQLException ignored) {
                // This is ignored because it doesn't have to exist.
            }
            c.setServiceType(st);


            return c;
        } catch (SQLException e) {
            throw new PolygonException("constructRequest error: " + e
                    .getMessage());
        }
    }

    public List<Request> getRequestsUnaccepted(int limit)
            throws PolygonException {
        String query = "SELECT Request.Id, FkBuildingId, Submission, " +
                "FkReportId, Description, FkServiceTypeId, FkUserId, " +
                "ServiceType.Name FROM Request INNER JOIN ServiceType ON " +
                "Request .FkServiceTypeId = ServiceType.Id WHERE FkUserId IS " +
                "NULL LIMIT ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                List<Request> list = new ArrayList<>();
                while (rs.next())
                    list.add(constructRequest(rs));
                return list;
            } catch (PolygonException e) {
                throw new PolygonException("getRequestsUnaccepted: " + e
                        .getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getRequestsUnaccepted error: " + e
                    .getMessage());
        }
    }

    public List<Request> getRequestsAcceptedByEmployee(int id, int limit)
            throws PolygonException {
        String query = "SELECT Request.Id, FkBuildingId, Submission, " +
                "FkReportId, Description, FkServiceTypeId, FkUserId, " +
                "ServiceType.Name, `User`.Name, `User`.Email, `User`.Phone " +
                "FROM Request INNER JOIN ServiceType ON Request" +
                ".FkServiceTypeId = ServiceType.Id INNER JOIN `User` ON " +
                "Request.FkUserId = `User`.Id WHERE `User`.Id = ? LIMIT ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setInt(2, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                List<Request> list = new ArrayList<>();
                while (rs.next())
                    list.add(constructRequest(rs));
                return list;
            } catch (PolygonException e) {
                throw new PolygonException("getRequestsAcceptedByEmployee: " +
                                                   e.getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getRequestsAcceptedByEmployee error: " +
                                               e.getMessage());
        }
    }

    public List<Request> getRequestsLimit(int limit) throws PolygonException {
        String query = "SELECT Request.Id, FkBuildingId, Submission, " +
                "FkReportId, Description, FkServiceTypeId, FkUserId, " +
                "ServiceType.Name, `User`.Name, `User`.Email, `User`.Phone " +
                "FROM Request INNER JOIN ServiceType ON Request" +
                ".FkServiceTypeId = ServiceType.Id LEFT JOIN `User` ON " +
                "Request.FkUserId = `User`.Id LIMIT ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                List<Request> list = new ArrayList<>();
                while (rs.next())
                    list.add(constructRequest(rs));
                return list;
            } catch (PolygonException e) {
                throw new PolygonException("getRequestsLimit: " + e
                        .getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getRequestsLimit error: " + e
                    .getMessage());
        }
    }
}
