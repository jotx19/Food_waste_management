<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Inventory</title>
    <style>
       /* CSS Styles */
/* CSS Styles */
body {
  font-family: Arial, sans-serif;
    display: flex;
    flex-direction: column;
    min-height: 100vh; /* Set minimum height to full viewport height */
    margin: 0;
    padding: 0; /* Removed padding */
   /* background-color: #f5f5f5; /* Light gray background */
   background-image: url('./css/food.jpeg');
}

.header {
    background-color: #293d3d; /* Dark gray header background */
    color: #fff;
    padding: 20px;
    text-align: center;
    font-size: 24px;
    width: 100%; /* Make header full width */
}

.container {
    flex: 1; /* Grow to fill remaining space */
    padding: 20px; /* Added padding */
    margin-top: 20px; /* Added margin */
    display: flex;
    flex-direction: column;
    align-items: center;
}

form {
margin: 20px auto; 
    max-width: 400px;
   
    padding: 20px;
    background-color: #fff; /* White container background */
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: center; /* Center align form elements */
}

input[type="number"] {
    width: calc(100% - 22px); /* Adjusted width */
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc; /* Light gray border */
    border-radius: 4px;
    box-sizing: border-box;
}

button[type="submit"] {
    width: calc(100% - 22px); /* Adjusted width */
    padding: 10px;
    background-color: #293d3d; /* Same color as header */
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.footer {
    text-align: center;
    padding: 20px;
    background-color:#293d3d;
    color: #fff;
    width: 100%;
    position: fixed;
    bottom: 0;
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


    </style>
</head>
<body>
    <div class="header">
        <h1>Delete Inventory</h1>
        <div class="header-buttons">
            <a href="LoginServlet" class="button">Logout</a>
            
</div>
    </div>
    <form action="InventoryServlet" method="post">
        <input type="hidden" name="action" value="delete">
        Enter Item ID to delete: <input type="number" name="itemId" required><br>
        <button type="submit">Delete Inventory</button>
    </form>
    <div class="footer">
        <p>© 2024 Food Waste Reduction Platform</p>
    </div>
</body>
</html>
