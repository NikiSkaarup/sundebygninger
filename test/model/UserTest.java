/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domain.Facade;
import exceptions.PolygonException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Tanja
 */
public class UserTest {
    
    User u = new User();
    Facade facade = Facade.getFacade();
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws PolygonException {
        u = facade.getUserLogin("hefle@hvikom.dk", "Henny42!");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void getEmail() throws PolygonException {
        u.setEmail("TestUser");
        facade.updateUser(u);
        assertEquals("TestUser",u.getEmail()); 
        assertEquals("TestUser", facade.getUserLogin("hefle@hvikom.dk","Henny42!").getEmail());
    }
    @Test
    public void getUsers() throws PolygonException {
        u.getOrg();
    
    
    }
    
}
