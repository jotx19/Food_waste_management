<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Inventory</title>
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
            margin: 50px 350px;
            max-width: 1200px;
            padding: 40px;
            max-width: 100%;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .container h1 {
            text-align: center;
        }
        .inventory-item {
            margin-bottom: 20px;
            padding: 20px;
            background-color: #f5f5f5;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .inventory-item h2 {
            margin-top: 0;
        }
        .inventory-item form {
            display: flex;
            flex-direction: column;
        }
        .inventory-item label {
            /*margin-bottom: 2px;*/
            font-weight: bold;
        }
        .inventory-item input[type="text"],
        .inventory-item input[type="number"],
        .inventory-item input[type="date"],
        .inventory-item select {
            margin-bottom: 1px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            width: 100%;
        }
        .inventory-item button[type="submit"] {
            background-color: black;
            color: white;
            padding: 10px;
            width: 50%;
            align-items: center;
            border-radius: 25px;
            border: none;
            transition: background-color 0.3s ease;
            font-size: 14px;
            align-self: center;
        }
        .inventory-item button[type="submit"]:hover {
            background-color: yellow;
            color: black;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>Update Inventory</h1>
        <div class="header-buttons">
            <a href="LoginServlet" class="button">Logout</a>
        </div>
    </div>
    <div class="container">
        <div class="inventory-item">
            <form action="InventoryServlet" method="post">
                <input type="hidden" name="action" value="update">
                <label for="itemId">Item ID:</label>
                <input type="number" name="itemId" id="itemId" required><br>
                
                <label for="newItemName1">Item Name:</label>
                <input type="text" name="newItemName" id="newItemName1" required><br>
                
                <label for="newQuantity1">Quantity:</label>
                <input type="number" name="newQuantity" id="newQuantity1" required><br>
                
                <label for="newRetailerPrice1">Retailer Price:</label>
                <input type="number" name="newRetailerPrice" id="newRetailerPrice1" required><br>
                
                <label for="newIsDonation1">Donation:</label>
                <select name="newIsDonation" id="newIsDonation1">
                    <option value="true">Yes</option>
                    <option value="false" selected>No</option>
                </select><br>
                
                <label for="newIsSale1">Sale Price:</label>
                <select name="newIsSale" id="newIsSale1">
                    <option value="true" selected>Yes</option>
                    <option value="false">No</option>
                </select><br>
                
                <label for="newDiscountPrice1">Discount Price:</label>
                <input type="number" name="newDiscountPrice" id="newDiscountPrice1" required><br>
                
                <label for="newExpirationDate1">New Expiration Date:</label>
                <input type="date" name="newExpirationDate" id="newExpirationDate1" required><br>
                
                <button type="submit">Update Inventory</button>
            </form>
        </div>
    </div>
</body>
</html>
