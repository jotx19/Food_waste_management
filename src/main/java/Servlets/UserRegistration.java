package Servlets;

import DAO.UserDAO;
import DAOImpl.UserDAOImpl;
import DTO.Types;
import DTO.userdto;
import Models.Passwordvalidator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The `UserRegistration` servlet handles user registration requests.
 * It processes form submissions to register new users by validating input, creating a `userdto` object,
 * and interacting with the `UserDAO` to persist user data in the database.
 *
 * <p>The servlet supports different user types and directs users to appropriate pages based on their role after successful registration.</p>
 *
 * @author YourName
 */
@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {

    /**
     * Handles POST requests to register a new user.
     *
     * <p>This method processes the registration form data, validates the password, creates a `userdto` object with the user details,
     * and calls the `UserDAO` to register the user in the database. Depending on the user type, it forwards the user to a specific page.</p>
     *
     * @param request The `HttpServletRequest` object containing the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws ServletException If an input or output error occurs while processing the request.
     * @throws IOException If an error occurs while forwarding the request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userTypeString = request.getParameter("userType");

        Types userType = null;
        if (userTypeString != null) {
            try {
                userType = Types.valueOf(userTypeString);
            } catch (IllegalArgumentException e) {
                userType = Types.Consumer; // Default value if the userTypeString is invalid
            }
        } else {
            userType = Types.Consumer; // Default value
        }

        // Validate password
        if (!Passwordvalidator.validate(password)) {
            request.setAttribute("error", "Password must be at least 8 characters and meet the specified requirements.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        userdto user = new userdto(name, email, password, userType);
        System.out.println("Registering user: " + user.getName() + ", " + user.getEmail() + ", " + user.getUserType());

        UserDAO userDAO = new UserDAOImpl(); // Use the implementation class
        int userId = 0;
        try {
            userId = userDAO.registerUser(user);
        } catch (SQLException ex) {
            Logger.getLogger(UserRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("User ID: " + userId);
        if (userId > 0) {
            switch (userType) {
                case Retailer:
                    request.getRequestDispatcher("/Inventory-retailer.jsp").forward(request, response);
                    break;
                case Charity:
                    request.getRequestDispatcher("/Claim-charity.jsp").forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("/Consumer.jsp").forward(request, response);
                    break;
            }
        } else {
            System.out.println("User not created.");
        }
    }
}
