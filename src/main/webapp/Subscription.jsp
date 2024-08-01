<%--
  Created by IntelliJ IDEA.
  User: ham
  Date: 7/31/24
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Subscribe for Surplus Food Alerts</title>
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
        }

        .container h1 {
            align-content: center;
            text-align: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: black;
            color: white;
            padding: 14px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #FFBF00;
            color: black;
        }

        .footer {
            display: flex;
            justify-content: center;
            padding: 15px;
            position: fixed;
            bottom: 0;
            width: 100%;
            align-items: center;
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
            color: black;
        }
    </style>
</head>
<body>
<div class="header">
    <h1>Subscribe for Surplus Food Alerts</h1>
    <div class="header-buttons">
        <a href="LoginServlet" class="button">Logout</a>
        <a href="FeedbackServlet" class="button">Feedback</a>
    </div>
</div>
<div class="container">
    <form action="SubscribeServlet" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="location">Location:</label>
        <input type="text" id="location" name="location" required><br><br>

        <label for="communication_method">Preferred Communication Method:</label>
        <select id="communication_method" name="communication_method" required>
            <option value="email">Email</option>
            <option value="phone">Phone</option>
        </select><br><br>

        <label for="food_preferences">Food Preferences (comma-separated):</label>
        <input type="text" id="food_preferences" name="food_preferences"><br><br>

        <input type="submit" value="Subscribe">
    </form>
</div>
<div class="footer">
    <a href="Subscription.jsp" class="subscription-button">Subscribe</a>
</div>
</body>
</html>
