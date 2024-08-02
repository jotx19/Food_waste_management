package DAOImpl;

import DB.DBconnection;
import Models.Subscription;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionDAOImplTest {
    private Connection connection;
    private SubscriptionDAOImpl subscriptionDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        // Initialize the connection using DBconnection class
        connection = DBconnection.getConnection();
        createTables();
        subscriptionDAO = new SubscriptionDAOImpl(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            dropTables();
            connection.close();
        }
    }

    private void createTables() throws SQLException {
        String createTableSQL = "CREATE TABLE Subscriptions (" +
                "SubscriptionID INT AUTO_INCREMENT PRIMARY KEY, " +
                "UserID INT NOT NULL, " +
                "CommunicationMethod VARCHAR(255) NOT NULL, " +
                "Location VARCHAR(255) NOT NULL, " +
                "FoodPreferences VARCHAR(255) NOT NULL, " +
                "Email VARCHAR(255) NOT NULL)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    private void dropTables() throws SQLException {
        String dropTableSQL = "DROP TABLE Subscriptions";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(dropTableSQL);
        }
    }

    @Test
    public void testAddSubscription() {
        Subscription subscription = new Subscription();
        subscription.setUserID(1);
        subscription.setmoc("Email");
        subscription.setLocation("Test Location");
        subscription.setFoodPreferences("Vegan");
        subscription.setEmail("test@example.com");

        boolean result = subscriptionDAO.addSubscription(subscription);
        assertTrue(result, "Subscription should be added successfully.");

        List<Subscription> subscriptions;
        try {
            subscriptions = subscriptionDAO.getAllSubscriptions();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertFalse(subscriptions.isEmpty(), "Subscription list should not be empty after adding.");
        Subscription addedSubscription = subscriptions.get(0);
        assertEquals("test@example.com", addedSubscription.getEmail(), "Email should match.");
    }

    @Test
    public void testGetAllSubscriptions() {
        Subscription subscription = new Subscription();
        subscription.setUserID(1);
        subscription.setmoc("Email");
        subscription.setLocation("Test Location");
        subscription.setFoodPreferences("Vegan");
        subscription.setEmail("test@example.com");
        subscriptionDAO.addSubscription(subscription);

        List<Subscription> subscriptions;
        try {
            subscriptions = subscriptionDAO.getAllSubscriptions();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertFalse(subscriptions.isEmpty(), "Subscription list should not be empty.");
        Subscription retrievedSubscription = subscriptions.get(0);
        assertEquals("test@example.com", retrievedSubscription.getEmail(), "Email should match.");
    }
}
