<%@ page import="model.bean.Account" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <% Account account=(Account) request.getSession().getAttribute("account"); if (account !=null) {
            request.setAttribute("account", account); request.getRequestDispatcher(request.getContextPath() + "/"
            ).forward(request, response); } %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Login</title>
                <%@include file="includes/header.jsp" %>
                <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
                <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navbar.css">
            </head>

            <body>
                <%@include file="includes/navbar.jsp" %>
                    <div class="login-container">
                        <h1>Login</h1>
                        <form class="login-form" action="${pageContext.request.contextPath}/auth/login" method="post">
                            <div class="form-group">
                                <label for="username">Username:</label>
                                <input type="text" id="username" name="username" placeholder="Input username"
                                    required>
                            </div>
                            <div class="form-group">
                                <label for="password">Password:</label>
                                <input type="password" id="password" name="password" placeholder="Input password"
                                    required>
                            </div>
                            <div class="error" id="login-error">
                                <% String error=(String) request.getAttribute("error"); if (error !=null) { %>
                                    <%= error %>
                                        <% } %>
                            </div>
                            <button type="submit" class="login-btn">Login</button>
                        </form>
                    </div>
                    <%@include file="includes/footer.jsp" %>
            </body>

            </html>