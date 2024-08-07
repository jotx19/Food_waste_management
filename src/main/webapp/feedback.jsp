<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Feedback View</title>
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
            margin: 80px 200px ; 
            max-width: 100%;
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
        .footer {
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
        .footer a {
            text-decoration: none;
            background-color: black;
            color: white;
            padding: 10px 20px;
            border-radius: 25px;
            transition: background-color 0.3s ease;
            text-align: center;
            width: 7vw;
        }
        .footer a:hover {
            background-color: #e0b800;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <div class="header">
        <h1>Feedback View</h1>
        <div class="header-buttons">
            <a href="LoginServlet" class="button">Logout</a>
        </div>
    </div>

    <!-- Main content container -->
    <div class="container">
        <table>
            <tr>
                <th>Feedback ID</th>
                <th>User ID</th>
                <th>Date</th>
                <th>Feedback</th>
                <th>Rating</th>
            </tr>
            <!-- Loop through feedback -->
            <%
                Connection connection = null;
                Statement st = null;
                ResultSet rs = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fwrp", "root", "Nimrat22$");
                    st = connection.createStatement();
                    String query = "SELECT * FROM Feedback";
                    rs = st.executeQuery(query);
                    while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getInt("feedback_id") %></td>
                <td><%= rs.getString("UserID") %></td>
                <td><%= rs.getDate("feedback_date") %></td>
                <td><%= rs.getString("feedback_text") %></td>
                <td><%= rs.getInt("rating") %></td>
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

</body>
</html>
