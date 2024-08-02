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
        createTables();
        claimDAO = new ClaimDAOImpl(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            dropTables();
            connection.close();
        }
    }

    private void createTables() throws SQLException {
        String createTableSQL = "CREATE TABLE Claims (" +
                "ClaimID INT AUTO_INCREMENT PRIMARY KEY, " +
                "ItemID INT NOT NULL, " +
                "ClaimDate TIMESTAMP NOT NULL, " +
                "QuantityClaimed INT NOT NULL)";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(createTableSQL);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    private void dropTables() throws SQLException {
        String dropTableSQL = "DROP TABLE Claims";
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(dropTableSQL);
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
