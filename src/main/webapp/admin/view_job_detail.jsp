<%@ page import="model.bean.Job" %>
<%@ page import="model.bo.JobBO" %>
<%@ page import="model.bean.Account" %>
<%@ page import="model.bean.Candidate" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Account account = (Account) request.getSession().getAttribute("account");
if (account != null) {
	request.setAttribute("account", account);
}

Candidate candidate = (Candidate) request.getAttribute("candidate");
if (candidate != null) {
	request.setAttribute("candidate", candidate);
}

String jobId = request.getParameter("id");
Job job = (Job) request.getAttribute("job");

if (job == null) {
	// Xử lý trường hợp không tìm thấy công việc
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= job.getTitle() %></title>
    <%@include file="../includes/header.jsp"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/candidate/job-detail.css"> <!-- Đảm bảo CSS được tải -->
</head>
<body>
<%@include file="../includes/navbar.jsp"%>
<div class="container">
	<!-- Thêm nút Quay Lại -->
		<% if (request.getAttribute("error") != null) { %>
		<p style="color: red;"><%= request.getAttribute("error") %></p>
	<% } %>
	
	<button id="backButton" onclick="window.history.back()">Quay Lại</button>
    <h2><%= job.getTitle() %></h2>
    <p><strong>Company:</strong> <%= job.getEmployerName() %></p>
    <p><strong>Salary:</strong> <%= job.getSalaryRange() %></p>
    <p><strong>Location:</strong> <%= job.getLocation() %></p>
    <p><strong>Experience:</strong> <%= job.getExperience() %></p>
    <p><strong>Job Type:</strong> <%= job.getJobType() %></p>
    <p><strong>Description:</strong> <%= job.getDescription() %></p>
</div>
<%@include file="../includes/footer.jsp"%>

<script>
// Script để mở và đóng modal
var modal = document.getElementById("applyModal");
var btn = document.getElementById("applyButton");
var span = document.getElementsByClassName("close")[0];

btn.onclick = function() {
    modal.style.display = "block";
}

span.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>
</body>
</html>
