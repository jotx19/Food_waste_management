package Servlets;

import DAO.InventoryDAO;
import DAOImpl.InventoryDAOImpl;
import Models.Items;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The `InventoryServlet` class handles HTTP requests related to inventory management.
 * It supports actions such as adding, updating, deleting, viewing surplus, and claiming inventory items.
 * This servlet is responsible for processing both GET and POST requests and forwarding to the appropriate JSP pages or performing database operations.
 *
 * <p>On GET requests, the servlet determines the action based on the request parameter and forwards the user to the corresponding JSP page.
 * On POST requests, it performs the action specified by the request parameter, interacts with the database via DAO methods, and redirects the user to appropriate pages based on the outcome of the operation.</p>
 *
 * <p>The servlet uses an `InventoryDAO` for interacting with the database and a `NotificationService` to handle notifications related to inventory changes.</p>
 *
 */
@WebServlet("/InventoryServlet")
public class InventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private InventoryDAO inventoryDAO;
    private NotificationService notificationService;

    /**
     * Initializes the servlet, setting up DAO and notification service instances.
     *
     * @throws RuntimeException If there is an error initializing the notification service.
     */
    @Override
    public void init() {
        inventoryDAO = new InventoryDAOImpl();
        try {
            notificationService = new NotificationService(); // Use the implementation class
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles HTTP GET requests by forwarding to the appropriate JSP page based on the action parameter.
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws ServletException If an input or output error is detected when the servlet handles the request.
     * @throws IOException If an error occurs while sending the response.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            forwardToPage(request, response, "InventoryItem.jsp");
            return;
        }

        switch (action) {
            case "add":
                forwardToPage(request, response, "AddInventory.jsp");
                break;
            case "update":
                forwardInventory(request, response, "Update.jsp");
                break;
            case "delete":
                forwardInventory(request, response, "Delete.jsp");
                break;
            case "viewSurplus":
                forwardToPage(request, response, "SurplusItem.jsp");
                break;
            case "claim":
                forwardInventory(request, response, "Claim-charity.jsp");
                break;
            default:
                forwardToPage(request, response, "InventoryItem.jsp");
                break;
        }
    }

    /**
     * Handles HTTP POST requests by performing actions such as adding, updating, deleting, viewing surplus, or claiming inventory items.
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws ServletException If an input or output error is detected when the servlet handles the request.
     * @throws IOException If an error occurs while sending the response.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            try {
                switch (action) {
                    case "add":
                        addInventory(request, response);
                        break;
                    case "update":
                        updateInventory(request, response);
                        break;
                    case "delete":
                        deleteInventory(request, response);
                        break;
                    case "viewSurplus":
                        viewSurplusInventory(request, response);
                        break;
                    case "claim":
                        claimFood(request, response);
                        break;
                }
            } catch (IOException | ParseException e) {
                response.sendRedirect("Error.jsp");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Forwards the request to a specified JSP page.
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @param page The JSP page to forward to.
     * @throws ServletException If an input or output error is detected when forwarding the request.
     * @throws IOException If an error occurs while sending the response.
     */
    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    /**
     * Forwards the request to a specified inventory management JSP page with the current inventory data.
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @param page The JSP page to forward to.
     * @throws ServletException If an input or output error is detected when forwarding the request.
     * @throws IOException If an error occurs while sending the response.
     */
    private void forwardInventory(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        List<Items> inventory = inventoryDAO.getInventory();
        request.setAttribute("inventory", inventory);
        forwardToPage(request, response, page);
    }

    /**
     * Adds a new inventory item based on request parameters.
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws IOException If an error occurs while sending the response.
     * @throws ParseException If there is an error parsing date.
     * @throws SQLException If a database access error occurs.
     */
    private void addInventory(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException, SQLException {
        Items newItem = new Items();
        newItem.setItemName(request.getParameter("itemName"));
        newItem.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        newItem.setExpirationDate(DATE_FORMAT.parse(request.getParameter("expirationDate")));
        newItem.setRetailerPrice(Double.parseDouble(request.getParameter("retailerPrice")));
        newItem.setDonation(Boolean.parseBoolean(request.getParameter("isDonation")));
        newItem.setSale(Boolean.parseBoolean(request.getParameter("isSale")));
        newItem.setDiscountPrice(Double.parseDouble(request.getParameter("discountPrice")));

        boolean itemAdded = inventoryDAO.addItem(newItem);
        notificationService.sendNotifications(newItem);
        response.sendRedirect(itemAdded ? "Inventory-retailer.jsp" : "Failed.jsp");
    }

    /**
     * Updates an existing inventory item based on request parameters.
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws IOException If an error occurs while sending the response.
     * @throws ParseException If there is an error parsing date.
     */
    private void updateInventory(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        boolean updateSuccess = inventoryDAO.updateItem(
                itemId,
                request.getParameter("newItemName"),
                Integer.parseInt(request.getParameter("newQuantity")),
                DATE_FORMAT.parse(request.getParameter("newExpirationDate")),
                Double.parseDouble(request.getParameter("newRetailerPrice")),
                Boolean.parseBoolean(request.getParameter("newIsDonation")),
                Boolean.parseBoolean(request.getParameter("newIsSale")),
                Double.parseDouble(request.getParameter("newDiscountPrice"))
        );
        response.sendRedirect(updateSuccess ? "Inventory-retailer.jsp" : "Error.jsp");
    }

    /**
     * Deletes an existing inventory item based on request parameters.
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws IOException If an error occurs while sending the response.
     */
    private void deleteInventory(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            boolean deleteSuccess = inventoryDAO.deleteItem(itemId);
            response.sendRedirect(deleteSuccess ? "Inventory-retailer.jsp" : "Error.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
        }
    }

    /**
     * Flags an inventory item as surplus based on request parameters.
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws IOException If an error occurs while sending the response.
     */
    private void viewSurplusInventory(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int surplusItemId = Integer.parseInt(request.getParameter("itemId"));
            boolean surplusSuccess = inventoryDAO.flagSurplusItem(surplusItemId);
            response.sendRedirect(surplusSuccess ? "SurplusItem.jsp" : "Error.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
        }
    }

    /**
     * Claims an inventory item for charity based on request parameters.
     *
     * @param request The `HttpServletRequest` object that contains the request data.
     * @param response The `HttpServletResponse` object used to send a response to the client.
     * @throws IOException If an error occurs while sending the response.
     */
    private void claimFood(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            boolean claimSuccess = inventoryDAO.claimItem(itemId);
            response.sendRedirect(claimSuccess ? "ClaimSuccess.jsp" : "ClaimFailure.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
        }
    }
}
