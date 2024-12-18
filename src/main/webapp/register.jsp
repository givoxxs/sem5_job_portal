<%@ page import="model.bean.Account" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% 
	Account account=(Account) request.getSession().getAttribute("account"); 
	if (account !=null) {
		request.setAttribute("account", account); 
	} 
%>
<!DOCTYPE html>
<html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Register</title>
                <%@include file="includes/header.jsp" %>
                <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/register.css">
                <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/navbar.css">
            </head>
            <style>
            </style>

            <body>
                <%@include file="includes/navbar.jsp" %>
                    <div class="register-container">
                        <h1>Register</h1>
                        <form class="register-form" action="auth/register" method="POST">
                            <div class="role">
                                <label class="role-option">
                                    <input type="radio" name="role" value="employer" checked> Employer
                                </label>
                                <label class="role-option">
                                    <input type="radio" name="role" value="candidate"> Candidate
                                </label>
                            </div>
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
                            <div class="form-group">
                                <label for="confirmPassword">Confirm password:</label>
                                <input type="password" id="confirm_password" name="confirm_password"
                                    placeholder="Confirm password" required>
                            </div>
                            <div class="error" id="register-error">
                                <% String error=(String) request.getAttribute("error"); if (error !=null) { %>
                                    <%= error %>
                                        <% } %>
                            </div>
                            <button type="submit" class="register-btn">Register</button>
                        </form>
                    </div>
                    <%@include file="includes/footer.jsp" %>
                        <script src="${pageContext.request.contextPath}/assets/js/register.js"></script>
            </body>

</html>