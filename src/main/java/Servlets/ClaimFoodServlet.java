package Servlets;

import DAO.InventoryDAO;
import DAOImpl.InventoryDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * The `ClaimFoodServlet` class is responsible for handling HTTP POST requests
 * to claim food items from the inventory. This servlet interacts with the
 * inventory data access object (DAO) to process the claim and then redirects
 * the user to appropriate JSP pages based on the success or failure of the operation.
 *
 * <p>This servlet extends `HttpServlet` and overrides the `doPost` method to
 * process form submissions for claiming food items. It uses an instance of
 * `InventoryDAO` to perform the claim operation and provides feedback to the user
 * through redirection to success or failure pages.</p>
 *
 */
@WebServlet("/claimFood")
public class ClaimFoodServlet extends HttpServlet {
    private InventoryDAO inventoryDAO;

    /**
     * Initializes the servlet and sets up the `InventoryDAO` instance.
     * This method is called once when the servlet is first loaded.
     */
    @Override
    public void init() {
        inventoryDAO = new InventoryDAOImpl();
    }

    /**
     * Handles HTTP POST requests for claiming food items.
     *
     * <p>This method extracts the item ID from the request parameters, attempts to
     * claim the item using the `InventoryDAO` instance, and then redirects the user
     * to a success or failure page based on the result of the claim operation. If
     * the item ID is not a valid number, the user is redirected to an error page.</p>
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws ServletException If an input or output error is detected when the servlet handles the request.
     * @throws IOException If an error occurs while sending the response.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));

            // Claim the food item
            boolean claimSuccess = inventoryDAO.claimItem(itemId);

            // Redirect based on success or failure
            if (claimSuccess) {
                response.sendRedirect("ClaimSuccess.jsp");
            } else {
                response.sendRedirect("ClaimFailure.jsp");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("Error.jsp");
        }
    }
}
