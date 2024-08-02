/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Servlets;

import DAO.SubscriptionDAO;
import DAOImpl.SubscriptionDAOImpl;
import Models.Subscription;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The `SubscribeServlet` class handles user subscriptions for notifications based on their preferences.
 * It manages the subscription data by interacting with the `SubscriptionDAO` and processes form submissions
 * to add new subscriptions.
 *
 * <p>This servlet initializes a `SubscriptionDAO` with a database connection and processes POST requests
 * to create new subscriptions. It redirects users to a success page upon successful subscription.</p>
 *
 * @author YourName
 */
@WebServlet("/SubscribeServlet")
public class SubscribeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SubscriptionDAO subscriptionDAO;

    /**
     * Initializes the servlet and sets up the `SubscriptionDAO` with a connection to the database.
     *
     * <p>The database connection details are hardcoded, including the JDBC URL, username, and password.</p>
     */
    @Override
    public void init() {
        String jdbcURL = "jdbc:mysql://localhost/fwrp";
        String jdbcUsername = "root";
        String jdbcPassword = "root";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            subscriptionDAO = new SubscriptionDAOImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles POST requests to subscribe a user based on the form data provided.
     *
     * <p>The method extracts user data from the request, including the user ID, email, location, communication method,
     * and food preferences. It creates a `Subscription` object with this data and adds it to the database via the `SubscriptionDAO`.</p>
     *
     * @param request The `HttpServletRequest` object containing the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws ServletException If an input or output error occurs while processing the request.
     * @throws IOException If an error occurs while redirecting the response.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getSession().getAttribute("userID").toString());
        String email = request.getParameter("email");
        String location = request.getParameter("location");
        String communicationMethod = request.getParameter("communication_method");
        String foodPreferences = request.getParameter("food_preferences");

        Subscription subscription = new Subscription();
        subscription.setUserID(userId);
        subscription.setEmail(email);
        subscription.setmoc(communicationMethod);
        subscription.setLocation(location);
        subscription.setFoodPreferences(foodPreferences);

        subscriptionDAO.addSubscription(subscription);

        response.sendRedirect("subscription_success.jsp");
    }
}
