<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Account" %>
<%@ page import="model.bean.Job_Application" %>
<%
// Lấy dữ liệu từ request do Servlet truyền sang
    Account account =(Account) request.getSession().getAttribute("account"); 
	if (account != null) {
		request.setAttribute("account", account);
	}
	List<Job_Application> jobs = (List<Job_Application>) request.getAttribute("jobs_apply");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách công việc đã ứng tuyển</title>
    <!-- Link CSS -->
    <link rel="stylesheet" href="assets/css/view_apply_jobs.css">
    <link rel="stylesheet" href="assets/css/navbar.css">
    <link rel="stylesheet" href="assets/css/index.css">
    <link rel="stylesheet" href="assets/css/view_apply_jobs.css">
</head>
<style>
.no-jobs {
	text-align: center;
	color: #333;
	font-size: 1.5rem;
	margin-top: 20px;
}

//
css cho bảng
.job-table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

.job-table th {
	background-color: #f2f2f2;
}

.job-table th, .job-table td {
	border: 1px solid #ddd;
	padding: 8px;
}

.job-table th {
	text-align: left;
}

.job-table tr:nth-child(even) {
	background-color: #f2f2f2;
}

.job-table tr:hover {
	background-color: #f1f1f1;
}

.cv-link {
	color: #007bff;
	text-decoration: none;
}

.cv-link:hover {
	text-decoration: underline;
}
</style>
<body>
    <!-- Navbar -->
    <%@include file="../includes/navbar.jsp"%>

    <!-- Main content -->
    <div class="container">
        <h1 class="title">Danh sách công việc đã ứng tuyển</h1>
        
        <%
        	if (jobs.size() == 0) {
        %>
            <p class="no-jobs">Bạn chưa ứng tuyển công việc nào!</p>
        <%
            } else {
           %>
            <table class="job-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên</th>
                        <th>Email</th>
                        <th>Số điện thoại</th>
                        <th>Trạng thái</th>
                        <th>Link CV</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (jobs != null) {
                        for (Job_Application job : jobs) {
                    %>
                    <tr>
                        <td><%= job.getId() %></td>
                        <td><%= job.getName() %></td>
                        <td><%= job.getEmail() %></td>
                        <td><%= job.getPhone() %></td>
                        <td><%= job.getStatus() %></td>
                        <td>
                            <a href="<%= job.getCv_url() %>" target="_blank" class="cv-link">Xem CV</a>
                        </td>
                    </tr>
                    <%
                        }}
                    %>
                </tbody>
            </table>
		<%
            }
        %>
    </div>
</body>
</html>
