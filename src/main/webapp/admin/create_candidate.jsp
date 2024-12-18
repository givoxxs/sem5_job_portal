<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Candidate</title>
    <%@include file="../includes/header.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/register_candidate.css">
</head>

<body>
    <div class="candidate-container">
        <h1>Add Candidate</h1>
        <form class="candidate-form"  action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
        	<input type="hidden" id="candidateId" name="candidateId" value=${candidateId } >
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
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" placeholder="Input email" required>
            </div>
			<div class="form-group">
                <label for="cv">Your CV:</label>
                <input type="file" accept=".pdf,.png" id="cv" name="image" required> <!-- name là image vì servlet Upload cần param tên là image -->
            </div>
            
         	<!-- Trường ẩn chỉ định servlet chuyển tiếp -->
	        <input type="hidden" name="redirect" value="admin/create-candidate">
	        
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