package db;

import model.Org;
import model.Role;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exceptions.PolygonException;

/**
 * Created by Niki on 2016-11-09.
 *
 * @author Niki
 */
public class UserMapper {

    private static Connection conn;

    public UserMapper(Connection conn) {
        UserMapper.conn = conn;
    }

    public User getUser(int id) throws PolygonException {
        String query = "SELECT Id, `Name`, Email, Phone, FkRoleId, FkOrgId " +
                "FROM `User` WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return constructUser(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserLogin(String email, String pass) throws
            PolygonException {
        String query = "SELECT Id, `Name`, Email, Phone, FkRoleId, FkOrgId " +
                "FROM  `User` WHERE Email=? AND `Password`=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, pass);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return constructUser(rs);
                else
                    throw new PolygonException("getUserLogin No result found," +
                                                       " email: " + email);
            } catch (PolygonException e) {
                throw new PolygonException("getUserLogin ResultSet: " +
                                                   e.getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getUserLogin error: " + e.getMessage());
        }
    }

    public List<User> getUsers() throws PolygonException {
        return getUsers(null);
    }

    public List<User> getUsers(Org org) throws PolygonException {
        return getUsers(org, -1);
    }

    public List<User> getUsers(Org org, int count) throws PolygonException {
        String query = "SELECT Id, `Name`, Email, Phone, FkRoleId, FkOrgId " +
                "FROM `User`";
        if (org != null) {
            query += " WHERE FkOrgId=?";
            if (count > 0)
                query += " LIMIT ?";
        }
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
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

    public int insertUser(User u, String password) throws PolygonException {
        String query = "INSERT INTO `User` (`Name`, Email, `Password`, Phone, FkRoleId, " +
                "FkOrgId) VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement
                .RETURN_GENERATED_KEYS)) {
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, password);
            stmt.setString(4, u.getPhone());
            stmt.setInt(5, 1);
            stmt.setInt(6, u.getOrg().getId());
            int changed = stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next())
                    return rs.getInt(1);
                else
                    throw new PolygonException("Failed to insert user");
            } catch (Exception e) {
                throw new PolygonException("insertUser:" + e.getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("insertUser:" + e.getMessage());
        }
    }

    public boolean updateUser(User u) throws PolygonException {
        String query = "UPDATE `User` SET `Name`=?, Email=?, Phone=?, " +
                "FkOrgId=? WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPhone());
            stmt.setInt(4, u.getOrg().getId());
            stmt.setInt(5, u.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PolygonException("updateUser:" + e.getMessage());
        }
    }

    private User constructUser(ResultSet rs) throws PolygonException {
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
            throw new PolygonException("failed to constructUser: " + e
                    .getMessage());
        }
    }

    public List<User> getUsersLimit(int limit) throws PolygonException {
        String query = "SELECT Id, `Name`, Email, Phone, FkRoleId, FkOrgId " +
                "FROM `User` ORDER BY Id DESC LIMIT ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                List<User> list = new ArrayList<>();
                while (rs.next())
                    list.add(constructUser(rs));
                return list;
            } catch (PolygonException e) {
                throw new PolygonException("getUsersLimit: " + e
                        .getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getUsersLimit error: " + e
                    .getMessage());
        }
    }
}
