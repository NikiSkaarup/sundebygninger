package db;

import exceptions.PolygonException;
import model.FileType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 2016-11-29.
 *
 * @author Niki
 */
public class FileTypeMapper {

    private static Connection conn;

    public FileTypeMapper(Connection conn) {
        FileTypeMapper.conn = conn;
    }

    public List<FileType> getFileTypes() throws PolygonException {
        String query = "SELECT Id, `Name` FROM FileType";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                List<FileType> list = new ArrayList<>();
                while (rs.next())
                    list.add(constructFileType(rs));
                return list;
            } catch (PolygonException e) {
                throw new PolygonException("getFileTypes: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getFileTypes error: " + e.getMessage());
        }
    }

    private FileType constructFileType(ResultSet rs) throws
            PolygonException {
        try {
            FileType c = new FileType(rs.getInt("Id"));
            c.setName(rs.getString("Name"));
            return c;
        } catch (SQLException e) {
            throw new PolygonException("constructFileType error: " + e
                    .getMessage());
        }
    }
}
