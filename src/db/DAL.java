package db;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 2016-11-02.
 * DAL == Database Abstraction Layer
 *
 * @author Niki
 */
public class DAL {

    private static Connection conn;

    /**
     * Connect to database, in given connection
     *
     * @param conn
     */
    public DAL(Connection conn) {
        DAL.conn = conn;
    }

    public Building getBuilding(int id) {
        String sql = "SELECT Id, `Name`, Address, ConstructionYear, " +
                "CurrentUse, Area, PreviousUse FROM `Building` WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return constructBuilding(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Building> getBuildings(Org org) {
        return getBuildings(org, -1);
    }

    public List<Building> getBuildings(Org org, int count) {
        String sql = "SELECT Id, `Name`, Address, ConstructionYear, " +
                "CurrentUse, Area, PreviousUse FROM `Building`";
        if (org != null)
            sql += " WHERE FkOrgId=?";
        sql += " ORDER BY Submission DESC";
        if (org != null && count > 0)
                sql += " LIMIT ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            List<Building> list = new ArrayList<>();
            if (org != null) {
                stmt.setInt(1, org.getId());
                if (count > 0)
                    stmt.setInt(2, count);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                list.add(constructBuilding(rs));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertBuilding(Building b) {
        int id = -1;
        String sql = "INSERT INTO `Building` (`Name`, Address, " +
                "ConstructionYear, CurrentUse, Area, PreviousUse, FkOrgId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?); SELECT LAST_INSERT_ID() AS Id;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, b.getName());
            stmt.setString(2, b.getAddress());
            stmt.setTimestamp(3, b.getConstructionYear());
            stmt.setString(4, b.getCurrentUse());
            stmt.setString(5, b.getArea());
            stmt.setString(6, b.getPreviousUse());
            stmt.setInt(7, b.getOrg().getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getInt("Id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean updateBuilding(Building b) {
        String sql = "UPDATE `Building` SET `Name`=?, Address=?, " +
                "ConstructionYear=?, CurrentUse=?, Area=?, PreviousUse=?, " +
                "FkOrgId=? WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, b.getName());
            stmt.setString(2, b.getAddress());
            stmt.setTimestamp(3, b.getConstructionYear());
            stmt.setString(4, b.getCurrentUse());
            stmt.setString(5, b.getArea());
            stmt.setString(6, b.getPreviousUse());
            stmt.setInt(7, b.getOrg().getId());
            stmt.setInt(8, b.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Building constructBuilding(ResultSet rs) {
        try {
            Building c = new Building();
            c.setId(rs.getInt("Id"));
            c.setName(rs.getString("Name"));
            c.setAddress(rs.getString("Address"));
            c.setConstructionYear(rs.getTimestamp("ConstructionYear"));
            c.setCurrentUse(rs.getString("CurrentUse"));
            c.setArea(rs.getString("Area"));
            c.setPreviousUse(rs.getString("PreviousUse"));

            Org org = new Org();
            org.setId(rs.getInt("FkOrgId"));
            c.setOrg(org);

            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(int id) {
        String sql = "SELECT Id, `Name`, Email, Phone, FkRoleId, FkOrgId FROM" +
                " `User` WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return constructUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserLogin(String email, String pass) {
        String sql = "SELECT Id, `Name`, Email, Phone, FkRoleId, FkOrgId FROM" +
                " `User` WHERE Email=? AND Password=? GROUP BY FkOrgId ORDER " +
                "BY `Name`";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return constructUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUsers() {
        return getUsers(null);
    }

    public List<User> getUsers(Org org) {
        return getUsers(org, -1);
    }

    public List<User> getUsers(Org org, int count) {
        String sql = "SELECT Id, `Name`, Email, Phone, FkRoleId, FkOrgId FROM" +
                " `User`";
        if (org != null) {
            sql += " WHERE FkOrgId=?";
            if (count > 0)
                sql += " LIMIT ?";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (org != null) {
                stmt.setInt(1, org.getId());
                if (count > 0)
                    stmt.setInt(2, count);
            }
            ResultSet rs = stmt.executeQuery();
            List<User> list = new ArrayList<>();
            while (rs.next())
                list.add(constructUser(rs));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertUser(User u) {
        int id = -1;
        String sql = "INSERT INTO `User` (`Name`, Email, Phone, FkRoleId, " +
                "FkOrgId) VALUES (?, ?, ?, ?, ?); SELECT LAST_INSERT_ID() AS " +
                "Id;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPhone());
            stmt.setInt(4, u.getRole().getId());
            stmt.setInt(5, u.getOrg().getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getInt("Id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean updateUser(User u) {
        String sql = "UPDATE `User` SET `Name`=?, Email=?, Phone=?, " +
                "FkRoleId=?, FkOrgId=? WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPhone());
            stmt.setInt(4, u.getRole().getId());
            stmt.setInt(5, u.getOrg().getId());
            stmt.setInt(6, u.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private User constructUser(ResultSet rs) {
        try {
            User c = new User();
            c.setId(rs.getInt("Id"));
            c.setName(rs.getString("Name"));
            c.setEmail(rs.getString("Email"));
            c.setPhone(rs.getString("Phone"));

            Role role = new Role();
            role.setId(rs.getInt("FkRoleId"));
            c.setRole(role);

            Org org = new Org();
            org.setId(rs.getInt("FkOrgId"));
            c.setOrg(org);

            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Image getImage(int id) {
        String sql = "SELECT Id, `Name`, Path, FkBuildingId FROM `Image` " +
                "WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        String sql = "SELECT Id, `Name`, Path, FkBuildingId FROM `Image`";
        if (b != null) {
            sql += " WHERE FkBuildingId=?";
            if (count > 0)
                sql += " LIMIT ?";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        String sql = "INSERT INTO `Image` (`Name`, Path, FkBuildingId) VALUES" +
                " (?, ?, ?); SELECT LAST_INSERT_ID() AS Id;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, i.getName());
            stmt.setString(2, i.getPath());
            stmt.setInt(3, i.getBuilding().getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getInt("Id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean updateImage(Image i) {
        String sql = "UPDATE `Image` SET `Name`=?, Path=?, FkBuildingId=? " +
                "WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    public Document getDocument(int id) {
        String sql = "SELECT Id, `Name`, Path, FkBuildingId FROM `Document` " +
                "WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        String sql = "SELECT Id, `Name`, Path, FkBuildingId FROM `Document`";
        if (b != null) {
            sql += " WHERE FkBuildingId=?";
            if (count > 0)
                sql += " LIMIT ?";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        String sql = "INSERT INTO `Document` (`Name`, Path, FkBuildingId) " +
                "VALUES (?, ?, ?); SELECT LAST_INSERT_ID() AS Id;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, d.getName());
            stmt.setString(2, d.getPath());
            stmt.setInt(3, d.getBuilding().getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getInt("Id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean updateDocument(Document d) {
        String sql = "UPDATE `Document` SET `Name`=?, Path=?, FkBuildingId=? " +
                "WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
