<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm thông tin tài khoản</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/register_employer.css">
</head>

<body>
    <div class="employer-container">
        <h1>Thêm thông tin tài khoản</h1>
        <form class="employer-form"  action="${pageContext.request.contextPath}/employer/create/profile" method="post">
             	<!-- Trường ẩn chưa accountId -->
	        <input type="hidden" name="accountId" value=${accountId }>
	        
            <div class="form-group">
                <label for="name">Họ tên:</label>
                <input type="text" id="name" name="name" placeholder="Nhập họ tên" required>
            </div>
			<div class="form-group">
                <label for="address">Địa chỉ:</label>
                <input type="text" id="address" name="address" placeholder="Nhập địa chỉ" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" placeholder="Nhập email" required>
            </div>
            <div class="form-group"> 
                <label for="urlInput">Liên kết:</label>
                <input type="url" id="urlInput" name="urlInput" placeholder="Nhập một liên kết"> 
                <input type="hidden" id="link" name="link">
                <button onclick="addLink()">Thêm</button>
                <label for="linkList">Danh sách liên kết:</label>
				<ul id="linkList"></ul>	
            </div>
			<div class="form-group">
                <label for="description">Mô tả:</label>
                <textarea id="description" name="description" placeholder="Nhập mô tả" required> </textarea>
            </div>
            <div class="error" id="employer-error">
                <% 
			        String error = (String) request.getAttribute("error");
			        if (error != null) { 
			    %>
			        <%= error %>
			    <% 
			        } 
			    %>
            </div>
            <button type="submit" class="employer-btn">Thêm</button>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/assets/js/register_employer.js"></script>
</body>

</html>