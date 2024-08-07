/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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
 *
 * @author LENOVO
 */
public class ClaimFoodServlet extends HttpServlet{
    private InventoryDAO inventoryDAO;
            
    @Override
    public void init(){
        inventoryDAO =  new InventoryDAOImpl();
        
    }@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try{
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            
            // Claim the food item
            boolean claimSuccess = inventoryDAO.claimItem(itemId);
            
            //Redirect for Success or Failure
            if (claimSuccess) {
                response.sendRedirect("ClaimSuccess.jsp");
            } else {
                response.sendRedirect("ClaimFailer.jsp");
            }
        } catch (NumberFormatException e){
            response.sendRedirect("Error.jsp");
        }
    }  
}
