package DB;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DBconnectionTest {

    @BeforeAll
    public static void setUp() {
        // Set up mock properties or configuration before any tests are run
        Properties mockProperties = new Properties();
        mockProperties.setProperty("jdbc.driverClassName", "com.mysql.cj.jdbc.Driver");
        mockProperties.setProperty("jdbc.url", "jdbc:mysql://localhost:3306/fwrp");
        mockProperties.setProperty("jdbc.username", "root");
        mockProperties.setProperty("jdbc.password", "root");
        // Mock the FileInputStream or use a test configuration file
    }

    @AfterAll
    public static void tearDown() {
        // Clean up after all tests are complete if needed
    }

    @Test
    public void testGetConnection() {
        try {
            Connection connection = DBconnection.getConnection();
            assertNotNull(connection, "Connection should not be null.");
            DBconnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            // Fail the test if an exception is thrown
            assertTrue(false, "SQLException should not be thrown.");
        }
    }

    @Test
    public void testCloseConnection() {
        Connection connection = null;
        try {
            connection = DBconnection.getConnection();
            DBconnection.closeConnection(connection);
            // Verify the connection is closed
            assertTrue(connection.isClosed(), "Connection should be closed.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Fail the test if an exception is thrown
            assertTrue(false, "SQLException should not be thrown.");
        }
    }
}
