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

@WebServlet("/SubscribeServlet")
public class SubscribeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SubscriptionDAO subscriptionDAO;

    @Override
    public void init() {
        String jdbcURL = "jdbc:mysql://localhost/fwrp";
        String jdbcUsername = "root";
        String jdbcPassword = "Nimrat22$";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            subscriptionDAO = new SubscriptionDAOImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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