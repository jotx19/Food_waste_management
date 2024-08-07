<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventory</title>
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
        .expiring {
            background-color: red;
            color: black;
        }
        footer {
            display: flex;
            justify-content:center;
            gap: 10px;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
            align-items: center;
            left: 0;
        }
        footer a {
            text-decoration: none;
            background-color: black;
            color: white;
            padding: 10px 20px;
            border-radius: 25px;
            transition: background-color 0.3s ease;
            text-align: center;
            width: 7vw;
        }
        footer a:hover {
            background-color: #e0b800;
            color:black;
        }

        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4); 
            padding-top: 60px;
        }
        .modal-content {
            background-color: #fff;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 400px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.3);
            animation: fadeIn 0.3s;
        }
        @keyframes fadeIn {
            from {opacity: 0;}
            to {opacity: 1;}
        }
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        button[type="submit"] {
            background-color: black;
            color: white;
            padding: 10px;
            cursor: pointer;
            width: 100%;
            border-radius: 25px;
            transition: background-color 0.3s ease;
            font-size: 12px; 
            align-self: center;
        }
        button[type="submit"]:hover {
            background-color: #FFBF00;
            color: black;
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
        <h1>Inventory Management</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Item Name</th>
                <th>Quantity</th>
                <th>Expiration Date</th>
                <th>Retailer Price</th>
                <th>Donation</th>
                <th>Sale</th>
                <th>Discount Price</th>
            </tr>
            <%
            Connection connection = null;
            Statement stat = null;
            ResultSet result = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fwrp", "root", "477Azadeh936@");
                stat = connection.createStatement();
                String query = "SELECT * FROM Inventory";
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
                <td><%=result.getString("RetailerPrice")%></td>
                <td><%=result.getBoolean("IsDonation") ? "Yes" : "No" %></td>
                <td><%=result.getBoolean("IsSale") ? "Yes" : "No" %></td>
                <td><%=result.getString("DiscountPrice")%></td>
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
    
    <div id="deleteModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <form action="InventoryServlet" method="post">
                <input type="hidden" name="action" value="delete">
                Enter Item ID to delete: <input type="number" name="itemId" required><br>
                <button type="submit">Delete Inventory</button>
            </form>
        </div>
    </div>
        
    <footer>
        <a href="InventoryServlet?action=add">Add Item</a>
        <a href="InventoryServlet?action=update">Update Item</a>
        <a href="#" id="deleteFooterButton">Delete Item</a>
        <a href="SurplusItem.jsp" class="button">View Surplus</a>
        <a href="feedback.jsp" class="button">Feedbacks</a>
    </footer>
    
    <script>
        var modal = document.getElementById("deleteModal");
        var deleteLink = document.getElementById("deleteFooterButton");
        var span = document.getElementsByClassName("close")[0];

        deleteLink.onclick = function(event) {
            event.preventDefault();
            modal.style.display = "block";
        }
        span.onclick = function() {
            modal.style.display = "none";
        }
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>
</body>
</html>
