/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userTypeString = request.getParameter("userType");

        Types userType = null;
        if (userTypeString != null) {
            userType = Types.valueOf(userTypeString);
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
