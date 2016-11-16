package db;

import model.Building;
import model.File;
import model.FileType;

import java.io.ByteArrayInputStream;
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
        String query = "SELECT Id, `Name`, `Data`, FkBuildingId, FkFileTypeId" +
                " FROM `File` WHERE Id=?";
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
        String query = "SELECT Id, `Name`, `Data`, FkBuildingId, FkFileTypeId" +
                " FROM `File`";
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

    public List<File> getFiles(FileType t, int count) {
        String query = "SELECT Id, `Name`, `Data`, FkBuildingId, FkFileTypeId" +
                " FROM `File`";
        if (t != null) {
            query += " WHERE FkFileTypeId=?";
            if (count > 0)
                query += " LIMIT ?";
        }
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            if (t != null) {
                stmt.setInt(1, t.getId());
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

    public int insertFile(File f) {
        int id = -1;
        String query = "INSERT INTO `File` (`Name`, `Data`, FkBuildingId, " +
                "FkFileTypeId) VALUES (?, ?, ?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement
                .RETURN_GENERATED_KEYS)) {
            stmt.setString(1, f.getName());
            stmt.setBlob(2, new ByteArrayInputStream(f.getData()));
            stmt.setInt(3, f.getBuilding().getId());
            stmt.setInt(4, f.getType().getId());
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

    public boolean updateFile(File f) {
        String query = "UPDATE `File` SET `Name`=?, `Data`=?, " +
                "FkBuildingId=?, FkFileTypeId=? WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, f.getName());
            stmt.setBlob(2, new ByteArrayInputStream(f.getData()));
            stmt.setInt(3, f.getBuilding().getId());
            stmt.setInt(4, f.getType().getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private File constructFile(ResultSet rs) {
        try {
            File f = new File();
            f.setId(rs.getInt("Id"));
            f.setName(rs.getString("Name"));

            Blob blob = rs.getBlob("Data");
            f.setData(blob.getBytes(0, (int) blob.length()));

            Building b = new Building();
            b.setId(rs.getInt("FkBuildingId"));
            f.setBuilding(b);

            FileType ft = new FileType();
            ft.setId(rs.getInt("FkFileTypeId"));
            f.setType(ft);

            return f;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
