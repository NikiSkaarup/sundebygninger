package db;

import model.Building;
import model.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 2016-11-09.
 *
 * @author Niki
 */
public class DocumentMapper {

    private static Connection conn;

    public DocumentMapper(Connection conn) {
        DocumentMapper.conn = conn;
    }

    public Document getDocument(int id) {
        String query = "SELECT Id, `Name`, Path, FkBuildingId FROM `Document`" +
                " WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return constructDocument(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Document> getDocuments() {
        return getDocuments(null);
    }

    public List<Document> getDocuments(Building b) {
        return getDocuments(b, -1);
    }

    public List<Document> getDocuments(Building b, int count) {
        String query = "SELECT Id, `Name`, Path, FkBuildingId FROM `Document`";
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
            List<Document> list = new ArrayList<>();
            while (rs.next())
                list.add(constructDocument(rs));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertDocument(Document d) {
        int id = -1;
        String query = "INSERT INTO `Document` (`Name`, Path, FkBuildingId) " +
                "VALUES (?, ?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement
                .RETURN_GENERATED_KEYS)) {
            stmt.setString(1, d.getName());
            stmt.setString(2, d.getPath());
            stmt.setInt(3, d.getBuilding().getId());
            int changed = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt("Id");
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean updateDocument(Document d) {
        String query = "UPDATE `Document` SET `Name`=?, Path=?, " +
                "FkBuildingId=? WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, d.getName());
            stmt.setString(2, d.getPath());
            stmt.setInt(3, d.getBuilding().getId());
            stmt.setInt(4, d.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Document constructDocument(ResultSet rs) {
        try {
            Document c = new Document();
            c.setId(rs.getInt("Id"));
            c.setName(rs.getString("Name"));
            c.setPath(rs.getString("Path"));

            Building b = new Building();
            b.setId(rs.getInt("FkBuildingId"));
            c.setBuilding(b);

            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
