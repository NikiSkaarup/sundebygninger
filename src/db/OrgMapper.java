package db;

import exceptions.PolygonException;
import model.Org;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 2016-11-17.
 *
 * @author Niki
 */
public class OrgMapper {

    private static Connection conn;

    /***
     * Constructor requires a Database Connection
     * @param conn Database Connection
     */
    public OrgMapper(Connection conn) {
        OrgMapper.conn = conn;
    }

    /***
     *
     * @param id
     * @return single org
     * @throws PolygonException
     */
    public Org getOrg(int id) throws PolygonException {
        String query = "SELECT Id, `Name`, Phone FROM `Org` WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return constructOrg(rs);
                else
                    throw new PolygonException("No result found with id: " + id);
            } catch (PolygonException e) {
                throw new PolygonException("getOrg ResultSet: " +
                        e.getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getOrg error: " + e.getMessage());
        }
    }

    /***
     *
     * @return All organizations
     * @throws PolygonException
     */
    public List<Org> getOrgs() throws PolygonException {
        return getOrgs(-1);
    }

    /***
     *
     * @param n Amount of organizations to return (-1 == all)
     * @return Return n organizations
     * @throws PolygonException
     */
    public List<Org> getOrgs(int n) throws PolygonException {
        String query = "SELECT Id, `Name`, Phone FROM Org";
        if (n > 0)
            query += " LIMIT ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            if (n > 0)
                stmt.setInt(1, n);
            try (ResultSet rs = stmt.executeQuery()) {
                List<Org> list = new ArrayList<>();
                while (rs.next())
                    list.add(constructOrg(rs));
                return list;
            } catch (PolygonException e) {
                throw new PolygonException("getOrgs resultSet Failed: " + e
                        .getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getOrgs failed: " + e.getMessage());
        }
    }

    /***
     *
     * @param o Org to insert
     * @return Id of newly inserted Organization
     * @throws PolygonException
     */
    public int insertOrg(Org o) throws PolygonException {
        String query = "INSERT INTO `Org` (`Name`, Phone ) VALUES (?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement
                .RETURN_GENERATED_KEYS)) {
            stmt.setString(1, o.getName());
            stmt.setString(2, o.getPhone());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next())
                    return rs.getInt(1);
                else
                    throw new PolygonException("insertOrg failed to get " +
                            "generated Id");
            } catch (Exception e) {
                throw new PolygonException("insertOrg failed to " +
                        "insert file: " + e);
            }
        } catch (SQLException e) {
            throw new PolygonException("insertOrg error: " + e
                    .getMessage());
        }
    }

    /***
     *
     * @param o Org to update
     * @return If it Succeeds it returns True, if it fails it returns false
     * or throws an exception
     * @throws PolygonException
     */
    public boolean updateOrg(Org o) throws PolygonException {
        String query = "UPDATE `Org` SET `Name`=?, `Phone`=? WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, o.getName());
            stmt.setString(2, o.getPhone());
            stmt.setInt(3, o.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PolygonException("updateOrg error: " + e
                    .getMessage());
        }
    }

    /***
     *
     * @param rs ResultSet to construct Organization from
     * @return Newly constructed Organization
     * @throws PolygonException
     */
    private Org constructOrg(ResultSet rs) throws PolygonException {
        try {
            Org o = new Org(rs.getInt("Id"));
            o.setName(rs.getString("Name"));
            o.setPhone(rs.getString("Phone"));

            return o;
        } catch (SQLException e) {
            throw new PolygonException("constructOrg error: " + e
                    .getMessage());
        }
    }
}
