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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // In real application, password should be hashed

        String userType = authenticateUser(email, password);
        if (userType != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userType", userType);
            switch (userType) {
                case "Retailer":
                    request.getRequestDispatcher("/InventoryItem.jsp").forward(request, response);
                    break;
                case "Charitable_Organization":
                    request.getRequestDispatcher("/ClaimInformation.jsp").forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("/Consumer.jsp").forward(request, response);
                    break;
            }
        } else {
            response.sendRedirect("login.jsp?error=true"); // Redirect back to login page with error
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle logout
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidate session
        }
        response.sendRedirect("login.jsp"); // Redirect to login page after logout
    }

    private String authenticateUser(String email, String password) {
        // Use DatabaseUtil to establish database connection
        try (Connection connection = DBconnection.getConnection();
             // Prepare a statement to prevent SQL injection
             PreparedStatement statement = connection.prepareStatement("SELECT userType FROM Users WHERE email = ? AND password = ?")) {
            statement.setString(1, email);
            statement.setString(2, password); // In real application, use hashed password

            try (ResultSet resultSet = statement.executeQuery()) {
                // Check if the result set has any rows
                if (resultSet.next()) {
                    return resultSet.getString("userType");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return null if authentication fails
        return null;
    }
}
