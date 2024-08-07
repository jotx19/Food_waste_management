<%-- 
    Document   login
    Created on : Jul. 14, 2024, 3:46:43 p.m.
    Author     : 14375
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        body {
    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
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
            background: rgba(30, 20, 23, 0.2);
            backdrop-filter: blur(50px);
            width: 400px;
            height: 350px;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 10px 4px 10px rgba(0, 0, 0, 0.2);
            text-align: center;
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
            padding: 10px 30px;
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

        .loading-overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            z-index: 1000;
            visibility: hidden;
        }

        .loading-overlay.visible {
            visibility: visible;
        }

        .loading-spinner {
            width: 50px;
            height: 50px;
            border: 5px solid rgba(0, 0, 0, 0.1);
            border-top-color: #000;
            border-radius: 50%;
            animation: spin 0.5s linear infinite;
        }

        @keyframes spin {
            to { transform: rotate(360deg); }
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>LOGIN</h2>
    
    <form id="loginForm" action="LoginServlet" method="post">
        <input type="email" id="email" name="email" placeholder="Email" required><br>
        
        <input type="password" id="password" name="password" placeholder="Password" required><br>
        <br>
        <button type="submit">Login</button>
    </form>
    <br>
    <p>Create Account! <a href="./register.jsp">Registration</a></p> 
    <p><a href="http://localhost:8080/WebApplication/">Home</a></p>
</div>

<div class="loading-overlay" id="loadingOverlay">
    <div class="loading-spinner"></div>
</div>

<script>
    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault();
        document.getElementById('loadingOverlay').classList.add('visible');
        setTimeout(() => {
            this.submit();
        }, 200);
    });

    window.addEventListener('pageshow', function(event) {
        if (event.persisted || (window.performance && window.performance.navigation.type == 2)) {
            document.getElementById('loadingOverlay').classList.remove('visible');
        }
    });
</script>

</body>
</html>
