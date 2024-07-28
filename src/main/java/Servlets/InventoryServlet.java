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
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");private InventoryDAO inventoryDAO;

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
            }
        }
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    private void forwardInventory(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        List<Items> inventory = inventoryDAO.getInventory();
        request.setAttribute("inventory", inventory);
        forwardToPage(request, response, page);
    }

    private void addInventory(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ParseException {
        Items newItem = new Items();
        newItem.setItemName(request.getParameter("itemName"));
        newItem.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        newItem.setExpirationDate(DATE_FORMAT.parse(request.getParameter("expirationDate")));
        newItem.setRetailerPrice(Double.parseDouble(request.getParameter("retailerPrice")));
        newItem.setDonation(Boolean.parseBoolean(request.getParameter("isDonation")));
        newItem.setSale(Boolean.parseBoolean(request.getParameter("isSale")));
        newItem.setDiscountPrice(Double.parseDouble(request.getParameter("discountPrice")));

        boolean itemAdded = inventoryDAO.addItem(newItem);
        response.sendRedirect(itemAdded ? "Inventory-retailer.jsp" : "Failed.jsp");
    }

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