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
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            background-color: #f9f9f9;
        }

        .header {
            margin:2px;
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
            background: rgba(30, 23, 23, 0.1);
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
            margin-left: 10px;
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
            /*overflow-x: auto;  Handles horizontal overflow */
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
            display: flex;
            justify-content: center;
            /*gap: 10px;*/
            padding: 15px;
            position: fixed;
            bottom: 0;
            width: 100%;
            align-items: center;
/*            left: 0;*/
        }

        .footer a {
            text-decoration: none;
            background-color: black;
            color: white;
            padding: 10px 30px;
            border-radius: 25px;
            transition: background-color 0.3s ease;
            text-align: center;
            width: 10vw;
        }

        .footer a:hover {
            background-color: #e0b800;
        }

        .header-buttons {
            display: flex;
            gap: 10px;
        }

        .header-buttons a {
            text-decoration: none;
            background-color: #FFBF00;
            color: black;
            padding: 8px 15px;
            border-radius: 25px;
            transition: background-color 0.3s ease;
            font-size: 14px;
        }

        .header-buttons a:hover {
            background-color: black;
            color:white;
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fwrp", "root", "root");
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
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (st != null) try { st.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        %>
    </table>
</div>
<div class="footer">
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
