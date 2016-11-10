/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import domain.Facade;
import java.util.ArrayList;
import java.util.List;
import model.Building;
import model.Comment;
import model.Report;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jamie
 */
public class BuildingsTest {

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
    public void setUp() {
        b = facade.getBuilding(1);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    @Test
    public void changeName() {
        b.setName("TestBuilding");
        facade.updateBuilding(b);
        assertEquals("TestBuilding", b.getName());

        assertEquals("TestBuilding", facade.getBuilding(1).getName());

    }

    @Test
    public void changeAddress() {
        b.setAddress("TestStreet 42");
        facade.updateBuilding(b);
        assertEquals("TestStreet 42", b.getAddress());

        assertEquals("TestStreet 42", facade.getBuilding(1).getAddress());
    }

    @Test
    public void addComment() {
        List<Report> reportList = new ArrayList();
        Report r = new Report();
        r.setBuilding(b);
        User user = new User();
        r.setUser(user);
        Comment c = new Comment();
        c.setComment("Test comment");
        List<Comment> lc;
        lc = new ArrayList();
        lc.add(c);
        r.setComments(lc);
        reportList.add(r);
        b.setReports(reportList);
        assertEquals("Test comment", b.getReports().get(b.getReports().size() - 1).getComments().get(b.getReports().get(b.getReports().size() - 1).getComments().size() - 1).getComment());

        int insertReport = facade.insertReport(r);
        facade.updateBuilding(b);
        assertEquals("Test comment", facade.getBuilding(1).getReports().get(b.getReports().size() - 1).getComments().get(b.getReports().get(b.getReports().size() - 1).getComments().size() - 1).getComment());

    }
}
