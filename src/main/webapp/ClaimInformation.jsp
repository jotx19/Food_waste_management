<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Claim Information</title>
    <style>
        /* CSS styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            background-image: url('./css/food.jpeg');
}
        

        .container {
            max-width: 800px;
            margin: 20px auto;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #527a7a;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        button {
            background-color: #293d3d;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 5px;
        }

        button:hover {
            background-color: #45a049;
        }
        .header-buttons {
    position: absolute;
    top: 50px;
    right: 20px;
}

.header-buttons a {
    text-decoration: none;
   background-color: #527a7a; /* Light gray button background */
    color: #fff; /* White text color */
    padding: 8px 15px; /* Adjusted button padding */
    border-radius: 4px;
    transition: background-color 0.3s ease;
    margin-left: 10px;
    font-size: 14px; /* Adjusted button font size */
}

.header-buttons a:hover {
    background-color: #bbb; /* Slightly darker shade on hover */
}
.header {
          
            background-color: #293d3d;
            color: #fff;
            padding: 1px;
            text-align: center;
            font-size: 24px;
        }
        .footer {
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            text-align: center;
            padding: 20px;
            background-color: #293d3d;
            color: #fff;
            border-radius: 0 0 5px 5px;
        }

    </style>
</head>
<body>
    <!-- Header -->
    <div class="header">
        <h1>Claim Information</h1>
         <div class="header-buttons">
            <a href="LoginServlet" class="button">Logout</a>
            <a href="FeedbackServlet" class="button">Feedback</a>
        </div>
        <!-- Add any header content here -->
    </div>

    <!-- Main content container -->
    <div class="container">
        <!-- Donation items table -->
        <h2>Donation Items Available for Claim</h2>
        <table>
            <tr>
                <th>Item Name</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
            <!-- Loop through donation items -->
            <%
                Connection connection = null;
                Statement st = null;
                ResultSet rs = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fwrp", "root", "Ar@200703");
                    st = connection.createStatement();
                    String query = "SELECT * FROM Inventory WHERE IsDonation = 1 ";
                    rs = st.executeQuery(query);
                    while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getString("ItemName") %></td>
                <td><%= rs.getInt("Quantity") %></td>
                <td>
                    <form action="CharityServlet" method="post">
                        <input type="hidden" name="itemId" value="<%= rs.getInt("ItemID") %>">
                        <% if (rs.getInt("Quantity") > 0) { %>
                           <button type="submit" name="action" value="claim" onclick="alert('Are you sure you want to claim this item?')">Claim</button>
                        <% } else { %>
                            <span>No items to claim</span>
                        <% } %>
                    </form>
                    <% if (request.getAttribute("claimMessage") != null) { %>
                        <p><%= request.getAttribute("claimMessage") %></p>
                    <% } %>
                </td>
            </tr>
            <%
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (st != null) st.close();
                        if (connection != null) connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>
        </table>


    </div>
        <div class="footer">
        <p>© 2024 Food Waste Reduction Platform</p>
    </div>
</body>
</html>
