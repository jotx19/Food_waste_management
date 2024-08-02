package DAOImpl;

import DB.DBconnection;
import Models.Items;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryDAOImplTest {
    private InventoryDAOImpl inventoryDAO;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        // Initialize the DAO
        inventoryDAO = new InventoryDAOImpl();

        // Establish database connection
        connection = DBconnection.getConnection();
        connection.setAutoCommit(false); // Use manual commit for tests

        // Create tables
        createTables();

        // Create test data
        prepareTestData();
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Rollback changes to ensure tests don't affect each other
        if (connection != null) {
            connection.rollback();
            DBconnection.closeConnection(connection);
        }
    }

    private void createTables() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Inventory (" +
                "ItemID INT AUTO_INCREMENT PRIMARY KEY, " +
                "ItemName VARCHAR(255) NOT NULL, " +
                "Quantity INT NOT NULL, " +
                "ExpirationDate DATE, " +
                "RetailerPrice DOUBLE, " +
                "IsDonation BOOLEAN, " +
                "IsSale BOOLEAN, " +
                "DiscountPrice DOUBLE" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    private void prepareTestData() throws SQLException {
        String insertTestDataSQL = "INSERT INTO Inventory (ItemName, Quantity, ExpirationDate, RetailerPrice, IsDonation, IsSale, DiscountPrice) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertTestDataSQL)) {
            pstmt.setString(1, "Item1");
            pstmt.setInt(2, 10);
            pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis() + 86400000L * 30)); // 30 days from now
            pstmt.setDouble(4, 50.0);
            pstmt.setBoolean(5, false);
            pstmt.setBoolean(6, false);
            pstmt.setDouble(7, 0.0);
            pstmt.executeUpdate();

            pstmt.setString(1, "Item2");
            pstmt.setInt(2, 5);
            pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis() + 86400000L * 30)); // 30 days from now
            pstmt.setDouble(4, 30.0);
            pstmt.setBoolean(5, true);
            pstmt.setBoolean(6, false);
            pstmt.setDouble(7, 0.0);
            pstmt.executeUpdate();
        }
    }

    @Test
    void testGetInventory() {
        List<Items> items = inventoryDAO.getInventory();
        assertNotNull(items, "The inventory list should not be null.");
        assertTrue(items.size() > 0, "The inventory list should not be empty.");
    }

    @Test
    void testAddItem() {
        Items newItem = new Items(0, "Item3", 20, new Date(), 40.0, false, true, 35.0);
        boolean result = inventoryDAO.addItem(newItem);
        assertTrue(result, "The item should be added successfully.");

        // Verify item was added
        List<Items> items = inventoryDAO.getInventory();
        boolean itemExists = items.stream().anyMatch(item -> "Item3".equals(item.getItemName()));
        assertTrue(itemExists, "The new item should be present in the inventory.");
    }

}
