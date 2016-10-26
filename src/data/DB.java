package data;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Niki on 2016-10-26.
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
                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUser(user);
                dataSource.setPassword(password);
                dataSource.setServerName(ip);
                dataSource.setDatabaseName(db);
                conn = dataSource.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
