package DAO;

import DTO.userdto;
import java.sql.SQLException;

/**
 * The UserDAO interface defines methods for managing user data
 * within the system. It provides operations for user registration.
 */
public interface UserDAO {

    /**
     * Registers a new user in the system.
     *
     * @param user The userdto object representing the user to be registered.
     * @return An integer representing the result of the registration operation.
     *         Typically, this could be the ID of the newly registered user or an error code.
     * @throws SQLException if a database access error occurs during registration.
     */
    int registerUser(userdto user) throws SQLException;
}
