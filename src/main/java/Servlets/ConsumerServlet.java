package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CustomerServlet")
public class ConsumerServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("purchase".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("itemId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // Execute purchase operation and update stock level
            boolean isPurchaseSuccessful = processPurchase(productId, quantity);

            if (isPurchaseSuccessful) {
                // Redirect to confirmation page
                response.sendRedirect("Consumer.jsp?success=true");
            } else {
                // Redirect to failure page
                response.sendRedirect("Consumer.jsp?success=false");
            }
        } else {
            // Handle unknown or missing action parameter
            response.sendRedirect("Inventory-retailer.jsp");
        }
    }

    private boolean processPurchase(int productId, int quantity) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean isSuccessful = false;

        try {
            // Establish database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fwrp", "root", "Nimrat22$");

            // Update the stock level
            isSuccessful = updateStockLevel(conn, productId, quantity);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close database resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isSuccessful;
    }

    private boolean updateStockLevel(Connection conn, int productId, int quantity) throws SQLException {
        PreparedStatement stmt = null;

        try {
            // Fetch the current stock level
            String selectQuery = "SELECT quantity FROM Inventory WHERE itemId = ?";
            stmt = conn.prepareStatement(selectQuery);
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            int currentStock = 0;
            if (rs.next()) {
                currentStock = rs.getInt("quantity");
            }

            // Check if there is enough stock
            if (currentStock < quantity) {
                return false; // Not enough stock to fulfill the order
            }

            // Update the inventory stock level
            String updateQuery = "UPDATE Inventory SET quantity = ? WHERE itemId = ?";
            stmt = conn.prepareStatement(updateQuery);
            stmt.setInt(1, currentStock - quantity); // Deduct the specified quantity
            stmt.setInt(2, productId);
            int rowsUpdated = stmt.executeUpdate();

            // Check if the stock update was successful
            return rowsUpdated > 0;
        } finally {
            // Close the statement
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
