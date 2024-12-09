<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Employer</title>
	<link rel="stylesheet" href="../assets/css/employer/t3.css" />
    <link rel="stylesheet" href="../assets/css/employer/t2.css" />
</head>
<body>
	<body>
    <div class="contentt3">
      <div class="t3-center">
        <div class="center-menu">
          <div class="link">
            <div class="tittle">Manager</div>
            <div class="sub-tittle">
              <a href="employer_profile" target="view"
                >Employer profile</a
              >
              <a href="JobServlet?action=showjob" target="view">My job</a>

              <a href="Form_add_job" target="view"
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
              height="500px"
              style="border: 1px;"
            ></iframe>

          </div>
        </div>
      </div>
    </div>
  </body>
</body>
</html>