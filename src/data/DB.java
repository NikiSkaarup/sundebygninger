package data;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * Deprecated class, use db.Conn instead which supports tests
 *
 * @author Niki
 */
public class DB {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String ip = "localhost";
    private static String db = "sundebygninger";
    private static int port = 3306;
    private static String url = "jdbc:mysql://" + ip + ":" + port + "/" + db;
    private static String user = "test";
    private static String password = "password";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
