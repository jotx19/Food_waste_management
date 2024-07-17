<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Purchase List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
/*            background-color: #f9f9f9;*/
            background-image: url('./css/food.jpeg');
        }

       

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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
            color: #fff;
            text-transform: uppercase;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .purchase-button {
            background-color: #293d3d;
            color: #fff;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .purchase-button:hover {
            background-color: #1d2929;
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

        .subscription-button {
            background-color: #527a7a;
            color: #fff;
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-decoration: none;
            margin-top: 20px;
            display: inline-block;
        }

        .subscription-button:hover {
            background-color: #405b5b;
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

    </style>
</head>
<body>
<div class="header">
    <h1>Customer Purchase List</h1>
    <div class="header-buttons">
            <a href="LoginServlet" class="button">Logout</a>
            <a href="FeedbackServlet" class="button">Feedback</a>
        </div>
</div>
<div class="container">
    <table border="1">
        <tr>
            <th>Item ID</th>
            <th>Item Name</th>
            <th>Quantity</th>
            <th>Expiration Date</th>
            <th>Retailer Price</th>
            <th>Discount Price</th>
            <th>Action</th>
        </tr>
        <%
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fwrp", "root", "Ar@200703");
            st = connection.createStatement();
            String query = "SELECT * FROM Inventory WHERE isDonation = 0";
            rs = st.executeQuery(query);
            while (rs.next()) {
        %>
        <tr>
            <td><%=rs.getString(1)%></td>
            <td><%=rs.getString(2)%></td>
            <td><%=rs.getString(3)%></td>
            <td><%=rs.getString(4)%></td>
            <td><%=rs.getString(5)%></td>
            <td><%=rs.getString("discountPrice")%></td>
            <td>
                <form id="purchaseForm<%=rs.getString(1)%>" action="CustomerServlet" method="post">
                    <input type="hidden" name="action" value="purchase">
                    <input type="hidden" name="itemId" value="<%=rs.getString(1)%>">
                    <button type="button" class="purchase-button" onclick="purchaseItem('<%=rs.getString(1)%>')">Purchase</button>
                </form>
            </td>
        </tr>
        <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        %>
    </table>
</div>
<div class="footer">
    <p>© 2024 Food Waste Reduction Platform</p>
    <a href="Subscription.jsp" class="subscription-button">Subscribe</a>
</div>

<script>
    function purchaseItem(itemId) {
        var form = document.getElementById("purchaseForm" + itemId);
        form.submit();
        alert("Your purchase was successful!");
    }
</script>

</body>
</html>
