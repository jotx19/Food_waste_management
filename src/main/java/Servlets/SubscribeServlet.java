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
import javax.servlet.http.HttpSession;

@WebServlet("/SubscribeServlet")
public class SubscribeServlet extends HttpServlet {
    private SubscriptionDAO subscriptionDAO;

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object userIDAttribute = session.getAttribute("userID");

        if (userIDAttribute == null) {
            // Handle the case where the userID is not present in the session
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User ID is missing. Please log in.");
            return;
        }

        int userId;
        try {
            userId = Integer.parseInt(userIDAttribute.toString());
        } catch (NumberFormatException e) {
            // Handle the case where the userID attribute is not a valid integer
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid User ID.");
            return;
        }

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
