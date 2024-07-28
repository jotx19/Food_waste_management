<%-- 
    Document   : Claim-charity
    Created on : Jul. 28, 2024, 1:21:22 p.m.
    Author     : LENOVO
--%>

<%@page import="java.util.List"%>
<%@page import="Models.Items"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Claim Surplus Food</title>
    </head>
    <body>
        <h1>Claim Surplus Food</h1>
        <table border="1">
        <tr>
            <th>Item ID</th>
            <th>Item Name</th>
            <th>Quantity</th>
            <th>Expiration Date</th>
            <th>Action</th>
        </tr>
        <%
            List<Items> inventory = (List<Items>) request.getAttribute("inventory");
            if (inventory != null) {
                for (Items item : inventory) {
        %>
        <tr>
            <td><%= item.getItemId() %></td>
            <td><%= item.getItemName() %></td>
            <td><%= item.getQuantity() %></td>
            <td><%= item.getExpirationDate() %></td>
            <td>
                <form action="InventoryServlet" method="post">
                    <input type="hidden" name="action" value="claim">
                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                    <button type="submit">Claim</button>
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
        </table>
    <br>
    <a href="index.html">Back to Home</a>
    </body>
</html>
