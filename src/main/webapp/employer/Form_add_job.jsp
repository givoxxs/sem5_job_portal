	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Job</title>
    <link rel="stylesheet" href="assets/css/employer/form.css" />
</head>
<body>
    <h1>Add Job</h1>
    <form action="JobServlet?action=addjob" method="post">
      
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required><br><br>

		<label for="salaryRangeId">Salary Range:</label>
		<select id="salaryRangeId" name="salaryRangeId" required>
		    <option value="1">Dưới 5 triệu</option>
		    <option value="2">Từ 5 - 10 triệu</option>
		    <option value="3">Từ 10 - 15 triệu</option>
		    <option value="4">Từ 15 - 20 triệu</option>
		    <option value="5">Từ 20 - 30 triệu</option>
		    <option value="6">Từ 30 - 40 triệu</option>
		    <option value="7">Trên 40 triệu</option>
		</select><br><br>

        <label for="location">Location:</label>
        <input type="text" id="location" name="location" required><br><br>
        
		<label for="jobType">Job Type:</label>
		<select id="jobType" name="jobType" required>
		    <option value="full-time">Full-time</option>
		    <option value="part-time">Part-time</option>
		    <option value="internship">Internship</option>
		</select><br><br>

		<label for="experience">Experience:</label>
		<select id="experience" name="experience" required>
		    <option value="Intern">Intern</option>
		    <option value="Fresher">Fresher</option>
		    <option value="Junior">Junior</option>
		    <option value="Middle">Middle</option>
		    <option value="Senior">Senior</option>
		    <option value="Lead">Lead</option>
		</select><br><br>

		<label for="description">Description:</label>
        <textarea id="description" name="description" required></textarea><br><br>
        
        <input type="submit" value="Add Job">
    </form>
</body>
</html>
