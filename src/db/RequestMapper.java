package db;

import model.Building;
import model.Request;

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

    public Request getRequest(int id) {
        String query = "SELECT Id, FkBuildingId, Submission, FkReportId, " +
                "Description, FkServiceTypeId, FkUserId FROM `Request` WHERE " +
                "Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return constructRequest(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Request> getRequests() {
        return getRequests(null);
    }

    public List<Request> getRequests(Building b) {
        return getRequests(b, -1);
    }

    public List<Request> getRequests(Building b, int count) {
        String query = "SELECT Id, FkBuildingId, Submission, FkReportId, " +
                "Description, FkServiceTypeId, FkUserId FROM `Request`";
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
            ResultSet rs = stmt.executeQuery();
            List<Request> list = new ArrayList<>();
            while (rs.next())
                list.add(constructRequest(rs));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertRequest(Request r) {
        int id = -1;
        String query = "INSERT INTO `Request` (FkBuildingId, Description, " +
                "FkServiceTypeId) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, r.getBuilding().getId());
            stmt.setString(2, r.getDescription());
            stmt.setInt(3, r.getServiceType().getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt(1);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean updateRequest(Request r) {
        String query = "UPDATE `Report` SET `FkBuildingId`=?, FkUserId=?, " +
                "FkBuildingId=? WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, r.getBuilding().getId());
            stmt.setInt(2, r.getUser().getId());
            stmt.setInt(3, r.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Request constructRequest(ResultSet rs) {
        try {
            Request c = new Request();
            c.setId(rs.getInt("Id"));
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
