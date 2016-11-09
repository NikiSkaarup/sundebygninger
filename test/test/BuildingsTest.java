/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import domain.Facade;
import model.Building;
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
    public void hello() {
        b.setName("TestBuilding");
        facade.updateBuilding(b);
        assertEquals("TestBuilding", b.getName());
        
        assertEquals("TestBuilding", facade.getBuilding(1).getName());
    }
}
