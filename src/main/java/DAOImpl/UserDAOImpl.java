package DAOImpl;

import DAO.UserDAO;
import DB.DBconnection;
import DTO.userdto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The UserDAOImpl class implements the UserDAO interface,
 * providing the functionality to manage user data, including
 * user registration within the system.
 */
public class UserDAOImpl implements UserDAO {

    /**
     * Registers a new user in the system.
     *
     * This method inserts the provided user information into the
     * database and returns the generated user ID if the registration
     * is successful. It utilizes a PreparedStatement to execute
     * the SQL insertion query and retrieve the generated key.
     *
     * @param user The userdto object representing the user to be registered.
     * @return An integer representing the ID of the newly registered user,
     *         or 0 if the registration failed or no user ID was generated.
     * @throws SQLException if a database access error occurs during the registration process.
     */
    @Override
    public int registerUser(userdto user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int userId = 0;

        try {
            connection = DBconnection.getConnection();
            String userSql = "INSERT INTO Users (name, email, password, userType) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(userSql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUserType().name());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    userId = resultSet.getInt(1);
                }
            }
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userId;
    }
}
