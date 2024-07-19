<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Inventory</title>
<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    min-height: 100vh;
    background-color: #fff6f6;
/*    background-image: url('res/R.jpg'); 
    background-size: cover; 
    background-position: center;
    background-repeat: no-repeat; */
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
}
.header a:hover {
    background-color: black;
    color: white;
}
.container {
    font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;
/*    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;*/
    /*flex: 1;*/ 
    max-width: 90%;
    margin: 20px auto;
    padding: 20px 20px;
    background-color: #fff; /* White container background */
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin-top: 10vh;
}

.container h1{
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
    text-align: left;
    border-bottom: 1px solid #ddd; /* Gray border bottom */
}

th {
    background-color: #ee96e3;
    color: black;
    text-transform: uppercase;
}

tr:nth-child(even) {
    background-color: #f2f2f2; /* Light gray alternating row background */
}

tr:hover {
    background-color: #ddd; /* Light gray background on hover */
}

footer {
    display: flex;
    justify-content:center;
    /*flex-wrap: wrap;*/
    gap: 10px;
    padding: 10px;
    position: fixed;
    bottom: 0;
    width: 100%;
    align-items: center;
    left: 0;
    /*background: gray;*/
    
}

footer a {
    text-decoration: none;
    background-color: black;
    color: white;
    padding: 10px 20px;
    border-radius: 25px;
    transition: background-color 0.3s ease;
    text-align: center;
    width: 7vw; /* Fixed width for buttons */
}

footer a:hover {
    background-color: #e0b800;
}

.action-buttons {
    text-align: right;
    margin-bottom: 20px;
}

.action-buttons a {
    text-decoration: none;
    background-color: #527a7a; /* Dark gray button background */
    color: #fff;
    padding: 10px 20px;
    border-radius: 4px;
    transition: background-color 0.3s ease;
    margin-left: 10px;
}

.action-buttons a:hover {
    background-color: #1d2929; /* Darker shade on hover */
}
</style>
</head>
<body>
    <div class="header">
        <h1>FWRP</h1>
        <a href="LoginServlet" class="button">Logout</a>
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
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/food", "root", "root");
                stat = connection.createStatement();
                String query = "SELECT * FROM Inventory";
                result = stat.executeQuery(query);
                while (result.next()) {
            %>
            <tr>
                <td><%=result.getString(1)%></td>
                <td><%=result.getString(2)%></td>
                <td><%=result.getString(3)%></td>
                <td><%=result.getString(4)%></td>
                <td><%=result.getString(5)%></td>
                <td><%=result.getString(6)%></td>
                <td><%=result.getString(7)%></td>
                <td><%=result.getString(8)%></td>
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
        
    <footer>
        <a href="InventoryServlet?action=add">Add Item</a>
        <a href="InventoryServlet?action=update">Update Item</a>
        <a href="InventoryServlet?action=delete">Delete Item</a>
        <a href="surplusItem.jsp" class="button">View Surplus</a>
        <a href="feedback.jsp" class="button">VFeedback</a>
    </footer>
        
</body>
</html>
