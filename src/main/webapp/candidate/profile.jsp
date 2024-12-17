<%@ page import="model.bean.Account" %>
<%@ page import="model.bean.Candidate" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Account account = (Account) request.getSession().getAttribute("account");
    Candidate candidate = (Candidate) request.getAttribute("candidate");

    if (account == null || candidate == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <%@ include file="../includes/header.jsp" %>
    <style>
        .avatar img {
            max-width: 150px;
            border-radius: 50%;
        }

        .form-control[readonly] {
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
    <%@ include file="../includes/navbar.jsp" %>
    <div class="container mt-4">
        <h2 class="text-center">Profile Information</h2>

        <!-- Success or Error Messages -->
        <% String success = (String) request.getAttribute("success"); %>
        <% String error = (String) request.getAttribute("error"); %>
        <% if (success != null) { %>
            <div class="alert alert-success"><%= success %></div>
        <% } else if (error != null) { %>
            <div class="alert alert-danger"><%= error %></div>
        <% } %>

        <!-- Profile Form -->
        <form action="profile-candidate" method="post" enctype="multipart/form-data">
            <!-- Avatar Upload -->
            <div class="text-center mb-2 avatar">
                <img id="avatarPreview" src="<%= account.getAvatarUrl() != null ? account.getAvatarUrl() : "https://www.shutterstock.com/image-illustration/man-avatar-icon-flat-illustration-260nw-791541205.jpg" %>" alt="Avatar">
                <div id="uploadSection" style="display: none;">
                    <input type="file" id="avatarInput" name="avatar" accept="image/*" onchange="previewAvatar(event)">
                </div>
            </div>

            <!-- Profile Information -->
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" value="<%= account.getUsername() %>" readonly>
            </div>
            <div class="form-group">
                <label for="fullname">Full Name</label>
                <input type="text" class="form-control" id="fullname" name="fullname" value="<%= candidate.getName() %>" readonly>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="<%= candidate.getEmail() %>" readonly>
            </div>
            <div class="form-group">
                <label for="cvUrl">CV URL</label>
                <input type="text" class="form-control" id="cvUrl" name="cvUrl" value="<%= candidate.getCvUrl() %>" readonly>
                <button type="button" class="btn btn-info mt-2"
					onclick="window.open('<%= candidate.getCvUrl() %>', '_blank')">
					View CV
				</button>
            </div>

            <!-- Buttons -->
            <div class="text-center">
                <button type="button" class="btn btn-secondary" onclick="toggleEdit()">Edit</button>
                <button type="submit" class="btn btn-success" id="saveButton" style="display: none;">Save</button>
            </div>
        </form>
    </div>

		<script>
        function toggleEdit() {
            const formInputs = document.querySelectorAll('.form-control');
            const saveButton = document.getElementById('saveButton');
            const uploadSection = document.getElementById('uploadSection');
            const isReadonly = formInputs[0].hasAttribute('readonly');

            formInputs.forEach(input => {
                if (isReadonly) input.removeAttribute('readonly');
                else input.setAttribute('readonly', 'true');
            });

            saveButton.style.display = isReadonly ? 'inline-block' : 'none';
            uploadSection.style.display = isReadonly ? 'block' : 'none';
        }

        function previewAvatar(event) {
            const output = document.getElementById('avatarPreview');
            output.src = URL.createObjectURL(event.target.files[0]);
            output.onload = () => URL.revokeObjectURL(output.src);
        }
    </script>
    <%@ include file="../includes/footer.jsp" %>
</body>
</html>
