<%@ page import="model.bean.Job" %>
<%@ page import="model.bo.JobBO" %>
<%@ page import="model.bean.Account" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
Account account = (Account) request.getSession().getAttribute("account");
if (account != null) {
	request.setAttribute("account", account);
}

String jobId = request.getParameter("id");
Job job = JobBO.getInstance().getJobById(jobId); // Cần triển khai phương thức getJobById trong JobBO và JobDAO

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
</head>
<body>
<%@include file="../includes/navbar.jsp"%>
<div class="container">
    <h2><%= job.getTitle() %></h2>
    <%-- Hiển thị chi tiết công việc ở đây --%>
    <p>Company: <%= job.getEmployerName()%></p> <%-- Thay bằng tên công ty --%>
    <p>Salary: <%= job.getSalaryRange() %></p>
    <p>Location: <%= job.getLocation() %></p>
    <p>Experience: <%= job.getExperience() %></p>
    <p>Job Type: <%= job.getJobType() %></p>
    <p>Description: <%= job.getDescription() %></p>
    <button id="applyButton">Apply Now</button>

    <div id="applyModal" class="modal">
        <%-- Form ứng tuyển --%>
    </div>
</div>
<%@include file="../includes/footer.jsp"%>
</body>
</html>