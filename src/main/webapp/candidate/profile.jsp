<%@ page import="model.bean.Account" %>
<%@ page import="model.bean.Candidate" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Account account = (Account) request.getSession().getAttribute("account");
    Candidate candidate = (Candidate) request.getAttribute("candidate");
    if (account != null && candidate != null) {
        request.setAttribute("account", account);
        request.setAttribute("candidate", candidate);
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <%@ include file="../includes/header.jsp" %>
    <link rel="stylesheet" href="assets/css/candidate/profile.css">
    <link rel="stylesheet" href="assets/css/navbar.css">
    <style>
        .profile-container {
            margin: 20px;
            max-width: 800px;
            background-color: white;
            padding: 20px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .avatar {
            text-align: center;
            margin-bottom: 20px;
        }

        .avatar img {
            max-width: 150px;
            border-radius: 50%;
            margin-bottom: 10px;
        }

        .profile-container form div {
            margin-bottom: 15px;
        }

        .profile-container input[readonly] {
            background-color: #f0f0f0;
        }

        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        footer {
            text-align: center;
            background-color: #333;
            color: white;
            padding: 10px;
            position: absolute;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <%@ include file="../includes/navbar.jsp" %>

    <div class="container mt-4">
        <h2 class="text-center">Profile Information</h2>

        <div class="profile-container">
            <div class="profile-header">
                <!-- Display user's avatar -->
                <div class="avatar">
                    <img src="<%= account.getAvatarUrl() != null ? account.getAvatarUrl() : "https://www.shutterstock.com/image-illustration/man-avatar-icon-flat-illustration-260nw-791541205.jpg" %>" alt="Avatar" class="profile-avatar">
                    <button class="btn btn-success">Edit Avatar</button>
                </div>

                <!-- Edit Profile Form -->
                <form action="profile-candidate" method="post">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="username" value="<%= account.getUsername() %>" readonly>
                    </div>
                    <div class="form-group">
                        <label for="fullname">Full Name</label>
                        <input type="text" class="form-control" id="fullname" name="fullname" value="<%= candidate.getName() %>">
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" value="<%= candidate.getEmail() %>">
                    </div>
                    <div class="form-group">
                        <label for="cv-url">CV URL</label>
                        <input type="text" class="form-control" id="cv-url" name="cvUrl" value="<%= candidate.getCvUrl() %>">
                        <button type="button" class="btn btn-primary mt-2" onclick="window.open('<%= candidate.getCvUrl() %>', '_blank')">View CV</button>
                    </div>

                    <!-- Save and Cancel buttons -->
                    <button type="submit" class="btn btn-success">Save</button>
                    <button type="button" class="btn btn-secondary" onclick="toggleEditForm()">Edit</button>
                </form>
            </div>
        </div>
    </div>

    <footer>
        <p>&copy; 2024 Employment Services. All rights reserved.</p>
    </footer>

    <script>
        function toggleEditForm() {
            const inputs = document.querySelectorAll('.form-control');
            const isReadonly = inputs[0].hasAttribute('readonly');
            
            inputs.forEach(input => {
                if (isReadonly) {
                    input.removeAttribute('readonly');
                } else {
                    input.setAttribute('readonly', 'true');
                }
            });
        }
    </script>

    <%@ include file="../includes/footer.jsp" %>
</body>
</html>
