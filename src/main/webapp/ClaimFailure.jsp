<%-- 
    Document   : ClaimFailure
    Created on : Jul. 24, 2024, 3:46:43 p.m.
    Author     : 14375
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Claim Failed</title>
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
        .container h1 {
            align-content: center;
            text-align: center;
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
        .button {
            text-decoration: none;
            background-color: #FFBF00;
            color: black;
            padding: 10px 25px; 
            border-radius: 25px;
            transition: background-color 0.3s ease;
            font-size: 14px; 
            display: block;
            text-align: center;
            margin: auto;
        }
        .button:hover {
            background-color: black;
            color: white;
        }
        .button-container {
            display: flex;
            justify-content: center;
        }
    </style>
    </head>
    <body>
        <div class="header">
            <h1>FWRP</h1>
            <div class="header-buttons">
                <a href="LoginServlet" class="button">Logout</a>
            </div>
        </div>
        <div class="container">
            <h1>Failed to Claim Food Item. Please try again.</h1>
                <form action="Claim-charity.jsp" method="post">
                    <input type="hidden" name="action" value="claim">
                    <button type="submit" class="button">Back to Charity Page</button>
                </form>
        </div>
    </body>
</html>