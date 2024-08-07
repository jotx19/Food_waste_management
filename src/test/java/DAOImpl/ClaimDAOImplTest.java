package DAOImpl;

import DAO.ClaimDAO;
import Models.Claims;
import DB.DBconnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ClaimDAOImplTest {
    private Connection connection;
    private ClaimDAO claimDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        // Initialize the connection using DBconnection class
        connection = DBconnection.getConnection();
        claimDAO = new ClaimDAOImpl(connection);
        clearData();
        insertTestData();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        clearData();
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    private void clearData() throws SQLException {
        String deleteClaimsSQL = "DELETE FROM Claims";
        String deleteInventorySQL = "DELETE FROM inventory";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(deleteClaimsSQL);
            stmt.execute(deleteInventorySQL);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    private void insertTestData() throws SQLException {
        String insertInventorySQL = "INSERT INTO inventory (ItemID, ItemName, Quantity) VALUES (1, 'Test Item', 100)";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(insertInventorySQL);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Test
    public void testAddClaim() {
        Claims claim = new Claims(1, new Date(), 10);
        boolean result = claimDAO.addClaim(claim);
        assertTrue(result, "Claim should be added successfully.");

        int quantityClaimed = claimDAO.getQuantityClaimed(1);
        assertEquals(10, quantityClaimed, "Quantity claimed should be 10.");
    }

    @Test
    public void testAddOrUpdateExistingClaim() {
        Claims claim = new Claims(1, new Date(), 10);
        claimDAO.addClaim(claim);

        Claims newClaim = new Claims(1, new Date(), 20);
        boolean result = claimDAO.addOrUpdate(newClaim);
        assertTrue(result, "Claim should be updated successfully.");

        int quantityClaimed = claimDAO.getQuantityClaimed(1);
        assertEquals(30, quantityClaimed, "Quantity claimed should be 30 after update.");
    }

    @Test
    public void testAddOrUpdateNewClaim() {
        Claims claim = new Claims(1, new Date(), 10);
        boolean result = claimDAO.addOrUpdate(claim);
        assertTrue(result, "New claim should be added successfully.");

        int quantityClaimed = claimDAO.getQuantityClaimed(1);
        assertEquals(10, quantityClaimed, "Quantity claimed should be 10.");
    }

    @Test
    public void testExistingClaim() {
        Claims claim = new Claims(1, new Date(), 10);
        claimDAO.addClaim(claim);

        boolean exists = claimDAO.existingClaim(1);
        assertTrue(exists, "Claim should exist in the database.");
    }

    @Test
    public void testUpdateQuantity() {
        Claims claim = new Claims(1, new Date(), 10);
        claimDAO.addClaim(claim);

        boolean result = claimDAO.updateQuantity(1, 15);
        assertTrue(result, "Quantity should be updated successfully.");

        int quantityClaimed = claimDAO.getQuantityClaimed(1);
        assertEquals(15, quantityClaimed, "Quantity claimed should be 15 after update.");
    }

    @Test
    public void testGetQuantityClaimed() {
        Claims claim = new Claims(1, new Date(), 10);
        claimDAO.addClaim(claim);

        int quantityClaimed = claimDAO.getQuantityClaimed(1);
        assertEquals(10, quantityClaimed, "Quantity claimed should be 10.");
    }
}
