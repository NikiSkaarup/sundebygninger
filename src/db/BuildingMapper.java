package db;

import model.Building;
import model.Org;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public Building getBuilding(int id) {
        String query = "SELECT Building.Id, Building.`Name`, Address, " +
                "ConstructionYear, FkOrgId, CurrentUse, Area, PreviousUse, " +
                "Org.Name FROM `Building` INNER JOIN Org ON FkOrgId = Org.Id " +
                "  WHERE Building.Id=?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
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
        String query = "SELECT Id, `Name`, Address, ConstructionYear, " +
                "CurrentUse, Area, PreviousUse, FkOrgId FROM `Building`";
        if (org != null)
            query += " WHERE FkOrgId=?";
        query += " ORDER BY Submission DESC";
        if (org != null && count > 0)
            query += " LIMIT ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            List<Building> list = new ArrayList<>();
            if (org != null) {
                stmt.setInt(1, org.getId());
                if (count > 0)
                    stmt.setInt(2, count);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                list.add(constructBuilding(rs));
            rs.close();
            stmt.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertBuilding(Building b) {
        int id = -1;
        String query = "INSERT INTO `Building` (`Name`, Address, " +
                "ConstructionYear, CurrentUse, Area, PreviousUse, FkOrgId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement
                .RETURN_GENERATED_KEYS)) {
            stmt.setString(1, b.getName());
            stmt.setString(2, b.getAddress());
            stmt.setTimestamp(3, b.getConstructionYear());
            stmt.setString(4, b.getCurrentUse());
            stmt.setString(5, b.getArea());
            stmt.setString(6, b.getPreviousUse());
            stmt.setInt(7, b.getOrg().getId());
            int changed = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt("Id");
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean updateBuilding(Building b) {
        String query = "UPDATE `Building` SET `Name`=?, Address=?, " +
                "ConstructionYear=?, CurrentUse=?, Area=?, PreviousUse=?, " +
                "FkOrgId=? WHERE Id=?";
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
            e.printStackTrace();
        }
        return false;
    }

    private Building constructBuilding(ResultSet rs) {
        try {
            Building c = new Building();
            c.setId(rs.getInt("Building.Id"));
            c.setName(rs.getString("Building.Name"));
            c.setAddress(rs.getString("Address"));
            c.setConstructionYear(rs.getTimestamp("ConstructionYear"));
            c.setCurrentUse(rs.getString("CurrentUse"));
            c.setArea(rs.getString("Area"));
            c.setPreviousUse(rs.getString("PreviousUse"));

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
            e.printStackTrace();
        }
        return null;
    }
}