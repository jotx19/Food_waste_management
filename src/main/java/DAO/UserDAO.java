package DAO;

import DTO.userdto;
import java.sql.SQLException;

public interface UserDAO {
    int registerUser(userdto user) throws SQLException;
}
