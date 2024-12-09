<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký Tài Khoản</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/register.css">
</head>

<body>
    <div class="register-container">
        <h1>Đăng Ký Tài Khoản</h1>
        <form class="register-form" action="${pageContext.request.contextPath}/auth/register" method="post">
            <div class="role">
                <label class="role-option">
                    <input type="radio" name="role" value="employer" checked> Nhà tuyển dụng
                </label>
                <label class="role-option">
                    <input type="radio" name="role" value="candidate"> Ứng viên
                </label>
            </div>
            <div class="form-group">
                <label for="username">Tên đăng nhập:</label>
                <input type="text" id="username" name="username" placeholder="Nhập tên đăng nhập" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" placeholder="Nhập mật khẩu" required>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Xác nhận mật khẩu:</label>
                <input type="password" id="confirm_password" name="confirm_password" placeholder="Nhập lại mật khẩu"
                    required>
            </div>
            <div class="error" id="register-error">
                <% 
			        String error = (String) request.getAttribute("error");
			        if (error != null) { 
			    %>
			        <%= error %>
			    <% 
			        } 
			    %>
            </div>
            <button type="submit" class="register-btn">Đăng Ký</button>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/assets/js/register.js"></script>
</body>

</html>
