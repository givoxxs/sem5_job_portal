<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <link rel="stylesheet" type="text/css" href="assets/css/styles.css"> <%--  Link to your CSS if needed --%>
    <style>
        /* Style for the login form container */
        .login-container {
            width: 300px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Add shadow (optional) */
        }

        /* Style for form elements */
        .login-container label,
        .login-container input[type="text"],
        .login-container input[type="password"] {
            display: block;
            width: 100%;
            margin-bottom: 10px;
            padding: 8px;
            box-sizing: border-box; /* Include padding in width */
        }

        /* Style for the submit button */
        .login-container input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

		/* Style for error messages */
.error-message {
    color: red;
    margin-bottom: 10px;
}
    </style>
</head>
<body>

    <div class="login-container">
        <h2>Đăng nhập</h2>

        <%-- Error message (if any) --%>
       <p class="error-message"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></p>


        <form action="login" method="post">
            <label for="username">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" required><br><br>

            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" required><br><br>

            <input type="submit" value="Đăng nhập">
        </form>
    </div>

</body>
</html>