package db;

import exceptions.PolygonException;
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

    /***
     * Constructor requires a Database Connection
     * @param conn Database Connection
     */
    public FileMapper(Connection conn) {
        FileMapper.conn = conn;
    }

    /***
     *
     * @param id
     * @return
     * @throws PolygonException
     */
    public File getFile(int id) throws PolygonException {
        String query = "SELECT Id, `Name`, `Data`, FkBuildingId, FkFileTypeId" +
                " FROM `File` WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return constructFile(rs);
                else {
                    throw new PolygonException("No result found with id: " +
                            id);
                }
            } catch (PolygonException e) {
                throw new PolygonException("getFile ResultSet: " +
                        e.getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getFile error: " + e.getMessage());
        }
    }

    /***
     *
     * @param id
     * @return
     * @throws PolygonException
     */
    public File getFile(int id, FileType ft) throws PolygonException {
        String query = "SELECT Id, `Name`, `Data`, FkBuildingId, FkFileTypeId" +
                " FROM `File` WHERE Id=? AND FkFileTypeId=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setInt(2, ft.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return constructFile(rs);
                else {
                    throw new PolygonException("No result found with id: " +
                                                       id);
                }
            } catch (PolygonException e) {
                throw new PolygonException("getFile ResultSet: " +
                                                   e.getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getFile error: " + e.getMessage());
        }
    }

    /***
     *
     * @return
     * @throws PolygonException
     */
    public List<File> getFiles() throws PolygonException {
        return getFiles((Building) null);
    }

    /***
     *
     * @param b
     * @return
     * @throws PolygonException
     */
    public List<File> getFiles(Building b) throws PolygonException {
        return getFiles(b, -1);
    }

    /***
     *
     * @param b
     * @param count
     * @return
     * @throws PolygonException
     */
    public List<File> getFiles(Building b, int count) throws PolygonException {
        String query = "SELECT `File`.Id, `File`.`Name`, `Data`, " +
                "FkBuildingId, FkFileTypeId FROM `File` INNER JOIN FileType " +
                "ON `File`.FkFileTypeId = FileType.Id";
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
                List<File> list = new ArrayList<>();
                while (rs.next())
                    list.add(constructFile(rs));
                return list;
            } catch (PolygonException e) {
                throw new PolygonException("getFiles: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getFiles sql: " + e.getMessage());
        }
    }

    /***
     *
     * @param t
     * @return
     * @throws PolygonException
     */
    public List<File> getFiles(FileType t) throws PolygonException {
        return getFiles(t, -1);
    }

    /***
     *
     * @param t
     * @param count
     * @return
     * @throws PolygonException
     */
    public List<File> getFiles(FileType t, int count) throws PolygonException {
        String query = "SELECT `File`.Id, `File`.`Name`, `Data`, " +
                "FkBuildingId, FkFileTypeId FROM `File` INNER JOIN FileType " +
                "ON `File`.FkFileTypeId = FileType.Id";
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
            try (ResultSet rs = stmt.executeQuery()) {
                List<File> list = new ArrayList<>();
                while (rs.next())
                    list.add(constructFile(rs));
                return list;
            } catch (PolygonException e) {
                throw new PolygonException("getFiles: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getFiles: " + e.getMessage());
        }
    }

    /***
     *
     * @param f
     * @return
     * @throws PolygonException
     */
    public int insertFile(File f) throws PolygonException {
        String query = "INSERT INTO `File` (`Name`, `Data`, FkBuildingId, " +
                "FkFileTypeId) VALUES (?, ?, ?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement
                .RETURN_GENERATED_KEYS)) {
            stmt.setString(1, f.getName());
            stmt.setBlob(2, new ByteArrayInputStream(f.getData()));
            stmt.setInt(3, f.getBuilding().getId());
            stmt.setInt(4, f.getType().getId());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next())
                    return rs.getInt(1);
                else
                    throw new PolygonException("insertFile failed to get " +
                            "generated Id");
            } catch (Exception e) {
                throw new PolygonException("insertFile failed to " +
                        "insert file: " + e);
            }
        } catch (SQLException e) {
            throw new PolygonException("insertFile error: " + e
                    .getMessage());
        }
    }

    /***
     *
     * @param f
     * @return
     * @throws PolygonException
     */
    public boolean updateFile(File f) throws PolygonException {
        String query = "UPDATE `File` SET `Name`=?, `Data`=?, " +
                "FkBuildingId=?, FkFileTypeId=? WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, f.getName());
            stmt.setBlob(2, new ByteArrayInputStream(f.getData()));
            stmt.setInt(3, f.getBuilding().getId());
            stmt.setInt(4, f.getType().getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PolygonException("updateFile error: " + e
                    .getMessage());
        }
    }

    /***
     *
     * @param rs
     * @return
     * @throws PolygonException
     */
    private File constructFile(ResultSet rs) throws PolygonException {
        try {
            File f = new File(rs.getInt("File.Id"));
            f.setName(rs.getString("File.Name"));

            Blob blob = rs.getBlob("Data");
            f.setData(blob.getBytes(0, (int) blob.length()));

            Building b = new Building();
            b.setId(rs.getInt("FkBuildingId"));
            f.setBuilding(b);

            FileType ft = new FileType(rs.getInt("FkFileTypeId"));
            ft.setName(rs.getString("FileType.Name"));
            f.setType(ft);

            return f;
        } catch (SQLException e) {
            throw new PolygonException("constructFile error: " + e
                    .getMessage());
        }
    }
}
