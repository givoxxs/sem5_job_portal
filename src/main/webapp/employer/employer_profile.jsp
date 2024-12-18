<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ page import="model.bean.Employer" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Employer profile</title>
    <link rel="stylesheet" href="assets/css/employer/form.css" />
    <%@include file="../includes/header.jsp"%>
  </head>
  
  <body>
  <%
	if(request.getAttribute("message") != null) {
			%>
	<script>
                alert("${message}");
                </script>
			<%
		}
	%>
	
    <% Employer emp = (Employer) request.getAttribute("employer"); 
    if (emp ==    null) { 
    	out.println("Employer not found!"); return; } 
    	%>
    <h1>Employer profile</h1>
    <form action="EmployerServlet?action=update" method="post">
      <label for="id">Employer ID:</label>
      <input
        type="text"
        id="id"
        name="id"
        value="<%= emp.getId() %>"
        readonly
      /><br /><br />

      <label for="name">Name:</label>
      <input
        type="text"
        id="name"
        name="name"
        value="<%= emp.getName() %>"
        required
      /><br /><br />

      <label for="email">Email:</label>
      <input
        type="email"
        id="email"
        name="email"
        value="<%= emp.getEmail() %>"
        required
      /><br /><br />

      <label for="address">Address:</label>
      <textarea id="address" name="address" required>
<%= emp.getAddress() %></textarea
      ><br /><br />

     <label for="link">Connect:</label>
<input
    type="hidden"
    id="link"
    name="link"
    value="<%= emp.getLink() %>"
    required
/>
<!-- Ô input để thêm link mới -->
<input
    type="text"
    id="newUrl"
    placeholder="Nhập URL mới"
/>
<button type="button" onclick="addURL()">Thêm URL</button>

<!-- Danh sách hiển thị các URL -->
<ul id="urlList" class="list-unstyled mt-3">
    <%
        // Tách chuỗi URL từ đối tượng `emp`
        String urls = emp.getLink();
        if (urls != null && !urls.isEmpty()) {
            String[] urlArray = urls.split(";");
            for (String url : urlArray) {
                if (!url.isEmpty()) {
    %>
                    <li class="d-flex justify-content-between align-items-center mb-3 p-3 border rounded shadow-sm">
                        <a href="<%= url %>" target="_blank" class="text-decoration-none text-primary fw-bold fs-6 w-75">
                            <%= url.length() > 30 ? url.substring(0, 30) + "..." : url %>
                        </a>
                        <button type="button" class="btn btn-danger btn-sm" onclick="removeURL(this, '<%= url %>')">
                            <i class="bi bi-x-circle"></i> Xóa
                        </button>
                    </li>
    <%
                }
            }
        }
    %>
</ul>


<br /><br />

      <label for="description">Description:</label>
      <input
        type="text"
        id="description"
        name="description"
        value="<%= emp.getDescription() %>"
        required
      /><br /><br />

      <input type="submit" value="Update" />
    </form>
    
    <%@include file="../includes/footer.jsp"%>
    
    <script>
    // Hàm thêm URL
function addURL() {
    const newUrlInput = document.getElementById("newUrl");
    const hiddenInput = document.getElementById("link");
    const urlList = document.getElementById("urlList");
    const newUrl = newUrlInput.value.trim();

    if (newUrl) {
        // Lấy danh sách URL hiện tại từ input ẩn
        const urlArray = hiddenInput.value ? hiddenInput.value.split(";") : [];

        // Kiểm tra URL đã tồn tại chưa
        if (!urlArray.includes(newUrl)) {
            // Thêm URL mới vào mảng
            urlArray.push(newUrl);
            hiddenInput.value = urlArray.join(";"); // Cập nhật input ẩn

            // Tạo phần tử mới cho danh sách
            const listItem = document.createElement("li");
            listItem.innerHTML = `
                <a href="${newUrl}" target="_blank">${newUrl}</a>
                <button type="button" onclick="removeURL(this, '${newUrl}')">Xóa</button>
            `;
            urlList.appendChild(listItem); // Thêm vào danh sách hiển thị

            // Cập nhật lại danh sách URL trong `hiddenInput`
            hiddenInput.value = urlArray.join(";");
        } else {
            alert("URL này đã tồn tại trong danh sách!");
        }

        // Xóa nội dung ô input sau khi thêm
        newUrlInput.value = "";
    } else {
        alert("Vui lòng nhập URL hợp lệ!");
    }
}


    // Hàm xóa URL
    function removeURL(button, url) {
        const hiddenInput = document.getElementById("link");
        const urlArray = hiddenInput.value.split(";");

        // Loại bỏ URL khỏi danh sách
        const updatedArray = urlArray.filter(item => item !== url);
        hiddenInput.value = updatedArray.join(";"); // Cập nhật input ẩn

        // Xóa phần tử HTML khỏi danh sách
        button.parentElement.remove();
    }
</script>


    
  </body>
</html>
