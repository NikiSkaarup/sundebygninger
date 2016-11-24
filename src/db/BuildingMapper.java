package db;

import model.Building;
import model.Org;
import exceptions.PolygonException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Niki on 2016-11-09.
 *
 * @author Niki
 */
public class BuildingMapper {

    private static Connection conn;

    public BuildingMapper(Connection conn) {
        BuildingMapper.conn = conn;
    }

    public Building getBuilding(int id) throws PolygonException {
        String query = "SELECT Building.Id, Building.`Name`, Address, "
                + "ConstructionYear, FkOrgId, CurrentUse, Area, PreviousUse, "
                + "Org.Name FROM `Building` INNER JOIN Org ON FkOrgId = Org.Id "
                + "  WHERE Building.Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return constructBuilding(rs);
            } else {
                throw new PolygonException("The element is not found with id:" +
                                                   " " + id);
            }
        } catch (SQLException e) {
            throw new PolygonException("getBuilding error: " + e.getMessage());
        }
    }

    public List<Building> getBuildings(Org org) throws PolygonException {
        return getBuildings(org, -1);
    }

    public List<Building> getBuildings(Org org, int count) throws
            PolygonException {
        String query = "SELECT Id, `Name`, Address, ConstructionYear, "
                + "CurrentUse, Area, PreviousUse, FkOrgId FROM `Building`";
        if (org != null) {
            query += " WHERE FkOrgId=?";
        }
        query += " ORDER BY Submission DESC";
        if (org != null && count > 0) {
            query += " LIMIT ?";
        }
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            List<Building> list = new ArrayList<>();
            if (org != null) {
                stmt.setInt(1, org.getId());
                if (count > 0) {
                    stmt.setInt(2, count);
                }
            }
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(constructBuilding(rs));
                }
                rs.close();
                stmt.close();
                return list;
            } catch (PolygonException e) {
                throw new PolygonException("getBuildings: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getBuildings error: " + e.getMessage());
        }
    }

    public int insertBuilding(Building b) throws PolygonException {
        int id = -1;
        String query = "INSERT INTO `Building` (`Name`, Address, "
                + "ConstructionYear, CurrentUse, Area, PreviousUse, FkOrgId) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement
                .RETURN_GENERATED_KEYS)) {
            stmt.setString(1, b.getName());
            stmt.setString(2, b.getAddress());
            stmt.setTimestamp(3, b.getConstructionYear());
            stmt.setString(4, b.getCurrentUse());
            stmt.setString(5, b.getArea());
            stmt.setString(6, b.getPreviousUse());
            stmt.setInt(7, b.getOrg().getId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                } else {
                    throw new PolygonException("failed to get insertBuilding " +
                                                       "generated id");
                }
            } catch (Exception e) {
                throw new PolygonException("insertBuilding failed to insert " +
                                                   "building: " + e);
            }
        } catch (SQLException e) {
            throw new PolygonException("insertBuilding error: " + e
                    .getMessage());
        }
        return id;
    }

    public boolean updateBuilding(Building b) throws PolygonException {
        String query = "UPDATE `Building` SET `Name`=?, Address=?, "
                + "ConstructionYear=?, CurrentUse=?, Area=?, PreviousUse=?, "
                + "FkOrgId=? WHERE Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
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
            throw new PolygonException("updateBuilding error: " + e
                    .getMessage());
        }
    }

    private Building constructBuilding(ResultSet rs) throws PolygonException {
        try {
            Building c = new Building();
            c.setId(rs.getInt("Building.Id"));
            c.setName(rs.getString("Building.Name"));
            c.setAddress(rs.getString("Address"));
            c.setConstructionYear(rs.getTimestamp("ConstructionYear"));
            c.setCurrentUse(rs.getString("CurrentUse"));
            c.setArea(rs.getString("Area"));
            c.setPreviousUse(rs.getString("PreviousUse"));

            //reference to 1 building
            Org org = new Org();
            org.setId(rs.getInt("FkOrgId"));
            try {
                org.setName(rs.getString("Org.Name"));
            } catch (SQLException ignored) {
                org.setName("");
            }
            c.setOrg(org);
            return c;
        } catch (SQLException e) {
            throw new PolygonException("failed to constructBuilding: " + e
                    .getMessage());
        }
    }

    public List<Building> getBuildingsLimit(int limit) throws PolygonException {
        String query = "SELECT Id, `Name`, Address, ConstructionYear, " +
                "CurrentUse, Area, PreviousUse, FkOrgId FROM `Building` ORDER" +
                " BY Submission DESC LIMIT ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            List<Building> list = new ArrayList<>();
            stmt.setInt(1, limit);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next())
                    list.add(constructBuilding(rs));
                return list;
            } catch (PolygonException e) {
                throw new PolygonException("getBuildingsLimit: " + e
                        .getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getBuildingsLimit error: " + e
                    .getMessage());
        }
    }
}
