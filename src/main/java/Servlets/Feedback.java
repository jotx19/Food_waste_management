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

@WebServlet("/FeedbackServlet")
public class Feedback extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("form.jsp");
    }

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
