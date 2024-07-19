<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Inventory</title>
    <style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0; /* Added padding */
    /*background-color: #f5f5f5; /* Light gray background */
    background-image: url('./css/food.jpeg');
}
.header {
          
            background-color: #293d3d;
            color: #fff;
            padding: 1px;
            text-align: center;
            font-size: 24px;
        }

form {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    background-color: #fff; /* White container background */
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: center; /* Center align form elements */
    margin-bottom: 20px; /* Add margin-bottom */
}

input[type="text"],
input[type="number"],
input[type="date"],
select {
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

button[type="submit"]:hover {
    background-color: #1d2929; /* Darker shade on hover */
}

.footer {
            
            bottom: 0;
            left: 0;
            width: 100%;
            text-align: center;
            padding: 1px;
            background-color: #293d3d;
            color: #fff;
            border-radius: 0 0 5px 5px;
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
        <h1>Add Inventory</h1>
        <div class="header-buttons">
            <a href="LoginServlet" class="button">Logout</a>
            
        </div>
    </div>
    <form action="InventoryServlet" method="post">
        <input type="hidden" name="action" value="add">
        <label for="itemName">Item Name:</label>
        <input type="text" id="itemName" name="itemName" required><br>
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required><br>
        <label for="expirationDate">Expiration Date:</label>
        <input type="date" id="expirationDate" name="expirationDate" required><br>
        <label for="retailerPrice">Retailer Price:</label>
        <input type="number" id="retailerPrice" name="retailerPrice" required><br>
        <label for="isDonation">Is Donation:</label>
        <select id="isDonation" name="isDonation">
            <option value="true">Yes</option>
            <option value="false">No</option>
        </select><br>
        <label for="isSale">Is Sale:</label>
        <select id="isSale" name="isSale">
            <option value="true">Yes</option>
            <option value="false">No</option>
        </select><br>
        <label for="discountPrice">Discount Price:</label>
        <input type="number" id="discountPrice" name="discountPrice" required><br>
        <button type="submit">Add Inventory</button>
    </form>
    <div class="footer">
        <p>© 2024 Food Waste Reduction Platform</p>
    </div>
</body>
</html>
