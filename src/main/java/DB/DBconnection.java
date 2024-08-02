package DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The DBconnection class is responsible for managing database connections.
 * It loads database configuration properties, initializes the JDBC driver,
 * and provides methods for obtaining and closing database connections.
 */
public class DBconnection {
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream input = new FileInputStream("C:/Users/14375/Documents/NetBeansProjects/WebApplication/data/database.properties")) {
            properties.load(input);
            Class.forName(properties.getProperty("jdbc.driverClassName"));
        } catch (IOException | ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Failed to initialize database connection: " + e.getMessage());
        }
    }

    /**
     * Retrieves a connection to the database using the properties loaded from the configuration file.
     *
     * This method uses the JDBC DriverManager to establish a connection to the database with the
     * URL, username, and password specified in the properties file.
     *
     * @return A Connection object representing the database connection.
     * @throws SQLException if a database access error occurs or the URL is invalid.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password")
        );
    }

    /**
     * Closes the provided database connection.
     *
     * This method ensures that the connection is closed properly, releasing any database resources
     * associated with it. If an error occurs while closing the connection, it is logged to the console.
     *
     * @param connection The Connection object to be closed. If it is null, no action is taken.
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
