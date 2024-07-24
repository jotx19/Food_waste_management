package Servlets;

import DAO.InventoryDAO;
import DAOImpl.InventoryDAOImpl;
import Models.Items;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InventoryServlet")
public class InventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private InventoryDAO inventoryDAO;

    @Override
    public void init() {
        inventoryDAO = new InventoryDAOImpl(); // Use the implementation class
    }

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
                forwardToPageWithInventory(request, response, "updateInventory.jsp");
                break;
            case "delete":
                forwardToPageWithInventory(request, response, "deleteInventory.jsp");
                break;
            case "viewSurplus":
                forwardToPage(request, response, "surplusItem.jsp");
                break;
            default:
                forwardToPage(request, response, "InventoryItem.jsp");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            try {
                switch (action) {
                    case "add":
                        handleAddInventory(request, response);
                        break;
                    case "update":
                        handleUpdateInventory(request, response);
                        break;
                    case "delete":
                        handleDeleteInventory(request, response);
                        break;
                    case "viewSurplus":
                        handleViewSurplusInventory(request, response);
                        break;
                }
            } catch (IOException | ParseException e) {
                response.sendRedirect("Error.jsp");
            }
        }
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    private void forwardToPageWithInventory(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        List<Items> inventory = inventoryDAO.getInventory();
        request.setAttribute("inventory", inventory);
        forwardToPage(request, response, page);
    }

    private void handleAddInventory(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        Items newItem = new Items();
        newItem.setItemName(request.getParameter("itemName"));
        newItem.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        newItem.setExpirationDate(DATE_FORMAT.parse(request.getParameter("expirationDate")));
        newItem.setRetailerPrice(Double.parseDouble(request.getParameter("retailerPrice")));
        newItem.setDonation(Boolean.parseBoolean(request.getParameter("isDonation")));
        newItem.setSale(Boolean.parseBoolean(request.getParameter("isSale")));
        newItem.setDiscountPrice(Double.parseDouble(request.getParameter("discountPrice")));

        boolean itemAdded = inventoryDAO.addItemToInventory(newItem);
        response.sendRedirect(itemAdded ? "Inventory-retailer.jsp" : "Failed.jsp");
    }

    private void handleUpdateInventory(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        boolean updateSuccess = inventoryDAO.updateInventoryItem(
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

    private void handleDeleteInventory(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            boolean deleteSuccess = inventoryDAO.deleteInventoryItem(itemId);
            response.sendRedirect(deleteSuccess ? "Inventory-retailer.jsp" : "Error.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
        }
    }

    private void handleViewSurplusInventory(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            int surplusItemId = Integer.parseInt(request.getParameter("itemId"));
            boolean surplusSuccess = inventoryDAO.flagSurplusItem(surplusItemId);
            response.sendRedirect(surplusSuccess ? "surplusItem.jsp" : "Error.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
        }
    }
}
