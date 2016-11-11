/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.Conn;
import domain.Facade;
import exceptions.PolygonException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;
import model.Building;
import model.Org;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.TestHelper;

/**
 *
 * @author Jamie
 */
public class BuildingsTest {

    private Connection conn = null;
    Building b = new Building();
    Facade facade = Facade.getFacade();

    public BuildingsTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws PolygonException {
        try {
            conn = Conn.get("localhost", "junitTestDB", "junitTest", "junitTest");
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(TestHelper.getCloneDBScript());
            }
            facade = Facade.getFacadeWithConn(conn);
        } catch (Exception ex) {
            conn = null;
            System.out.println("Could not open connection to database: " + ex
                    .getMessage());
        }
        b = facade.getBuilding(1);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test
    public void setName() throws PolygonException {
        b.setName("TestBuilding");
        facade.updateBuilding(b);
        assertEquals("TestBuilding", b.getName());

        assertEquals("TestBuilding", facade.getBuilding(1).getName());

    }

    @Test
    public void setAddress() throws PolygonException {
        b.setAddress("TestStreet 42");
        facade.updateBuilding(b);
        assertEquals("TestStreet 42", b.getAddress());

        assertEquals("TestStreet 42", facade.getBuilding(1).getAddress());
    }

    @Test
    public void setCurrentUse() throws PolygonException {
        b.setCurrentUse("TestUse");
        facade.updateBuilding(b);
        assertEquals("TestUse", b.getCurrentUse());

        assertEquals("TestUse", facade.getBuilding(1).getCurrentUse());
    }

    @Test
    public void setPreviousUse() throws PolygonException {
        b.setPreviousUse("TestPUse");
        facade.updateBuilding(b);
        assertEquals("TestPUse", b.getPreviousUse());

        assertEquals("TestPUse", facade.getBuilding(1).getPreviousUse());
    }

    @Test
    public void setArea() throws PolygonException {
        b.setArea("666");
        facade.updateBuilding(b);
        assertEquals("666", b.getArea());

        assertEquals("666", facade.getBuilding(1).getArea());
    }

    @Test
    public void setConstructionYear() throws PolygonException {
        String text = "2011-11-11 11:11:11";
        Timestamp ts = Timestamp.valueOf(text);
        b.setConstructionYear(ts);
        facade.updateBuilding(b);
        assertEquals(ts, b.getConstructionYear());

        assertEquals(ts, facade.getBuilding(1).getConstructionYear());
    }

    @Test   // Org-support not implemented yet
    public void setOrg() throws PolygonException {
        Org o = new Org();
        b.setOrg(o);
        facade.updateBuilding(b);
        assertEquals("TestOrg", b.getOrg().getName());

        assertEquals("TestOrg", facade.getBuilding(1).getOrg().getName());
    }

//    @Test
//    public void addComment() throws PolygonException {
//        List<Report> reportList = new ArrayList();
//        Report r = new Report();
//        r.setBuilding(b);
//        User user = new User();
//        r.setUser(user);
//        Comment c = new Comment();
//        c.setComment("Test comment");
//        List<Comment> lc;
//        lc = new ArrayList();
//        lc.add(c);
//        r.setComments(lc);
//        reportList.add(r);
//        b.setReports(reportList);
//        assertEquals("Test comment", b.getReports().get(b.getReports().size() - 1).getComments().get(b.getReports().get(b.getReports().size() - 1).getComments().size() - 1).getComment());
//
//        facade.insertReport(r);
//        facade.updateBuilding(b);
//        assertEquals("Test comment", facade.getReport(0).getComments().get(0));
//    }
}
