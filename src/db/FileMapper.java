package db;

import model.Building;
import model.Document;
import model.File;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 2016-11-09.
 *
 * @author Niki
 */
public class FileMapper {

    private static Connection conn;

    public FileMapper(Connection conn) {
        FileMapper.conn = conn;
    }
    
    public File getFile(int id) {
        String query = "SELECT Id, `Name`, Path, FkBuildingId FROM `File`" +
                " WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return constructFile(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<File> getFiles() {
        return getFiles(null);
    }

    public List<File> getFiles(Building b) {
        return getFiles(b, -1);
    }

    public List<File> getFiles(Building b, int count) {
        String query = "SELECT Id, `Name`, Path, FkBuildingId FROM `File`";
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
            List<File> list = new ArrayList<>();
            while (rs.next())
                list.add(constructFile(rs));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertFile(File d) {
        int id = -1;
        String query = "INSERT INTO `File` (`Name`, Path, FkBuildingId) " +
                "VALUES (?, ?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement
                .RETURN_GENERATED_KEYS)) {
            stmt.setString(1, d.getName());
            stmt.setString(2, d.getPath());
            stmt.setInt(3, d.getBuilding().getId());
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

    public boolean updateFile(File d) {
        String query = "UPDATE `File` SET `Name`=?, Path=?, " +
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

    private File constructFile(ResultSet rs) {
        try {
            File c = new File();
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
