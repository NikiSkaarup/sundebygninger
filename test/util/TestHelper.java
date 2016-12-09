package util;

/**
 * Created by Niki on 2016-11-11.
 *
 * @author Niki
 */
public class TestHelper {
    public static String getCloneDBScript() {
        return "" +
                "DROP SCHEMA IF EXISTS `junitTestDB` ;" +
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
                "CREATE TABLE RoomToDamageType LIKE sundebygninger" +
                "CREATE TABLE CommentType LIKE sundebygninger.CommentType;" +
                "CREATE TABLE Comment LIKE sundebygninger.Comment;" +
                "INSERT INTO Org " +
                "SELECT * FROM sundebygninger.Org;" +
                "INSERT INTO Role " +
                "SELECT * FROM sundebygninger.Role;" +
                "INSERT INTO User " +
                "SELECT * FROM sundebygninger.User;" +
                "INSERT INTO Building " +
                "SELECT * FROM sundebygninger.Building;" +
                "INSERT INTO FileType " +
                "SELECT * FROM sundebygninger.FileType;" +
                "INSERT INTO File " +
                "SELECT * FROM sundebygninger.File;" +
                "INSERT INTO Report " +
                "SELECT * FROM sundebygninger.Report;" +
                "INSERT INTO ServiceType " +
                "SELECT * FROM sundebygninger.ServiceType;" +
                "INSERT INTO Request " +
                "SELECT * FROM sundebygninger.Request;" +
                "INSERT INTO DamageType " +
                "SELECT * FROM sundebygninger.DamageType;" +
                "INSERT INTO Incident " +
                "SELECT * FROM sundebygninger.Incident;" +
                "INSERT INTO Room " +
                "SELECT * FROM sundebygninger.Room;" +
                ".RoomToDamageType;" +
                "INSERT INTO RoomToDamageType " +
                "SELECT * FROM sundebygninger.RoomToDamageType;" +
                "INSERT INTO CommentType " +
                "SELECT * FROM sundebygninger.CommentType;" +
                "INSERT INTO Comment " +
                "SELECT * FROM sundebygninger.Comment;";
    }
}
