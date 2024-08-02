package DAOImpl;

import DB.DBconnection;
import DAO.UserDAO;
import DTO.Types;
import DTO.userdto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
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
        createTables();
        userDAO = new UserDAOImpl();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            dropTables();
            connection.close();
        }
    }

    private void createTables() throws SQLException {
        String createTableSQL = "CREATE TABLE Users (" +
                "UserID INT AUTO_INCREMENT PRIMARY KEY, " +
                "Name VARCHAR(255) NOT NULL, " +
                "Email VARCHAR(255) UNIQUE NOT NULL, " +
                "Password VARCHAR(255) NOT NULL, " +
                "UserType VARCHAR(50) NOT NULL)";
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
        String dropTableSQL = "DROP TABLE Users";
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
    public void testRegisterUser() throws SQLException {
        userdto user = new userdto("Test User", "test@example.com", "password", Types.Consumer);
        int userId = userDAO.registerUser(user);
        assertTrue(userId > 0, "User ID should be greater than 0.");

        // Additional code to verify user registration can be added here
    }
}
