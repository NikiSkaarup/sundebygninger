package db;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 2016-11-09.
 *
 * @author Niki
 */
public class ReportMapper {

    private static Connection conn;

    public ReportMapper(Connection conn) {
        ReportMapper.conn = conn;
    }


    public Report getReport(int id) {
        String query = "SELECT Id, FkBuildingId, FkUserId FROM `Report` " +
                "WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return constructReport(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Report> getReports() {
        return getReports(null);
    }

    public List<Report> getReports(Building b) {
        return getReports(b, -1);
    }

    public List<Report> getReports(Building b, int count) {
        String query = "SELECT Id, `Name`, Path, FkBuildingId FROM `Report`";
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
            List<Report> list = new ArrayList<>();
            while (rs.next())
                list.add(constructReport(rs));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertReport(Report r) {
        int id = -1;
        String query = "INSERT INTO `Report` (`FkBuildingId`, `FkUserId`) " +
                "VALUES (?, ?); SELECT LAST_INSERT_ID() AS Id;";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, r.getBuilding().getId());
            stmt.setInt(2, r.getUser().getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getInt("Id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean updateReport(Report r) {
        String query = "UPDATE `Report` SET `FkBuildingId`=?, FkUserId=? " +
                "WHERE Id=?";
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

    private Report constructReport(ResultSet rs) {
        try {
            Report c = new Report();
            c.setId(rs.getInt("Id"));
            c.setSubmission(rs.getTimestamp("Submission"));

            User u = new User();
            u.setId(rs.getInt("FkUserId"));
            c.setUser(u);

            Building b = new Building();
            b.setId(rs.getInt("FkBuildingId"));
            c.setBuilding(b);

            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Room constructRoom(ResultSet rs) {
        try {
            Room c = new Room();
            c.setId(rs.getInt("Id"));

            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Comment constructComment(ResultSet rs) {
        try {
            Comment c = new Comment();
            c.setId(rs.getInt("Id"));
            c.setPath(rs.getString("Path"));
            c.setComment(rs.getString("Comment"));

            Room room = new Room();
            room.setId(rs.getInt("FkRoomId"));
            c.setRoom(room);

            CommentType ct = new CommentType();
            ct.setId(rs.getInt("FkCommentTypeId"));
            c.setCommentType(ct);

            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
