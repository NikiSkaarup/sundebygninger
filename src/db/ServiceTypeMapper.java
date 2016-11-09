package db;

import model.ServiceType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niki on 2016-11-09.
 *
 * @author Niki
 */
public class ServiceTypeMapper {

    private static Connection conn;

    public ServiceTypeMapper(Connection conn) {
        ServiceTypeMapper.conn = conn;
    }

    public List<ServiceType> getServiceTypes() {
        String query = "SELECT Id, `Name` FROM `ServiceType`";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            List<ServiceType> list = new ArrayList<>();
            while (rs.next())
                list.add(constructServiceType(rs));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ServiceType constructServiceType(ResultSet rs) {
        try {
            ServiceType c = new ServiceType();
            c.setId(rs.getInt("Id"));
            c.setName(rs.getString("Name"));
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
