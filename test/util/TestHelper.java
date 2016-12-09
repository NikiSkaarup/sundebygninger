package util;

/**
 * Created by Niki on 2016-11-11.
 *
 * @author Niki
 */
public class TestHelper {
    public static String getCloneDBScript() {
        return "" +
                "DROP SCHEMA IF EXISTS `junitTestDB`;" +
                "CREATE SCHEMA IF NOT EXISTS `junitTestDB` DEFAULT " +
                "CHARACTER SET utf8;" +
                "USE `junitTestDB`;" +
                "GRANT USAGE ON `junitTestDB`.* TO 'junitTest'@'localhost';" +
                "DROP USER 'junitTest'@'localhost';" +
                "GRANT ALL PRIVILEGES ON junitTestDB.* TO " +
                "'junitTest'@'localhost' " +
                "IDENTIFIED BY 'junitTest';" +
                "FLUSH PRIVILEGES;" +
                "CREATE TABLE Org LIKE sundebygninger.Org;" +
                "INSERT INTO Org " +
                "SELECT * FROM sundebygninger.Org;" +
                "CREATE TABLE Role LIKE sundebygninger.Role;" +
                "INSERT INTO Role " +
                "SELECT * FROM sundebygninger.Role;" +
                "CREATE TABLE User LIKE sundebygninger.User;" +
                "INSERT INTO User " +
                "SELECT * FROM sundebygninger.User;" +
                "CREATE TABLE Building LIKE sundebygninger.Building;" +
                "INSERT INTO Building " +
                "SELECT * FROM sundebygninger.Building;" +
                "CREATE TABLE Document LIKE sundebygninger.Document;" +
                "INSERT INTO Document " +
                "SELECT * FROM sundebygninger.Document;" +
                "CREATE TABLE Report LIKE sundebygninger.Report;" +
                "INSERT INTO Report " +
                "SELECT * FROM sundebygninger.Report;" +
                "CREATE TABLE ServiceType LIKE sundebygninger.ServiceType;" +
                "INSERT INTO ServiceType " +
                "SELECT * FROM sundebygninger.ServiceType;" +
                "CREATE TABLE Request LIKE sundebygninger.Request;" +
                "INSERT INTO Request " +
                "SELECT * FROM sundebygninger.Request;" +
                "CREATE TABLE DamageType LIKE sundebygninger.DamageType;" +
                "INSERT INTO DamageType " +
                "SELECT * FROM sundebygninger.DamageType;" +
                "CREATE TABLE Incident LIKE sundebygninger.Incident;" +
                "INSERT INTO Incident " +
                "SELECT * FROM sundebygninger.Incident;" +
                "CREATE TABLE Image LIKE sundebygninger.Image;" +
                "INSERT INTO Image " +
                "SELECT * FROM sundebygninger.Image;" +
                "CREATE TABLE Room LIKE sundebygninger.Room;" +
                "INSERT INTO Room " +
                "SELECT * FROM sundebygninger.Room;" +
                "CREATE TABLE RoomToDamageType LIKE sundebygninger" +
                ".RoomToDamageType;" +
                "INSERT INTO RoomToDamageType " +
                "SELECT * FROM sundebygninger.RoomToDamageType;" +
                "CREATE TABLE CommentType LIKE sundebygninger.CommentType;" +
                "INSERT INTO CommentType " +
                "SELECT * FROM sundebygninger.CommentType;" +
                "CREATE TABLE Comment LIKE sundebygninger.Comment;" +
                "INSERT INTO Comment " +
                "SELECT * FROM sundebygninger.Comment;";
    }
}
