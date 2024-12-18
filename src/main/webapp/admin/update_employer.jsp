<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit employer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/register_employer.css">
</head>

<body>
    <div class="employer-container">
        <h1>Edit Employer</h1>
        <form class="employer-form"  action="${pageContext.request.contextPath}/admin/update-employer" method="post">
        	<input type="hidden" id="employerId" name="employerId" value='${employer.id }' >
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Input username" value='${employerAccount.username }' readonly>
            </div>
            <div class="form-group">
                <label for="password">New Password:</label>
                <input type="password" id="password" name="password" placeholder="Input new password if you want to change">
            </div>
	        
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" placeholder="Input name" value='${employer.name }' required>
            </div>
			<div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" placeholder="Input address" value='${employer.address }' required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" placeholder="Input email" value='${employer.email }' required>
            </div>
            <div class="form-group"> 
                <label for="urlInput">Link:</label>
                <input type="url" id="urlInput" name="urlInput" placeholder="Input link"> 
                <input type="hidden" id="link" name="link" value='${employer.link }'>
                <button type="button" onclick="addLink()">Add</button>
                <label for="linkList">Link list:</label>
				<ul id="linkList"></ul>	
            </div>
			<div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" placeholder="Input description" required>${employer.description } </textarea>
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
            <button type="submit" class="employer-btn">Add</button>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/assets/js/register_employer.js"></script>
    <script>
	    document.addEventListener('DOMContentLoaded', function() {
	    	// Lấy giá trị của trường input "link" chứa các link nối nhau bằng dấu ";"
	        const links = document.getElementById('link').value;

	        // Tách các link theo dấu ";"
	        const linkArray = links.split(';');

	        // Duyệt qua từng link và thêm vào danh sách
	        linkArray.forEach(link => {
	            const trimmedLink = link.trim();  // Loại bỏ khoảng trắng thừa

	            // Kiểm tra nếu link hợp lệ và không rỗng
	            if (trimmedLink) {
	            	// Tạo một phần tử li mới
	                var li = document.createElement('li');
	                li.classList.add('link-item');
	                
	                // Tạo văn bản chứa liên kết
	                var link = document.createElement('a');
	                link.href = trimmedLink;
	                link.textContent = trimmedLink;
	                link.target = "_blank"; // Mở liên kết trong tab mới

	                // Tạo nút xóa
	                var deleteButton = document.createElement('button');
	                deleteButton.textContent = "Delete";
	                deleteButton.onclick = function() {
	                    removeLink(trimmedLink, li); // Gọi hàm xoá link và phần tử li
	                };

	                // Thêm link và nút xóa vào li
	                li.appendChild(link);
	                li.appendChild(deleteButton);

	                // Thêm li vào danh sách
	                document.getElementById('linkList').appendChild(li);
	            }
	        });
	    });
    </script>
</body>

</html>