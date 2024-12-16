<%@ page import="model.bean.Account" %>
<%@ page import="model.bo.JobBO" %>
<%@ page import="model.bo.SalaryRangeBO" %>
<%@ page import="model.bean.Job" %>
<%@ page import="model.bean.SalaryRange" %>
<%@ page import="model.bean.Job_Application" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Account account = (Account) request.getSession().getAttribute("account");
if (account != null) {
	request.setAttribute("account", account);
}

//Xử lý tìm kiếm
String job_id = request.getParameter("job_id");
String name = request.getParameter("name");

List<Job_Application> job_applications = (List<Job_Application>) request.getAttribute("listjobapplication");
int noOfPages = (int) request.getAttribute("noOfPages");
int currentPage = (int) request.getAttribute("currentPage");
boolean search = false;
if(request.getAttribute("search") != null) {
	search = (boolean) request.getAttribute("search");
}
%>
<!DOCTYPE html>
<html>
<head>
	<title>Job application</title>
	<%@include file="../includes/header.jsp"%>
	<link rel="stylesheet" href="assets/css/index.css">
	<link rel="stylesheet" href="assets/css/navbar.css">
	<link rel="stylesheet" href="assets/css/styles.css">
	<link rel="stylesheet" href="assets/css/employer/button.css">
</head>
<style>
h1 {
	text-align: center;
	margin-top: 30px;
	text-transform: uppercase;
	color: #2c3e50;
}
</style>
<body>
	
	<div class="container">
	    <!-- Search Form -->
	    <h1>Find Job Application</h1>
	    
	    <form action="job_applicationServlet?action=search" method="POST" class="search-bar">
	    	<input type="hidden" name="job_id" value="<%= job_id %>"/>
	        <!-- Search by job name -->
	        <input type="text" name="name" placeholder="Search Job application by Name" 
	        style="width: 80%;"/>
	
	        <!-- Search button -->
	        <button type="submit" style= "width: 20%;">Search</button>
	    </form>
	            <!-- Filter Form -->
            <h2>Application of job: <%= (String) request.getAttribute("job_id") %> </h2>
            <div class="job-list">
        <% for (Job_Application ja : job_applications) { %>
            <div class="job-item">
                <div class="job-title">
                    <h3><%= ja.getName() %></h3>
                </div>
                <div class="job-details">
                    <span class="detail-item"><i class="fa fa-id-badge"></i><a href =" <%= ja.getCv_url() %> " target ="_blank">CV</a></span>
                    <span class="detail-item"><i class="fa fa-envelope"></i> <%= ja.getEmail() %></span>
                    <span class="detail-item"><i class="fa fa-phone"></i> <%= ja.getPhone() %></span>
                    <%-- Hiển thị nút chấp nhận hoặc từ chối--%>
                    <% 
                    String status = ja.getStatus();
                    if(status.equals("Pending")) {
                    	%>
                    	<a href="job_applicationServlet?action=updateStatusJob_application&jobapp_id=<%=ja.getId() %>&status=Reject" class="button">Reject</a>
                    	<a href="job_applicationServlet?action=updateStatusJob_application&jobapp_id=<%=ja.getId() %>&status=Accept" class="button">Accept</a>
                    <%
                    }else{
                    	%>
                    	<span class="detail-item"><i class="fa fa-bookmark"></i> <%= status %></span>
                    	<%
                    }
                    %>
                    
                    
                </div>
            </div>
        <% } %>
    </div>
    
    <!-- Pagination -->
    <div class="pagination">
        <% for (int i = 1; i <= noOfPages; i++) { %>
        <% if(search){
        	%>
			<a
				href="job_applicationServlet?action=search&name=<%= name%>&job_id=<%= job_id %>&page=<%= i %>"
				class="<%= (i == currentPage) ? "active" : "" %>"><%= i %></a> 
				<%}else{
        	%>
            <a href="job_applicationServlet?action=showJob_applicationOfJob&page=<%= i %>" class="<%= (i == currentPage) ? "active" : "" %>"><%= i %></a>
        <% }
        } %>
    </div>
    
	</div>
	<%@include file="../includes/footer.jsp"%>
</body>
</html>