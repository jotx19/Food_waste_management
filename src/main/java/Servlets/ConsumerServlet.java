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

/**
 * The `ConsumerServlet` class handles HTTP POST requests for processing purchases made by consumers.
 * It performs operations related to updating the inventory when a purchase action is requested.
 * This servlet connects to the database, performs the purchase operation by updating the inventory,
 * and redirects the user to the appropriate page based on the success or failure of the purchase.
 *
 * <p>The servlet extends `HttpServlet` and overrides the `doPost` method to process the purchase action.
 * It interacts directly with the database to manage inventory updates and handle exceptions related to database operations.</p>
 *
 */
@WebServlet("/ConsumerServlet")
public class ConsumerServlet extends HttpServlet {

    /**
     * Processes HTTP POST requests for purchasing items.
     *
     * <p>This method retrieves the action parameter from the request to determine if a purchase operation
     * is requested. It then performs the purchase operation by calling the `purchase` method, which updates
     * the inventory. Based on the result, the user is redirected to the appropriate JSP page indicating success
     * or failure of the purchase. If the action parameter is invalid or missing, the user is redirected to an
     * inventory-related page.</p>
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws ServletException If an input or output error is detected when the servlet handles the request.
     * @throws IOException If an error occurs while sending the response.
     */
    @Override
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

    /**
     * Handles the purchase operation by updating the inventory.
     *
     * <p>This method establishes a database connection, retrieves the current quantity and discount price
     * of the item specified by `itemId`, and updates the inventory to reflect the purchase. It returns a boolean
     * indicating the success or failure of the operation.</p>
     *
     * @param itemId The ID of the item being purchased.
     * @return true if the purchase operation was successful, false otherwise.
     */
    private boolean purchase(int itemId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean success = false;

        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fwrp", "root", "root");

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

    /**
     * Updates the inventory quantity for a given item.
     *
     * <p>This method retrieves the current quantity and discount price of the item specified by `itemId`,
     * updates the quantity to reflect the purchase, and returns a boolean indicating the success of the update.</p>
     *
     * @param connection The `Connection` object used to interact with the database.
     * @param itemId The ID of the item whose quantity is being updated.
     * @return true if the inventory update was successful, false otherwise.
     * @throws SQLException If an error occurs while interacting with the database.
     */
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
