<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Account" %>
<%@ page import="model.bean.Job_Application" %>
<%
    // Lấy dữ liệu từ request do Servlet truyền sang
    Account account = (Account) request.getSession().getAttribute("account"); 
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
    <!-- Link CSS từ Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <%@include file="../includes/header.jsp"%>
</head>
<body>
    <!-- Navbar -->
    <%@include file="../includes/navbar.jsp"%>

    <!-- Main content -->
    <div class="container mt-5">
        <h1 class="text-center mb-4">Danh sách công việc đã ứng tuyển</h1>

        <% if (jobs.size() == 0) { %>
            <div class="alert alert-info text-center" role="alert">
                Bạn chưa ứng tuyển công việc nào!
            </div>
        <% } else { %>
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <thead class="table-light">
                        <tr>
                            <th scope="col">Your Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Phone Number</th>
                            <th scope="col">Status</th>
                            <th scope="col">Your CV</th>
                            <th scope="col">Detail Job</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (jobs != null) {
                            for (Job_Application job : jobs) { %>
                                <tr>
                                    <td><%= job.getName() %></td>
                                    <td><%= job.getEmail() %></td>
                                    <td><%= job.getPhone() %></td>
                                    <td><%= job.getStatus() %></td>
                                    <td>
                                        <a href="<%= job.getCv_url() %>" target="_blank" class="text-primary text-decoration-none">Xem CV</a>
                                    </td>
                                    <td>
                                        <a href="/sem5_job_portal/admin/job-detail?candidateId=<%= (String) request.getParameter("candidateId")%>&id=<%= job.getJob_id() %>" class="btn btn-sm btn-primary">Xem</a>
                                    </td>
                                </tr>
                            <% } 
                        } %>
                    </tbody>
                </table>
            </div>
        <% } %>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>