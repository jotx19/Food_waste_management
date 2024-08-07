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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("purchase")) {
            int itemId = Integer.parseInt(request.getParameter("itemId"));

            // Perform the purchase operation and update inventory quantity
            boolean purchaseSuccess = purchase(itemId);

            if (purchaseSuccess) {
                // Redirect back to the same page with updated quantity
                response.sendRedirect("Consumer.jsp?success=true");
            } else {
                // Redirect to a failed page with success parameter
                response.sendRedirect("Consumer.jsp?success=false");
            }
        } else {
            // Handle invalid or missing action parameter
            response.sendRedirect("Inventory-retailer.jsp");
        }
    }

    private boolean purchase(int itemId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean success = false;

        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fwrp", "root", "477Azadeh936@");

            // Decrease inventory quantity
            success = quantity(connection, itemId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }

    private boolean quantity(Connection connection, int itemId) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // Retrieve current quantity of the item and discounted price
            String query = "SELECT quantity, discountPrice FROM Inventory WHERE itemId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();

            int currentQuantity = 0;
            double discountPrice = 0.0;
            if (resultSet.next()) {
                currentQuantity = resultSet.getInt("quantity");
                discountPrice = resultSet.getDouble("discountPrice");
            }

            // Update quantity in the inventory
            query = "UPDATE Inventory SET quantity = ? WHERE itemId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, currentQuantity - 1); // Decrease quantity by 1 for purchase
            preparedStatement.setInt(2, itemId);
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the update was successful
            return rowsAffected > 0;
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
}
