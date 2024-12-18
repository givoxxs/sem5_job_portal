<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add employer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/register_employer.css">
</head>

<body>
    <div class="employer-container">
        <h1>Add Employer</h1>
        <form class="employer-form"  action="${pageContext.request.contextPath}/admin/create-employer" method="post">
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
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" placeholder="Input name" required>
            </div>
			<div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" placeholder="Input address" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" placeholder="Input email" required>
            </div>
            <div class="form-group"> 
                <label for="urlInput">Link:</label>
                <input type="url" id="urlInput" name="urlInput" placeholder="Input link"> 
                <input type="hidden" id="link" name="link">
                <button type="button" onclick="addLink()">Add</button>
                <label for="linkList">Link list:</label>
				<ul id="linkList"></ul>	
            </div>
			<div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" placeholder="Input description" required> </textarea>
            </div>
            <div class="error" id="employer-error">
                <% 
			        String error = (String) request.getAttribute("error");
			        if (error != null) { 
			    %>
			        <%= error %>
			    <% 
			        } 
			    %>
            </div>
            <button type="submit" class="employer-btn">Add</button>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/assets/js/register_employer.js"></script>
</body>

</html>