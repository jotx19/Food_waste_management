package DAOImpl;

import DB.DBconnection;
import DAO.UserDAO;
import DTO.Types;
import DTO.userdto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOImplTest {
    private Connection connection;
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        // Initialize the connection using DBconnection class
        connection = DBconnection.getConnection();
        userDAO = new UserDAOImpl();

        // Prepare test data
        clearTestData();
        insertTestData();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            // Clean up test data
            clearTestData();
            connection.close();
        }
    }

    private void clearTestData() throws SQLException {
        String deleteTestDataSQL = "DELETE FROM Users WHERE Email LIKE 'test_%@example.com'";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(deleteTestDataSQL);
        }
    }

    private void insertTestData() throws SQLException {
        String insertTestDataSQL = "INSERT INTO Users (Name, Email, Password, UserType) VALUES " +
                "('Test User 1', 'test_user1@example.com', 'password1', 'consumer'), " +
                "('Test User 2', 'test_user2@example.com', 'password2', 'retailer')";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(insertTestDataSQL);
        }
    }

    @Test
    public void testRegisterUser() throws SQLException {
        userdto user = new userdto("Test User", "test_new@example.com", "password", Types.Consumer);
        int userId = userDAO.registerUser(user);
        assertTrue(userId > 0, "User ID should be greater than 0.");

        // Additional verification of user registration
        assertTrue(isUserPresent("test_new@example.com"), "Newly registered user should exist in the database.");
    }

    private boolean isUserPresent(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM Users WHERE Email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
