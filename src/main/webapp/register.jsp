<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Page</title>
    <style>
        body {
            display: 100vh;
            height: 85vh;
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            color: #000000;
            margin: 0;
            padding: 0;
            background-image: url("res/hero3.jpeg");
            background-size: cover; 
            background-position: center; 
            background-repeat: no-repeat; 
        }

        .register-container {
            
            height: 400px;
            max-width: 500px;
            margin: 100px auto;
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

        input[type="text"],
        input[type="email"],
        input[type="password"],
        select {
            width: 80%;
            margin: 10px 0;
            border: 1px solid #ccc;
            padding: 15px 30px; 
            border: 1px solid black;
            border-radius: 25px;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="password"]:focus,
        select:focus {
            border-color: grey;
            outline: none;
        }

       input[type="submit"] {
         background-color: #000000;
         color: #fff;
         padding: 15px 30px; 
         border: 1px solid black;
         border-radius: 25px; 
         cursor: pointer;
         font-size: 16px;
         transition: background-color 0.3s;
}


        input[type="submit"]:hover {
            background-color: #FFBF00;
            color: black;
        }

        p {
            font-size: 14px;
            margin: 10px 0;
        }

        ul {
            list-style-type: none;
            padding: 0;
            text-align: left;
        }

        li {
            margin: 5px 0;
        }
       select {
           width: 60%; 
           padding: 10px; 
           border: 1px solid #ccc;
           border-radius: 25px;
           background-color: #fff; 
           background-position: right 10px left;
           background-size: 12px; 
           font-size: 14px; 
}

        @media (max-width: 500px) {
            .register-container {
                width: 90%;
            }
        }
    </style>
</head>
<body>

<div class="register-container">
    <h2>REGISTER</h2>
    
    <form id="registrationForm" action="UserRegistration" method="post">
        <input type="text" name="name" placeholder="Name" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" id="password" placeholder="Password" required>
        <select name="userType" required>
            <option value="" disabled selected>Select User Type</option>
            <option value="Retailer">Retailer</option>
            <option value="Consumer">Consumer</option>
            <option value="Charity">Charitable Organization</option>
        </select>

        <input type="submit" value="Register ">
    </form>
</div>

<script>
    const registrationForm = document.getElementById('registrationForm');
    const passwordInput = document.getElementById('password');
    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

    registrationForm.addEventListener('submit', function(event) {
        if (!passwordPattern.test(passwordInput.value)) {
            event.preventDefault(); // Prevent form submission
            alert('Password must be at least 8 characters and meet the specified requirements.');
        }
    });
</script>

</body>
</html>
