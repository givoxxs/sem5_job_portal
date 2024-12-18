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
    <link rel="stylesheet" href="assets/css/candidate/job-detail.css"> <!-- Đảm bảo CSS được tải -->
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
    <% 
    	if (account != null) {
    %>
    	<button id="applyButton">Apply Now</button>
	<%
    } else {
    %>
    	<button id="" onclick="location.href='${pageContext.request.contextPath}/login.jsp'">Apply Now</button>
  	<%      
		}
    %>
	
	<%
	if (candidate != null) {
		%>
    <!-- Modal for Apply Now -->
    <div id="applyModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <!-- Form ứng tuyển có thể thêm vào đây -->
            <h3>Apply for this Job</h3>
            <form action="${pageContext.request.contextPath}/apply-job-candidate" method="post">
                <!-- Form fields như Name, Email, Resume -->
                <input type="hidden" name="job_id" value="<%= job.getId() %>">
                <input type="hidden" name="candidate_id" value="<%= candidate.getId() %>">
                
                <label for="name">Your Name</label>
                <input type="text" name="name" placeholder="Your Name" value="<%= candidate.getName() %>"
                	 required><br><br> 
                	 
                 <label for="email">Your Email</label>
               	<input type="email" name="email" placeholder="Your Email" value="<%=candidate.getEmail()%>" 
               		required><br> <br>
               		
            	<label for="phone">Your Phone</label>
                <input type="text" name="phone" id="phone" required><br><br>
                
                <label for="cv">Your CV</label>
                <input type="text" name="cv_url" id="cv" value="<%= candidate.getCvUrl() %>" 
                	required><br><br>
                	
                <button type="submit" class="btn-submit">Submit</button>
            </form>
        </div>
    </div>
    <%
	}
    %>
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
