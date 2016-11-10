package db;

import exceptions.PolygonException;
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

    public Document getDocument(int id) throws PolygonException {
        String query = "SELECT Id, `Name`, Path, FkBuildingId FROM `Document`" +
                " WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return constructDocument(rs);
            else
                throw new PolygonException("No result found with id: " + id);
        } catch (SQLException e) {
            throw new PolygonException("getDocument error: " + e.getMessage());
        }
    }

    public List<Document> getDocuments() throws PolygonException {
        return getDocuments(null);
    }

    public List<Document> getDocuments(Building b) throws PolygonException {
        return getDocuments(b, -1);
    }

    public List<Document> getDocuments(Building b, int count) throws
            PolygonException {
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
            try (ResultSet rs = stmt.executeQuery()) {
                List<Document> list = new ArrayList<>();
                while (rs.next())
                    list.add(constructDocument(rs));
                return list;
            } catch (PolygonException e) {
                throw new PolygonException("getDocuments: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getDocuments error: " + e.getMessage());
        }
    }

    public int insertDocument(Document d) throws PolygonException {
        String query = "INSERT INTO `Document` (`Name`, Path, FkBuildingId) " +
                "VALUES (?, ?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement
                .RETURN_GENERATED_KEYS)) {
            stmt.setString(1, d.getName());
            stmt.setString(2, d.getPath());
            stmt.setInt(3, d.getBuilding().getId());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next())
                    return rs.getInt(1);
                else
                    throw new PolygonException("insertDocument failed to get " +
                            "generated Id");
            } catch (Exception e) {
                throw new PolygonException("insertDocument failed to " +
                        "insert document: " + e);
            }
        } catch (SQLException e) {
            throw new PolygonException("insertDocument error: " + e
                    .getMessage());
        }
    }

    public boolean updateDocument(Document d) throws PolygonException {
        String query = "UPDATE `Document` SET `Name`=?, Path=?, " +
                "FkBuildingId=? WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, d.getName());
            stmt.setString(2, d.getPath());
            stmt.setInt(3, d.getBuilding().getId());
            stmt.setInt(4, d.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PolygonException("updateDocument error: " + e
                    .getMessage());
        }
    }

    private Document constructDocument(ResultSet rs) throws PolygonException {
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
            throw new PolygonException("constructDocument error: " + e
                    .getMessage());
        }
    }
}
