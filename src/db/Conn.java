package db;

import java.sql.*;

/**
 * Database Connection class which can handle development and tests
 *
 * @author Niki
 */
public class Conn {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String ip = "localhost";
    private static String db = "sundebygninger";
    private static int port = 3306;
    private static String user = "test";
    private static String password = "password";

    private static Connection conn = null;

    public static Connection get() {
        if (conn == null) {
            try {
                Class.forName(driver);
                String url = "jdbc:mysql://" + ip + ":" + port + "/" + db;
                url += "?useUnicode=true&amp;characterEncoding=UTF8";
                conn = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static Connection get(String ip, String db,
                                 String user, String password) {
        try {
            Class.forName(driver);
            String url = "jdbc:mysql://" + ip + ":" + port + "/" + db;
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
