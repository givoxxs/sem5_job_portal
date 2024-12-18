<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.bean.Candidate" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit profile</title>
    <%@include file="../includes/header.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/register_candidate.css">
</head>

<body>
    <div class="candidate-container">
        <h1>Edit Candidate</h1>
        <form class="candidate-form"  action="${pageContext.request.contextPath}/admin/update-candidate" method="post">
        	<input type="hidden" id="candidateId" name="candidateId" value=${candidate.id } >
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Input username" value='${candidateAccount.username }' readonly>
            </div>
            <div class="form-group">
                <label for="password">New Password:</label>
                <input type="password" id="password" name="password" placeholder="Input new password if you want to change">
            </div>
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" placeholder="Input name" value='${ candidate.name}' required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" placeholder="Input email" value='${candidate.email }' required>
            </div>
	        
            <div class="error" id="candidate-error">
                <% 
			        String error = (String) request.getAttribute("error");
			        if (error != null) { 
			    %>
			        <%= error %>
			    <% 
			        } 
			    %>
            </div>
            <button type="submit" class="candidate-btn">Add</button>
        </form>
    </div>
</body>

</html>