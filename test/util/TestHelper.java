package util;

/**
 * Created by Niki on 2016-11-11.
 *
 * @author Niki
 */
public class TestHelper {
    public static String getCloneDBScript() {
        return "" +
                "DROP SCHEMA IF EXISTS junitTestDB;\n" +
                "GRANT USAGE ON junitTestDB.* TO 'junitTest'@'localhost';\n" +
                "DROP USER 'junitTest'@'localhost';\n" +
                "GRANT ALL PRIVILEGES ON junitTestDB.* TO " +
                "'junitTest'@'localhost' " +
                "IDENTIFIED BY 'junitTest';\n" +
                "FLUSH PRIVILEGES;\n" +
                "USE junitTestDB;\n" +
                "CREATE TABLE Org LIKE sundebygninger.Org;\n" +
                "INSERT INTO Org " +
                "SELECT * FROM sundebygninger.Org;\n" +
                "CREATE TABLE Role LIKE sundebygninger.Role;\n" +
                "INSERT INTO Role " +
                "SELECT * FROM sundebygninger.Role;\n" +
                "CREATE TABLE User LIKE sundebygninger.User;\n" +
                "INSERT INTO User " +
                "SELECT * FROM sundebygninger.User;\n" +
                "CREATE TABLE Building LIKE sundebygninger.Building;\n" +
                "INSERT INTO Building " +
                "SELECT * FROM sundebygninger.Building;\n" +
                "CREATE TABLE Document LIKE sundebygninger.Document;\n" +
                "INSERT INTO Document " +
                "SELECT * FROM sundebygninger.Document;\n" +
                "CREATE TABLE Report LIKE sundebygninger.Report;\n" +
                "INSERT INTO Report " +
                "SELECT * FROM sundebygninger.Report;\n" +
                "CREATE TABLE ServiceType LIKE sundebygninger.ServiceType;\n" +
                "INSERT INTO ServiceType " +
                "SELECT * FROM sundebygninger.ServiceType;\n" +
                "CREATE TABLE Request LIKE sundebygninger.Request;\n" +
                "INSERT INTO Request " +
                "SELECT * FROM sundebygninger.Request;\n" +
                "CREATE TABLE DamageType LIKE sundebygninger.DamageType;\n" +
                "INSERT INTO DamageType " +
                "SELECT * FROM sundebygninger.DamageType;\n" +
                "CREATE TABLE Incident LIKE sundebygninger.Incident;\n" +
                "INSERT INTO Incident " +
                "SELECT * FROM sundebygninger.Incident;\n" +
                "CREATE TABLE Image LIKE sundebygninger.Image;\n" +
                "INSERT INTO Image " +
                "SELECT * FROM sundebygninger.Image;\n" +
                "CREATE TABLE Room LIKE sundebygninger.Room;\n" +
                "INSERT INTO Room " +
                "SELECT * FROM sundebygninger.Room;\n" +
                "CREATE TABLE RoomToDamageType LIKE sundebygninger" +
                ".RoomToDamageType;\n" +
                "INSERT INTO RoomToDamageType " +
                "SELECT * FROM sundebygninger.RoomToDamageType;\n" +
                "CREATE TABLE CommentType LIKE sundebygninger.CommentType;\n" +
                "INSERT INTO CommentType " +
                "SELECT * FROM sundebygninger.CommentType;\n" +
                "CREATE TABLE Comment LIKE sundebygninger.Comment;\n" +
                "INSERT INTO Comment " +
                "SELECT * FROM sundebygninger.Comment;\n";
    }
}
