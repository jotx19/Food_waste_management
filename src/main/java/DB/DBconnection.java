/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconnection {
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream input = new FileInputStream("C:/Users/LENOVO/Desktop/java/Food_waste_management/data/database.properties")) {
            properties.load(input);
            Class.forName(properties.getProperty("jdbc.driverClassName"));
        } catch (IOException | ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Failed to initialize database connection: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            properties.getProperty("jdbc.url"),
            properties.getProperty("jdbc.username"),
            properties.getProperty("jdbc.password")
        );
    }

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
