package db;

import model.Building;
import model.Image;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 2016-11-09.
 *
 * @author Niki
 */
public class ImageMapper {

    private static Connection conn;

    public ImageMapper(Connection conn) {
        ImageMapper.conn = conn;
    }

    public Image getImage(int id) {
        String query = "SELECT Id, `Name`, Path, FkBuildingId FROM `Image` " +
                "WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return constructImage(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Image> getImages() {
        return getImages(null);
    }

    public List<Image> getImages(Building b) {
        return getImages(b, -1);
    }

    public List<Image> getImages(Building b, int count) {
        String query = "SELECT Id, `Name`, Path, FkBuildingId FROM `Image`";
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
            List<Image> list = new ArrayList<>();
            while (rs.next())
                list.add(constructImage(rs));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertImage(Image i) {
        int id = -1;
        String query = "INSERT INTO `Image` (`Name`, Path, FkBuildingId) " +
                "VALUES (?, ?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement
                .RETURN_GENERATED_KEYS)) {
            stmt.setString(1, i.getName());
            stmt.setString(2, i.getPath());
            stmt.setInt(3, i.getBuilding().getId());
            int changed = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt("Id");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean updateImage(Image i) {
        String query = "UPDATE `Image` SET `Name`=?, Path=?, FkBuildingId=? " +
                "WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, i.getName());
            stmt.setString(2, i.getPath());
            stmt.setInt(3, i.getBuilding().getId());
            stmt.setInt(4, i.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Image constructImage(ResultSet rs) {
        try {
            Image c = new Image();
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
