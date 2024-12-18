<%@ page import="model.bean.Account" %>
<%@ page import="model.bean.Job" %>
<%@ page import="model.bean.SalaryRange" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
// Lấy dữ liệu từ request do Servlet truyền sang
    Account account =(Account) request.getSession().getAttribute("account"); 
	if (account != null) {
		request.setAttribute("account", account);
	}
	List<Job> recentJobs = (List<Job>) request.getAttribute("recentJobs");
	List<Job> randomJobs = (List<Job>) request.getAttribute("randomJobs");
	List<Job> searchResults = (List<Job>) request.getAttribute("searchResults");
	List<SalaryRange> salaryRanges = (List<SalaryRange>) request.getAttribute("salaryRanges");
	List<String> locations = (List<String>) request.getAttribute("locations");
	// Lấy totalPages, nếu không có hoặc không thể chuyển đổi, mặc định là 1
	int totalPages = 1;
	Object totalPagesAttr = request.getAttribute("totalPages");
	if (totalPagesAttr != null) {
		try {
	totalPages = Integer.parseInt(totalPagesAttr.toString());
		} catch (NumberFormatException e) {
	totalPages = 1; // Nếu không thể chuyển đổi, gán giá trị mặc định là 1
		}
	}

	// Lấy currentPage, nếu không có hoặc không thể chuyển đổi, mặc định là 1
	int currentPage = 1;
	Object currentPageAttr = request.getAttribute("page");
	if (currentPageAttr != null) {
		try {
	currentPage = Integer.parseInt(currentPageAttr.toString());
		} catch (NumberFormatException e) {
	currentPage = 1; // Nếu không thể chuyển đổi, gán giá trị mặc định là 1
		}
	}

	String jobName = request.getParameter("jobName");
	String salaryRangeId = request.getParameter("salaryRange");
	String jobType = request.getParameter("jobType");
	String experience = request.getParameter("experience");
	String location = request.getParameter("location");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Job Portal</title>
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/navbar.css">
    
    <%@include file="includes/header.jsp"%>
</head>
<body>
    <%@include file="includes/navbar.jsp"%>

    <div class="container">
        <!-- Search Form -->
        <h1>Find Your Dream Job</h1>
        <form action="job-search" method="GET" class="search-bar">
            <input type="text" name="jobName" placeholder="Search Job by Name" value="<%= jobName != null ? jobName : "" %>"/>
            <select name="salaryRange">
                <option value="">Select Salary Range</option>
                <% for (SalaryRange salaryRange : salaryRanges) { %>
                    <option value="<%= salaryRange.getId() %>" <%= salaryRangeId != null && salaryRangeId.equals(salaryRange.getId()) ? "selected" : "" %> >
                        <%= salaryRange.getSalaryRange() %>
                    </option>
                <% } %>
            </select>
            <select name="jobType">
                <option value="">Select Job Type</option>
                <option value="full-time" <%= jobType != null && jobType.equals("full-time") ? "selected" : "" %>>Full-time</option>
                <option value="part-time" <%= jobType != null && jobType.equals("part-time") ? "selected" : "" %>>Part-time</option>
                <option value="internship" <%= jobType != null && jobType.equals("internship") ? "selected" : "" %>>Internship</option>
            </select>
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
                    <option value="<%= loc %>" <%= location != null && location.equals(loc) ? "selected" : "" %>> <%= loc %> </option>
                <% } %>
                <option value="" <%= location != null && location.equals("other") ? "selected" : "" %>>Other</option>
            </select>
            <button type="submit">Search</button>
        </form>

        <% if (searchResults == null) { %>
            <h2>Top 6 Latest Jobs</h2>
            <div class="job-list">
        <% for (Job job : recentJobs) { %>
            <div class="job-item">
                <div class="job-title">
                    <h3><a href="job-detail?id=<%= job.getId() %>"><%= job.getTitle() %></a></h3>
                </div>
                <div class="job-details">
                    <span class="detail-item"><i class="fas fa-map-marker-alt"></i> <%= job.getLocation() %></span>
                    <span class="detail-item"><i class="fas fa-briefcase"></i> <%= job.getJobType() %></span>
                    <span class="detail-item"><i class="fas fa-coins"></i> <%= job.getSalaryRange() %></span>
                    <span class="detail-item"><i class="fas fa-user-graduate"></i> <%= job.getExperience() %></span>
                    <span class="detail-item"><i class="fas fa-calendar-alt"></i> <%= job.getDatePost() %></span> <%-- Format date if needed --%>
                    <%-- Hiển thị isAvailable nếu cần thiết--%>
                    <span class="detail-item"><i class="fas fa-check-circle"></i> <%= job.isAvailable() ? "Available" : "Not Available" %></span>
                </div>
            </div>
        <% } %>
        </div>
        <% } else {%>
        <h2>Search Results</h2>
        <div class="job-list">
           <% for (Job job : searchResults) { %>
               <div class="job-item">
                 <div class="job-title">
                    <h3><a href="job-detail?id=<%= job.getId() %>"><%= job.getTitle() %></a></h3>
                </div>
                <div class="job-details">
                    <span class="detail-item"><i class="fas fa-map-marker-alt"></i> <%= job.getLocation() %></span>
                    <span class="detail-item"><i class="fas fa-briefcase"></i> <%= job.getJobType() %></span>
                    <span class="detail-item"><i class="fas fa-coins"></i> <%= job.getSalaryRange() %></span>
                    <span class="detail-item"><i class="fas fa-user-graduate"></i> <%= job.getExperience() %></span>
                    <span class="detail-item"><i class="fas fa-calendar-alt"></i> <%= job.getDatePost() %></span> <%-- Format date if needed --%>
                    <%-- Hiển thị isAvailable nếu cần thiết--%>
                    <span class="detail-item"><i class="fas fa-check-circle"></i> <%= job.isAvailable() ? "Available" : "Not Available" %></span>
                </div>
               </div>
           <% } %>
        </div>
           <% } %>
          </br>
          </br>
         <h2>Random 3 Jobs</h2>
         <div class="job-list">
            <% for (Job job : randomJobs) { %>
           <div class="job-item">
             <div class="job-title">
                 <h3><a href="job-detail?id=<%= job.getId() %>"><%= job.getTitle() %></a></h3>
             </div>
             <div class="job-details">
                 <span class="detail-item"><i class="fas fa-map-marker-alt"></i> <%= job.getLocation() %></span>
                 <span class="detail-item"><i class="fas fa-briefcase"></i> <%= job.getJobType() %></span>
                 <span class="detail-item"><i class="fas fa-coins"></i> <%= job.getSalaryRange() %></span>
                 <span class="detail-item"><i class="fas fa-user-graduate"></i> <%= job.getExperience() %></span>
                 <span class="detail-item"><i class="fas fa-calendar-alt"></i> <%= job.getDatePost() %></span> <%-- Format date if needed --%>
                 <%-- Hiển thị isAvailable nếu cần thiết--%>
                 <span class="detail-item"><i class="fas fa-check-circle"></i> <%= job.isAvailable() ? "Available" : "Not Available" %></span>
             </div>
         </div>
         <% } %>         
	</div>

	<!-- Previous and Next Links -->
	<div class="pagination">
	    <!-- Previous Link -->
	    <a href="?page=<%= currentPage > 1 ? currentPage - 1 : 1 %>&jobName=<%= jobName != null ? URLEncoder.encode(jobName, "UTF-8") : "" %>&salaryRange=<%= salaryRangeId != null ? URLEncoder.encode(salaryRangeId, "UTF-8") : "" %>&jobType=<%= jobType != null ? URLEncoder.encode(jobType, "UTF-8") : "" %>&experience=<%= experience != null ? URLEncoder.encode(experience, "UTF-8") : "" %>&location=<%= location != null ? URLEncoder.encode(location, "UTF-8") : "" %>"
	       class="previous">
	        Previous
	    </a>
	
	    <!-- Page Display -->
	    <span><%= currentPage %> / <%= totalPages %></span>
	
	    <!-- Next Link -->
	    <a href="?page=<%= currentPage < totalPages ? currentPage + 1 : totalPages %>&jobName=<%= jobName != null ? URLEncoder.encode(jobName, "UTF-8") : "" %>&salaryRange=<%= salaryRangeId != null ? URLEncoder.encode(salaryRangeId, "UTF-8") : "" %>&jobType=<%= jobType != null ? URLEncoder.encode(jobType, "UTF-8") : "" %>&experience=<%= experience != null ? URLEncoder.encode(experience, "UTF-8") : "" %>&location=<%= location != null ? URLEncoder.encode(location, "UTF-8") : "" %>"
	       class="next">
	        Next
	    </a>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>
