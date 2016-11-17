package db;

import exceptions.PolygonException;
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

    public List<ServiceType> getServiceTypes() throws PolygonException {
        String query = "SELECT Id, `Name` FROM `ServiceType`";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                List<ServiceType> list = new ArrayList<>();
                while (rs.next())
                    list.add(constructServiceType(rs));
                return list;
            } catch (PolygonException e) {
                throw new PolygonException("getServiceTypes: " + e.getMessage());
            }
        } catch (SQLException e) {
            throw new PolygonException("getServiceTypes error: " + e.getMessage());
        }
    }

    private ServiceType constructServiceType(ResultSet rs) throws
            PolygonException {
        try {
            ServiceType c = new ServiceType();
            c.setId(rs.getInt("Id"));
            c.setName(rs.getString("Name"));
            return c;
        } catch (SQLException e) {
            throw new PolygonException("constructServiceType error: " + e
                    .getMessage());
        }
    }
}
