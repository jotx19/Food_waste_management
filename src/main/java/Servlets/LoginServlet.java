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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String userType = authenticateUser(email, password);
        if (userType != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userType", userType);
            session.setAttribute("email", email);
            String userID = getuserID(email, password);
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        response.sendRedirect("login.jsp");
    }

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

    private String getuserID(String email, String password) {
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