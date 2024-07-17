<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            color: #000000;
            margin: 0;
            padding: 0;
            background-image: url("res/hero3.jpeg"); /* Background image path */
            background-size: cover; 
            background-position: center; 
            background-repeat: no-repeat;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-container {
            width: 400px;
            height:350px;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.8); /* Semi-transparent background */
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            backdrop-filter: blur(10px);
        }

        h2 {
            margin-bottom: 20px;
            color: #2c3e50;
        }

        input[type="email"],
        input[type="password"] {
            width: 80%;
            margin: 10px 0;
            padding: 15px 30px;
            border: 1px solid #ccc;
            border-radius: 25px;
            transition: border-color 0.3s;
        }

        input[type="email"]:focus,
        input[type="password"]:focus {
            border-color: grey;
            outline: none;
        }

        button[type="submit"] {
            background-color: #000000;
            color: #fff;
            padding: 15px 30px;
            border: 1px solid black;
            border-radius: 25px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        button[type="submit"]:hover {
            background-color: #FFBF00;
            color: black;
        }

        p {
            font-size: 14px;
            margin: 10px 0;
        }

        @media (max-width: 500px) {
            .login-container {
                width: 90%;
            }
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>LOGIN</h2>
    
    <form action="LoginServlet" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        
        <button type="submit">Login</button>
    </form>
    <br>
        <p>Create Account! <a href="./register.jsp">Registration</a></p> 

</div>

</body>
</html>
