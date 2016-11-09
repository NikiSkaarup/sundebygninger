package db;

import java.sql.Connection;

/**
 * Created by Niki on 2016-11-09.
 *
 * @author Niki
 */
public class RoleMapper {

    private static Connection conn;

    public RoleMapper(Connection conn) {
        RoleMapper.conn = conn;
    }
}
