package db;

import domain.Facade;
import exceptions.PolygonException;
import model.Building;
import model.Document;
import org.junit.*;
import util.TestHelper;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Niki on 2016-11-10.
 *
 * @author Niki
 */
public class DocumentMapperTest {

    private Facade facade;

    @Before
    public void setUp() throws Exception {
        try {
            Connection conn = Conn.get("localhost", "junitTestDB", "junitTest",
                    "junitTest");
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(TestHelper.getCloneDBScript());
            }
            facade = Facade.getFacadeWithConn(conn);
        } catch (Exception ex) {
            System.out.println("Could not open connection to database: " + ex
                    .getMessage());
        }
    }

    @Test
    public void getDocuments() throws Exception {

    }

    @Test
    public void getDocuments1() throws Exception {

    }

    @Test
    public void getDocuments2() throws Exception {

    }

    @Test
    public void insertAndGetDocument() throws Exception {
        Document d1 = new Document();
        d1.setPath("junitDocument.doc");
        Building b = new Building();
        b.setId(1);
        d1.setBuilding(b);
        d1.setName("junitDocument.doc");

        int id = -1;
        try {
            id = facade.insertDocument(d1);
        } catch (PolygonException e) {
            Assert.fail("Insert Document Failed");
        }

        Document d2 = null;
        try {
            Assert.assertTrue("Id must be above 0: ", id > 0);
            d2 = facade.getDocument(id);
        } catch (PolygonException e) {
            Assert.fail("Get Document Failed");
        }

        Assert.assertEquals("D1 name should be the same as D2 name",
                d1.getName(), d2.getName());
    }

    @Test
    public void updateDocument() throws Exception {

    }

}