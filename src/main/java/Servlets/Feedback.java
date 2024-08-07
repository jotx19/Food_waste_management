package Servlets;

import DB.DBconnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The `Feedback` servlet handles the submission of feedback from users.
 * It processes both GET and POST requests to manage feedback data.
 *
 * <p>When a GET request is received, the servlet redirects to a feedback form page.
 * For POST requests, it processes feedback data submitted by the user, including user ID,
 * item ID, feedback text, and rating. The feedback is inserted into the database, and
 * the user is redirected to a login page upon successful submission.</p>
 *
 */
@WebServlet("/FeedbackServlet")
public class Feedback extends HttpServlet {

    /**
     * Handles HTTP GET requests by redirecting to the feedback form page.
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws ServletException If an input or output error is detected when the servlet handles the request.
     * @throws IOException If an error occurs while sending the response.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("form.jsp");
    }

    /**
     * Handles HTTP POST requests by processing feedback submitted by the user.
     *
     * <p>This method retrieves the feedback data from the request parameters,
     * validates and parses the data, and inserts it into the database. If an error
     * occurs during database operations, an internal server error response is sent.
     * Upon successful insertion of feedback, the user is redirected to the login page.</p>
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws ServletException If an input or output error is detected when the servlet handles the request.
     * @throws IOException If an error occurs while sending the response.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID = request.getParameter("UserID");
        String itemIDStr = request.getParameter("itemID");
        String feedbackText = request.getParameter("textbox");
        String ratingStr = request.getParameter("rating");

        if (userID == null || itemIDStr == null || feedbackText == null || ratingStr == null ||
                userID.isEmpty() || itemIDStr.isEmpty() || feedbackText.isEmpty() || ratingStr.isEmpty()) {
            return;
        }

        int itemID, rating;
        try {
            itemID = Integer.parseInt(itemIDStr);
            rating = Integer.parseInt(ratingStr);
        } catch (NumberFormatException e) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try {
            insertFeedback(Integer.parseInt(userID), itemID, feedbackText, rating, formattedDate);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
            return;
        }

        response.sendRedirect("LoginServlet");
    }

    /**
     * Inserts feedback data into the database.
     *
     * <p>This method executes an SQL INSERT statement to add feedback data into the `Feedback` table.</p>
     *
     * @param userID The ID of the user providing feedback.
     * @param itemID The ID of the item being reviewed.
     * @param feedbackText The text of the feedback.
     * @param rating The rating given by the user.
     * @param feedbackDate The date and time when the feedback was provided.
     * @throws SQLException If an error occurs while interacting with the database.
     */
    private void insertFeedback(int userID, int itemID, String feedbackText, int rating, String feedbackDate) throws SQLException {
        String insertQuery = "INSERT INTO Feedback (UserID, ItemID, feedback_text, rating, feedback_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBconnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, itemID);
            stmt.setString(3, feedbackText);
            stmt.setInt(4, rating);
            stmt.setString(5, feedbackDate);
            stmt.executeUpdate();
        }
    }
}
