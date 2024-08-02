/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Servlets;

import DB.DBconnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The `LoginServlet` class handles HTTP requests for user authentication.
 * It processes login requests, authenticates users based on their email and password, and redirects users to the appropriate page based on their user type.
 * It also handles logout requests by invalidating the current session and redirecting the user to the login page.
 *
 * <p>On POST requests, the servlet extracts user credentials from the request, authenticates the user, and forwards the user to a page based on their role (retailer, charity, or consumer).
 * On GET requests, it invalidates the current session and redirects the user to the login page.</p>
 *
 * <p>The servlet uses the `DBconnection` class to obtain a connection to the database and perform authentication queries.</p>
 *
 * @author YourName
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP POST requests for user login.
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws ServletException If an input or output error is detected when the servlet handles the request.
     * @throws IOException If an error occurs while sending the response.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String userType = authenticateUser(email, password);
        if (userType != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userType", userType);
            session.setAttribute("email", email);
            String userID = getUserID(email, password);
            session.setAttribute("userID", userID);

            String targetPage;
            switch (userType.toLowerCase()) {
                case "retailer":
                    targetPage = "/Inventory-retailer.jsp";
                    break;
                case "charity":
                    targetPage = "/Claim-charity.jsp";
                    break;
                case "consumer":
                    targetPage = "/Consumer.jsp";
                    break;
                default:
                    targetPage = null;
                    break;
            }

            if (targetPage != null) {
                request.getRequestDispatcher(targetPage).forward(request, response);
            } else {
                response.sendRedirect("login.jsp?error=true");
            }
        } else {
            response.sendRedirect("login.jsp?error=true");
        }
    }

    /**
     * Handles HTTP GET requests for user logout.
     * Invalidates the current session and redirects the user to the login page.
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws ServletException If an input or output error is detected when the servlet handles the request.
     * @throws IOException If an error occurs while sending the response.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        response.sendRedirect("login.jsp");
    }

    /**
     * Authenticates a user by checking the provided email and password against the database.
     *
     * @param email The email address of the user.
     * @param password The password of the user.
     * @return The user type if authentication is successful, or null if authentication fails.
     */
    private String authenticateUser(String email, String password) {
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT userType FROM Users WHERE email = ? AND password = ?")) {

            if (connection == null) {
                System.err.println("Failed to obtain database connection");
                return null;
            }

            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("userType");
                } else {
                    System.out.println("User not found: " + email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves the user ID of the user by checking the provided email and password against the database.
     *
     * @param email The email address of the user.
     * @param password The password of the user.
     * @return The user ID if found, or null if no user is found.
     */
    private String getUserID(String email, String password) {
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT userID FROM Users WHERE email = ? AND password = ?")) {

            if (connection == null) {
                System.err.println("Failed to obtain database connection");
                return null;
            }

            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("userID");
                } else {
                    System.out.println("User not found: " + email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
