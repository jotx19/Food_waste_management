<%-- 
    Document   : Claim-Charity
    Created on : Aug. 1, 2024, 3:46:12 p.m.
    Author     : 14375
--%>

<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@page import="Models.Items"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Claim Surplus Food</title>
    <link rel="stylesheet" href="https://use.typekit.net/nsf1sey.css">
    <style>
        body {
            font-family: owners;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            background-color: #fff6f6;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: black;
            border-radius: 25px;
            padding: 10px 20px;
            color: black;
            position: fixed;
            left: 50%;
            transform: translateX(-50%);
            height: 5vh;
            width: 90%;
            padding: 10px 15px;
            background: rgba(25, 30, 30, 0.1);
            justify-content: space-between;
            align-items: center;
            backdrop-filter: blur(50px);
            border-radius: 30px;
        }
                .header h1 {
            font-size: 1.5em;
            margin: 0;
        }
        .header a {
            text-decoration: none;
            background-color: #FFBF00;
            color: black;
            padding: 10px 25px; 
            border-radius: 25px;
            transition: background-color 0.3s ease;
            font-size: 14px; 
        }
        .header a:hover {
            background-color: black;
            color: white;
        }
        .container {
            margin: 80px auto 20px auto; 
            max-width: 100%;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .container h1 {
            align-content: center;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 2px;
            border-radius: 5px;
            overflow: hidden;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #ee96e3;
            color: black;
            text-transform: uppercase;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        .button {
            text-decoration: none;
            background-color: #FFBF00;
            color: black;
            padding: 10px 25px; 
            border-radius: 25px;
            transition: background-color 0.3s ease;
            font-size: 14px; 
            display: inline-block;
        }
        .button:hover {
            background-color: black;
            color: white;
        }
        .button-container {
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>FWRP</h1>
        <div class="header-buttons">
            <a href="LoginServlet" class="button">Logout</a>
        </div>
    </div>
    <div class="container">
        <h1>Claim Surplus Food</h1>
        <table border="1">
            <tr>
                <th>Item ID</th>
                <th>Item Name</th>
                <th>Quantity</th>
                <th>Expiration Date</th>
                <th>Donation</th>
                <th>Action</th>
            </tr>
            <%
            Connection connection = null;
            Statement stat = null;
            ResultSet result = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fwrp", "root", "root");
                stat = connection.createStatement();
                String query = "SELECT * FROM Inventory WHERE IsDonation = true";
                result = stat.executeQuery(query);
                Date currentDate = new Date();
                while (result.next()) {
                    Date expirationDate = result.getDate("ExpirationDate");
                    boolean isExpiring = expirationDate != null && (expirationDate.getTime() - currentDate.getTime()) <= 7 * 24 * 60 * 60 * 1000;
            %>
            <tr>
                <td><%=result.getString("ItemID")%></td>
                <td><%=result.getString("ItemName")%></td>
                <td><%=result.getString("Quantity")%></td>
                <td class="<%= isExpiring ? "expiring" : "" %>"><%=sdf.format(expirationDate)%></td>
                <td><%=result.getBoolean("IsDonation") ? "Yes" : "No" %></td>
                <td>
                    <form action="InventoryServlet" method="post">
                        <input type="hidden" name="action" value="claim">
                        <input type="hidden" name="itemId" value="<%=result.getString("ItemID")%>">
                        <input type="number" name="requestedQuantity" min="1" max="<%=result.getInt("Quantity")%>" required>
                        <button type="submit" class="button">Claim</button>
                    </form>
                </td>
            </tr>
            <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (result != null) try { result.close(); } catch (SQLException ignore) {}
                if (stat != null) try { stat.close(); } catch (SQLException ignore) {}
                if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
            }
            %>
        </table>
    </div>
</body>
</html>