package util;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Niki on 2016-11-11.
 *
 * @author Niki
 */
public class TestHelper {
    
    public static void runDBScript(Statement stmt) throws SQLException {
        stmt.execute("DROP SCHEMA IF EXISTS `junitTestDB` ;");                
                stmt.execute("CREATE SCHEMA IF NOT EXISTS `junitTestDB` DEFAULT CHARACTER SET utf8 ;");
                stmt.execute("USE `junitTestDB` ;");
                stmt.execute("CREATE TABLE Org LIKE sundebygninger.Org;");
                stmt.execute("CREATE TABLE Role LIKE sundebygninger.Role;");
                stmt.execute("CREATE TABLE User LIKE sundebygninger.User;");
                stmt.execute("CREATE TABLE Building LIKE sundebygninger.Building;");
                stmt.execute("CREATE TABLE FileType LIKE sundebygninger.FileType;");
                stmt.execute("CREATE TABLE File LIKE sundebygninger.File;");
                stmt.execute("CREATE TABLE Report LIKE sundebygninger.Report;");
                stmt.execute("CREATE TABLE ServiceType LIKE sundebygninger.ServiceType;");
                stmt.execute("CREATE TABLE Request LIKE sundebygninger.Request;");
                stmt.execute("CREATE TABLE DamageType LIKE sundebygninger.DamageType;");
                stmt.execute("CREATE TABLE Incident LIKE sundebygninger.Incident;");
                stmt.execute("CREATE TABLE Room LIKE sundebygninger.Room;");
                stmt.execute("CREATE TABLE RoomToDamageType LIKE sundebygninger.RoomToDamageType");
                stmt.execute("CREATE TABLE CommentType LIKE sundebygninger.CommentType;");
                stmt.execute("CREATE TABLE Comment LIKE sundebygninger.Comment;");
                stmt.execute("INSERT INTO junittestdb.Org SELECT * FROM sundebygninger.Org;");
                stmt.execute("INSERT INTO junittestdb.Role SELECT * FROM sundebygninger.Role;");
                stmt.execute("INSERT INTO junittestdb.User SELECT * FROM sundebygninger.User;");
                stmt.execute("INSERT INTO junittestdb.Building SELECT * FROM sundebygninger.Building;");
                stmt.execute("INSERT INTO FileType SELECT * FROM sundebygninger.FileType;");
                stmt.execute("INSERT INTO File SELECT * FROM sundebygninger.File;");
                stmt.execute("INSERT INTO Report SELECT * FROM sundebygninger.Report;");
                stmt.execute("INSERT INTO ServiceType SELECT * FROM sundebygninger.ServiceType;");
                stmt.execute("INSERT INTO Request SELECT * FROM sundebygninger.Request;");
                stmt.execute("INSERT INTO DamageType SELECT * FROM sundebygninger.DamageType;");
                stmt.execute("INSERT INTO Incident SELECT * FROM sundebygninger.Incident;");
                stmt.execute("INSERT INTO Room SELECT * FROM sundebygninger.Room;");
                stmt.execute("INSERT INTO RoomToDamageType SELECT * FROM sundebygninger.RoomToDamageType;");
                stmt.execute("INSERT INTO CommentType SELECT * FROM sundebygninger.CommentType;");
                stmt.execute("INSERT INTO Comment SELECT * FROM sundebygninger.Comment;");
    }
    public static String getCloneDBScript() {
        return "" +
                "DROP SCHEMA IF EXISTS `junittest` ;" +
                "CREATE SCHEMA IF NOT EXISTS `junitTestDB` DEFAULT " +
                "CHARACTER SET utf8 ;" +
                "USE `junitTestDB` ;" +
                "GRANT USAGE ON `junitTestDB`.* TO 'junitTest'@'localhost' ;" +
                "DROP USER 'junitTest'@'localhost' ;" +
                "GRANT ALL PRIVILEGES ON `junitTestDB`.* TO " +
                "'junitTest'@'localhost' " +
                "IDENTIFIED BY 'junitTest' ;" +
                "FLUSH PRIVILEGES ;" +
                
                "CREATE TABLE Org LIKE sundebygninger.Org;" +
                "CREATE TABLE Role LIKE sundebygninger.Role;" +
                "CREATE TABLE User LIKE sundebygninger.User;" +
                "CREATE TABLE Building LIKE sundebygninger.Building;" +
                "CREATE TABLE FileType LIKE sundebygninger.FileType;" +
                "CREATE TABLE File LIKE sundebygninger.File;" +
                "CREATE TABLE Report LIKE sundebygninger.Report;" +
                "CREATE TABLE ServiceType LIKE sundebygninger.ServiceType;" +
                "CREATE TABLE Request LIKE sundebygninger.Request;" +
                "CREATE TABLE DamageType LIKE sundebygninger.DamageType;" +
                "CREATE TABLE Incident LIKE sundebygninger.Incident;" +
                "CREATE TABLE Room LIKE sundebygninger.Room;" +
                "CREATE TABLE RoomToDamageType LIKE sundebygninger.RoomToDamageType;" +
                "CREATE TABLE CommentType LIKE sundebygninger.CommentType;" +
                "CREATE TABLE Comment LIKE sundebygninger.Comment;" +
                
                "INSERT INTO junittestdb.Org SELECT * FROM sundebygninger.Org;" +
                "INSERT INTO junittestdb.Role SELECT * FROM sundebygninger.Role;" +
                "INSERT INTO junittestdb.User SELECT * FROM sundebygninger.User;" +
                "INSERT INTO Building SELECT * FROM sundebygninger.Building;" +
                "INSERT INTO FileType SELECT * FROM sundebygninger.FileType;" +
                "INSERT INTO File SELECT * FROM sundebygninger.File;" +
                "INSERT INTO Report SELECT * FROM sundebygninger.Report;" +
                "INSERT INTO ServiceType SELECT * FROM sundebygninger.ServiceType;" +
                "INSERT INTO Request SELECT * FROM sundebygninger.Request;" +
                "INSERT INTO DamageType SELECT * FROM sundebygninger.DamageType;" +
                "INSERT INTO Incident SELECT * FROM sundebygninger.Incident;" +
                "INSERT INTO Room SELECT * FROM sundebygninger.Room;" +                
                "INSERT INTO RoomToDamageType SELECT * FROM sundebygninger.RoomToDamageType;" +
                "INSERT INTO CommentType SELECT * FROM sundebygninger.CommentType;" +
                "INSERT INTO Comment SELECT * FROM sundebygninger.Comment;";
    }
}
