<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.bean.Job" %>
<%
    Job job = (Job) request.getAttribute("job");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Job detail</title>
    <link rel="stylesheet" href="assets/css/employer/form.css" />
</head>
<body>
    <h1>Job detail</h1>
    <form action="JobServlet?action=update" method="post">
    
        <input type="hidden" name="jobid" value="<%= job.getId() %>">
        
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" value="<%= job.getTitle() %>" required><br><br>

        <label for="salaryRangeId">Salary Range:</label>
        <select id="salaryRangeId" name="salaryRangeId" required>
            <option value="1" <%= "1".equals(job.getSalaryRange()) ? "selected" : "" %>>Dưới 5 triệu</option>
			<option value="2" <%= "2".equals(job.getSalaryRange()) ? "selected" : "" %>>Từ 5 - 10 triệu</option>
			<option value="3" <%= "3".equals(job.getSalaryRange()) ? "selected" : "" %>>Từ 10 - 15 triệu</option>
			<option value="4" <%= "4".equals(job.getSalaryRange()) ? "selected" : "" %>>Từ 15 - 20 triệu</option>
			<option value="5" <%= "5".equals(job.getSalaryRange()) ? "selected" : "" %>>Từ 20 - 30 triệu</option>
			<option value="6" <%= "6".equals(job.getSalaryRange()) ? "selected" : "" %>>Từ 30 - 40 triệu</option>
			<option value="7" <%= "7".equals(job.getSalaryRange()) ? "selected" : "" %>>Trên 40 triệu</option>

        </select><br><br>

        <label for="location">Location:</label>
        <input type="text" id="location" name="location" value="<%= job.getLocation() %>" required><br><br>
        
        <label for="jobType">Job Type:</label>
        <select id="jobType" name="jobType" required>
            <option value="full-time" <%= "full-time".equals(job.getJobType()) ? "selected" : "" %>>Full-time</option>
            <option value="part-time" <%= "part-time".equals(job.getJobType()) ? "selected" : "" %>>Part-time</option>
            <option value="internship" <%= "internship".equals(job.getJobType()) ? "selected" : "" %>>Internship</option>
        </select><br><br>

        <label for="experience">Experience:</label>
        <select id="experience" name="experience" required>
            <option value="Intern" <%= "Intern".equals(job.getExperience()) ? "selected" : "" %>>Intern</option>
            <option value="Fresher" <%= "Fresher".equals(job.getExperience()) ? "selected" : "" %>>Fresher</option>
            <option value="Junior" <%= "Junior".equals(job.getExperience()) ? "selected" : "" %>>Junior</option>
            <option value="Middle" <%= "Middle".equals(job.getExperience()) ? "selected" : "" %>>Middle</option>
            <option value="Senior" <%= "Senior".equals(job.getExperience()) ? "selected" : "" %>>Senior</option>
            <option value="Lead" <%= "Lead".equals(job.getExperience()) ? "selected" : "" %>>Lead</option>
        </select><br><br>

        <label for="description">Description:</label>
        <textarea id="description" name="description" required><%= job.getDescription() %></textarea><br><br>
        
        <input type="submit" value="Update Job">
        <!-- Ngừng tuyển -->
        
    </form>
</body>
</html>
