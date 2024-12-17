<%@ page import="model.bean.Account" %>
<%@ page import="model.bo.JobBO" %>
<%@ page import="model.bo.SalaryRangeBO" %>
<%@ page import="model.bean.Job" %>
<%@ page import="model.bean.SalaryRange" %>
<%@ page import="model.bean.Job" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Account account = (Account) request.getSession().getAttribute("account");
if (account != null) {
	request.setAttribute("account", account);
}


List<SalaryRange> salaryRanges = request.getAttribute("salaryRanges") != null ? (List<SalaryRange>) request.getAttribute("salaryRanges") : new ArrayList<>();

List<String> locations = new ArrayList<>();
locations.add("Hà Nội");
locations.add("Hồ Chí Minh");
locations.add("Đà Nẵng");
locations.add("Khác");

//Xử lý tìm kiếm
String jobName = request.getParameter("jobName");
String salaryRangeId = request.getParameter("salaryRange");
String jobType = request.getParameter("jobType");
String experience = request.getParameter("experience");
String location = request.getParameter("location");

	List<Job> recentJobs = (List<Job>) request.getAttribute("listjob");
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
	<title>Job Portal</title>
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
<%
	if(request.getAttribute("message") != null) {
			%>
	<script>
                alert("${message}");
                </script>
			<%
		}
	%>
	
	<div class="container">
	    <!-- Search Form -->
	    <h1>Find My Job</h1>
	    
	    <form action="JobServlet?action=search" method="POST" class="search-bar">
	        <!-- Search by job name -->
	        <input type="text" name="jobName" placeholder="Search Job by Name" value="<%= jobName != null ? jobName : "" %>"/>
	
	        <!-- Salary Range filter -->
	        <select name="salaryRange">
	            <option value="">Select Salary Range</option>
	            <% for (SalaryRange salaryRange : salaryRanges) { %>
	                <option 
	                	value="<%= salaryRange.getId() %>" 
	                	<%= salaryRangeId != null && salaryRangeId.equals(salaryRange.getId()) 
	                	? "selected" 
	                	: "" %>
	                >
                		<%= salaryRange.getSalaryRange() %>
                	</option>
	            <% } %>
	        </select>
	        <!-- Job Type filter -->
	        <select name="jobType">
	            <option value="">Select Job Type</option>
	            <option 
	            	value="full-time" 
	            	<%= jobType != null && jobType.equals("full-time") ? "selected" : "" %>
	            >
	            	Full-time
	            </option>
	            <option 
	            	value="part-time" 
	            	<%= jobType != null && jobType.equals("part-time") ? "selected" : "" %>
	            >
	            	Part-time
	            </option>
	            <option 
	            	value="internship" 
	            	<%= jobType != null && jobType.equals("internship") ? "selected" : "" %>
	            >
	            	Internship
	            </option>
        	</select>
        	<!-- Experience filter -->
        	<select name="experience">
	            <option value="">Select Experience</option>
	            <option value="Intern" <%= experience != null && experience.equals("Intern") ? "selected" : "" %>>Intern</option>
	            <option value="Fresher" <%= experience != null && experience.equals("Fresher") ? "selected" : "" %>>Fresher</option>
	            <option value="Junior" <%= experience != null && experience.equals("Junior") ? "selected" : "" %>>Junior</option>
	            <option value="Middle" <%= experience != null && experience.equals("Middle") ? "selected" : "" %>>Middle</option>
	            <option value="Senior" <%= experience != null && experience.equals("Senior") ? "selected" : "" %>>Senior</option>
	            <option value="Lead" <%= experience != null && experience.equals("Lead") ? "selected" : "" %>>Lead</option>
        	</select>
	
	 		<select name="location">
	            <option value="">Select Location</option>
	            <% for (String loc : locations) { %>
	                <option value="<%= loc %>" <%= location != null && location.equals(loc) ? "selected" : "" %>><%= loc %></option>
	            <% } %>
        	</select>
	        <!-- Search button -->
	        <button type="submit">Search</button>
	    </form>
	    
            <h2>Your Job</h2>
            <div class="job-list">
        <% for (Job job : recentJobs) { %>
            <div class="job-item">
                <div class="job-title">
                    <h3><a href="JobServlet?action=showjob&jobid=<%= job.getId() %>"><%= job.getTitle() %></a></h3>
                </div>
                <div class="job-details">
                    <span class="detail-item"><i class="fas fa-map-marker-alt"></i> <%= job.getLocation() %></span>
                    <span class="detail-item"><i class="fas fa-briefcase"></i> <%= job.getJobType() %></span>
                    <span class="detail-item"><i class="fas fa-coins"></i> <%= job.getSalaryRange() %></span>
                    <span class="detail-item"><i class="fas fa-user-graduate"></i> <%= job.getExperience() %></span>
                    <span class="detail-item"><i class="fas fa-calendar-alt"></i> <%= job.getDatePost() %></span> <%-- Format date if needed --%>
                    <%-- Hiển thị isAvailable nếu cần thiết--%>
                    <span class="detail-item"><i class="fas fa-check-circle"></i> <%= job.isAvailable() ? "Available" : "Not Available" %></span>
                    <span class="detail-item">
                    <a href="JobServlet?action=updateavaible&jobid=<%= job.getId() %>&status=<%= job.isAvailable() ? "false" : "true" %>" class="button"><%= job.isAvailable() ? "Hiring Freeze" : "Continue hiring" %></a>
                    <a href="job_applicationServlet?action=showJob_applicationOfJob&job_id=<%= job.getId() %>" class="button">View applications</a>
                    </span>           
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
				href="JobServlet?action=search&jobName=<%= jobName %>&salaryRange=<%= salaryRangeId %>&jobType=<%= jobType %>&experience=<%= experience %>&location=<%= location %>&page=<%= i %>"
				class="<%= (i == currentPage) ? "active" : "" %>"><%= i %></a> 
				<%}else{
        	%>
            <a href="JobServlet?action=showjob&page=<%= i %>" class="<%= (i == currentPage) ? "active" : "" %>"><%= i %></a>
        <% }
        } %>
    </div>
     
	</div>
	<%@include file="../includes/footer.jsp"%>
</body>
</html>