package controller;

import db.DBconnection;
import dto.Types;
import dto.userdto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/Controller.UserRegistration")
public class UserRegistration extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userTypeString = request.getParameter("userType");

        Types userType = null;
        if (userTypeString != null) {
            userType = Types.valueOf(userTypeString.replace(" ", "_"));
        } else {
            userType = Types.Consumer; // Default value
        }

        userdto user = new userdto(name, email, password, userType);
        System.out.println("Registering user: " + user.getName() + ", " + user.getEmail() + ", " + user.getUserType());

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBconnection.getConnection();
            String userSql = "INSERT INTO Users (name, email, password, userType) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(userSql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUserType().name());

            int affectedRows = statement.executeUpdate();
            System.out.println("Affected Rows: " + affectedRows);

            if (affectedRows > 0) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int userId = resultSet.getInt(1);
                    System.out.println("User ID: " + userId);
                }
            } else {
                System.out.println("User not created.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        switch (userType) {
            case Retailer:
                request.getRequestDispatcher("/InventoryItem.jsp").forward(request, response);
                break;
            case Charity:
                request.getRequestDispatcher("/ClaimInformation.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("/Consumer.jsp").forward(request, response);
                break;
        }
    }
}
