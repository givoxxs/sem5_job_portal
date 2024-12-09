<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.bean.Employer" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employer profile</title>
<link rel="stylesheet" href="assets/css/employer/form.css" />
</head>
<body>
<% 
    Employer emp = (Employer) request.getAttribute("employer"); 
    if (emp == null) { 
        out.println("Employer not found!");
        return; 
    }
%>
    <h2>Employer profile</h2>
    <form action="EmployerServlet?action=update" method="post">
        <label for="id">Employer ID:</label><br>
        <input type="text" id="id" name="id" value="<%= emp.getId() %>" readonly><br><br>

        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name" value="<%= emp.getName() %>" required><br><br>

        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" value="<%= emp.getEmail() %>" required><br><br>
        
        <label for="address">Address:</label><br>
        <textarea id="address" name="address" required><%= emp.getAddress() %></textarea><br><br>

        <label for="link">Connect:</label><br>
        <input type="text" id="link" name="link" value="<%= emp.getLink() %>" required><br><br>
        
        <label for="description">Description:</label><br>
        <input type="text" id="description" name="description" value="<%= emp.getDescription() %>" required><br><br>
        
        <input type="submit" value="Update">
    </form>
</body>
</html>
