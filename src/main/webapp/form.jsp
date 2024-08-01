<%-- 
    Document   : ClaimFailure
    Created on : Jul. 17, 2024, 3:46:43 p.m.
    Author     : 14375
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Form</title>
    <style>
        body {
            font-family: owners, Arial, sans-serif;
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
            color: black;
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
        form {
            max-width: 500px;
            margin: 100px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }
        input[type="text"],
        textarea,
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 16px;
        }
        textarea {
            height: 120px;
            resize: vertical;
        }
        input[type="submit"] {
            width: 70%;
            padding: 10px;
            background-color: black;
            border: none;
            color: white;
            border-radius: 25px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s ease;
            align-self: center;
        }
        input[type="submit"]:hover {
            background-color: yellow;
            color: black;
        }
        .footer {
            display: flex;
            justify-content: center;
            gap: 10px;
            padding: 10px;
            position: fixed;
            bottom: 0;
            width: 100%;
            align-items: center;
            left: 0;
            background-color: #293d3d;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Your Feedback Matters</h1>
        <div class="header-buttons">
            <a href="LoginServlet" class="button">Logout</a>
        </div>
    </div>

    <form action="FeedbackServlet" method="post">
        <label for="UserID">User ID:</label>
        <input type="text" id="UserID" name="UserID" required>
        <label for="itemID">Item ID:</label>
        <input type="text" id="itemID" name="itemID" required>
        <label for="textbox">Feedback Text:</label>
        <textarea id="textbox" name="textbox" required></textarea>
        <label for="rating">Rating:</label>
        <input type="number" id="rating" name="rating" min="1" max="5" required>
        <input type="submit" value="Submit Feedback">
    </form>

</body>
</html>
