<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Employer</title>
	<link rel="stylesheet" href="assets/css/employer/t3.css" />
    <link rel="stylesheet" href="assets/css/employer/t2.css" />
</head>
<body>
	<% 
	String employer_id = "EMP01";
	session.setAttribute("employer_id", employer_id);
	%>
    <div class="contentt3">
      <div class="t3-center">
        <div class="center-menu">
          <div class="link">
            <div class="tittle">Manager</div>
            <div class="sub-tittle">
              <a href="EmployerServlet?action=showprofile" target="view"
                >Employer profile</a
              >
              <a href="JobServlet?action=showjob" target="view">My job</a>

              <a href="JobServlet?action=formaddjob" target="view"
                >Add job</a
              >
            </div>
          </div>
        </div>
        <div class="center-view">
          <div class="fview">
            <iframe
              name="view"
              src="JobServlet?action=showjob"
              width="100%"
              height="800px"
              style="border: 1px;"
            ></iframe>

          </div>
        </div>
      </div>
    </div>
</body>
</html>