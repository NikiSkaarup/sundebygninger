package db;

import model.Building;
import model.Org;
import model.Role;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 2016-11-02.
 *
 * @author Niki
 */
public class Dal {
    private static Connection conn;

    /**
     * Connect to database, in given connection
     * @param conn
     */
    public Dal(Connection conn) {
        Dal.conn = conn;
    }

    public Building getBuilding(int id) {
        String sql = "SELECT Id, Name, Address, ConstructionYear, CurrentUse, Area, PreviousUse FROM `Building` WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return constructBuilding(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Building> getBuildings(Org org) {
        String sql = "SELECT Id, Name, Address, ConstructionYear, CurrentUse, Area, PreviousUse FROM `Building` WHERE FkOrgId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            List<Building> buildings = new ArrayList<>();
            stmt.setInt(1, org.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                buildings.add(constructBuilding(rs));
            }
            return buildings;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Building> getBuildings(Org org, int count) {
        String sql = "SELECT Id, Name, Address, ConstructionYear, CurrentUse, Area, PreviousUse FROM `Building` WHERE FkOrgId=? LIMIT ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            List<Building> buildings = new ArrayList<>();
            stmt.setInt(1, org.getId());
            stmt.setInt(2, count);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                buildings.add(constructBuilding(rs));
            }
            return buildings;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertBuilding(Building b) {
        int id = -1;
        String sql = "INSERT INTO `Building` (Name, Address, ConstructionYear, CurrentUse, Area, PreviousUse, FkOrgId) VALUES (?, ?, ?, ?, ?, ?, ?); SELECT LAST_INSERT_ID() AS Id;";
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
        String sql = "UPDATE `Building` SET Name=?, Address=?, ConstructionYear=?, CurrentUse=?, Area=?, PreviousUse=?, FkOrgId=? WHERE Id=?";
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
            Building building = new Building();
            building.setId(rs.getInt("Id"));
            building.setName(rs.getString("Name"));
            building.setAddress(rs.getString("Address"));
            building.setConstructionYear(rs.getTimestamp("ConstructionYear"));
            building.setCurrentUse(rs.getString("CurrentUse"));
            building.setArea(rs.getString("Area"));
            building.setPreviousUse(rs.getString("PreviousUse"));

            Org org = new Org();
            org.setId(rs.getInt("FkOrgId"));
            building.setOrg(org);
            return building;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(int id) {
        String sql = "SELECT Id, Name, Email, Phone, FkRoleId, FkOrgId FROM `User` WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return constructUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserLogin(String email, String pass) {
        String sql = "SELECT Id, Name, Email, Phone, FkRoleId, FkOrgId FROM `User` WHERE Email=? AND Password=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return constructUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUsers() {
        String sql = "SELECT Id, Name, Email, Phone, FkRoleId, FkOrgId FROM `User`";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(constructUser(rs));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUsers(Org org) {
        String sql = "SELECT Id, Name, Email, Phone, FkRoleId, FkOrgId FROM `User` WHERE FkOrgId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, org.getId());
            ResultSet rs = stmt.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(constructUser(rs));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUsers(Org org, int count) {
        String sql = "SELECT Id, Name, Email, Phone, FkRoleId, FkOrgId FROM `User` WHERE FkOrgId=? LIMIT ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, org.getId());
            stmt.setInt(2, count);
            ResultSet rs = stmt.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(constructUser(rs));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertUser(User u) {
        int id = -1;
        String sql = "INSERT INTO `User` (Name, Email, Phone, FkRoleId, FkOrgId) VALUES (?, ?, ?, ?, ?); SELECT LAST_INSERT_ID() AS Id;";
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
        String sql = "UPDATE `User` SET Name=?, Email=?, Phone=?, FkRoleId=?, FkOrgId=? WHERE Id=?";
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
            User user = new User();
            user.setId(rs.getInt("Id"));
            user.setName(rs.getString("Name"));
            user.setEmail(rs.getString("Email"));
            user.setPhone(rs.getString("Phone"));

            Role role = new Role();
            role.setId(rs.getInt("FkRoleId"));
            user.setRole(role);

            Org org = new Org();
            org.setId(rs.getInt("FkOrgId"));
            user.setOrg(org);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
