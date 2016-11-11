package db;

import domain.Facade;
import org.junit.Before;
import org.junit.Test;
import util.TestHelper;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Niki on 2016-11-10.
 *
 * @author Niki
 */
public class DocumentMapperTest {

    private Connection conn = null;
    private Facade facade;

    @Before
    public void setUp() throws Exception {
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
    }

    @Test
    public void getDocument() throws Exception {

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
    public void insertDocument() throws Exception {

    }

    @Test
    public void updateDocument() throws Exception {

    }

}