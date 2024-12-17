<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="model.bean.Account" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bảng điều khiển admin</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/pagination.css">
</head>

<body>
    <div class="container">
        <!-- Sidebar -->
        <aside class="sidebar">
            <ul>
                <li class="active"><a href="#" id="manageUsersTab">Quản lý người dùng</a></li>
                <li><a href="#" id="manageJobsTab">Quản lý công việc</a></li>
            </ul>
        </aside>
        <!-- Main Content -->
        <div class="main-content">
        	<%
        		String mainContent = (String) request.getAttribute("mainContent");
        		if (mainContent != null) 
        		{
        			if(mainContent.equals("manageUsers")){ 
        		
        	%>
			            <!-- Quản lý người dùng -->
			            <div id="manageUsers" class="content-section active">
							<div class="utility">
						        <button class="add-btn" onclick="window.location.href='create_user.html'">Thêm người dùng</button>
						        <div class="search-and-filter">
						            <div class="search-bar">
						                <input type="text" id="searchInput" placeholder="Tìm kiếm...">
						                <button id="searchButton" class="search-btn">
						                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 50 50">
						                        <path
						                            d="M 21 3 C 11.601563 3 4 10.601563 4 20 C 4 29.398438 11.601563 37 21 37 C 24.355469 37 27.460938 36.015625 30.09375 34.34375 L 42.375 46.625 L 46.625 42.375 L 34.5 30.28125 C 36.679688 27.421875 38 23.878906 38 20 C 38 10.601563 30.398438 3 21 3 Z M 21 7 C 28.199219 7 34 12.800781 34 20 C 34 27.199219 28.199219 33 21 33 C 13.800781 33 8 27.199219 8 20 C 8 12.800781 13.800781 7 21 7 Z">
						                        </path>
						                    </svg>
						                </button>
						            </div>
						
						            <div class="filter-search">
						                <select id="filter" name="filter">
						                    <option value="all">Tất cả</option>
											
						                </select>
						            </div>
						        </div>
						    </div>
						
						    <table>
						        <thead>
						            <tr>
						                <th>ID</th>
						                <th>Username</th>
						                <th>Role</th>
						                <th>Hành động</th>
						            </tr>
						        </thead>
						        <tbody>
						            <!-- Thêm các bản ghi user-->
						            <%
							            List<Account> users = (List<Account>)request.getAttribute("users");
							           	for (Account user : users) {
							        %>
							        		<tr>
						        			  	<td><%= user.getId() %></td>
									            <td><%= user.getUsername() %></td>
									            <td><%= user.getRole() %></td>
								                <td>
							                        <button class="view-btn">
							                            <i class="fas fa-eye"></i> <!-- Biểu tượng Xem -->
							                        </button>
							                        <button class="edit-btn">
							                            <i class="fas fa-edit"></i> <!-- Biểu tượng Chỉnh sửa -->
							                        </button>
							                        <button class="delete-btn">
							                            <i class="fas fa-trash"></i> <!-- Biểu tượng Xoá -->
							                        </button>
							                    </td>
						                    </tr>
							        <%
							           	}
						            %>
						        </tbody>
						    </table>
			            </div>
	        <%		} else if (mainContent.equals("manageJobs")){ %>
			            <!-- Quản lý phương pháp học -->
				            <div id="manageJobs" class="content-section active">
								<div class="utility">
							        <button class="add-btn" onclick="window.location.href='create_job.html'">Thêm công việc mới</button>
							        <div class="search-and-filter">
							            <div class="search-bar">
							                <input type="text" id="searchInput" placeholder="Tìm kiếm...">
							                <button id="searchButton" class="search-btn">
							                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 50 50">
							                        <path
							                            d="M 21 3 C 11.601563 3 4 10.601563 4 20 C 4 29.398438 11.601563 37 21 37 C 24.355469 37 27.460938 36.015625 30.09375 34.34375 L 42.375 46.625 L 46.625 42.375 L 34.5 30.28125 C 36.679688 27.421875 38 23.878906 38 20 C 38 10.601563 30.398438 3 21 3 Z M 21 7 C 28.199219 7 34 12.800781 34 20 C 34 27.199219 28.199219 33 21 33 C 13.800781 33 8 27.199219 8 20 C 8 12.800781 13.800781 7 21 7 Z">
							                        </path>
							                    </svg>
							                </button>
							            </div>
							
							            <div class="filter-search">
							                <select id="filter" name="filter">
							                    <option value="all">Tất cả</option>
							
							                </select>
							            </div>
							        </div>
							    </div>
							
							    <table>
							        <thead>
							            <tr>
							                <th>ID</th>
							                <th>Tiêu đề</th>
							                <th>Lương</th>
							                <th>Địa điểm</th>
							                <th>Loại</th>
							                <th>Ngày đăng</th>
							                <th>Hành động</th>
							            </tr>
							        </thead>
							        <tbody>
							            <!-- Thêm các bản ghi job-->
							        </tbody>
							    </table>
				            </div>
           	<%
           			} 
            	}
            %>
        </div>
       
		<% 
		    int currentPage = (int) request.getAttribute("currentPage");
		    int totalPages = (int) request.getAttribute("totalPages");
		    int pageRange = 2;  // Hiển thị tối đa 5 trang (trang hiện tại + 2 trước, 2 sau)
		    int startPage = Math.max(1, currentPage - pageRange);
		    int endPage = Math.min(totalPages, currentPage + pageRange);
		%>
		<div class="pagination">
		    <% if (currentPage > 1) { %>
		        <a href="/sem5_job_portal/admin/list?mainContent=<%= mainContent %>&page=<%= currentPage - 1 %>">« Trước</a>
		    <% } %>
		
		    <% if (startPage > 1) { %>
		        <a href="/sem5_job_portal/admin/list?mainContent=<%= mainContent %>&page=1">1</a>
		        <% if (startPage > 2) { %> <span>...</span> <% } %>
		    <% } %>
		
		    <% for (int i = startPage; i <= endPage; i++) { %>
		        <a href="/sem5_job_portal/admin/list?mainContent=<%= mainContent %>&page=<%= i %>" class="<%= i == currentPage ? "active" : "" %>"><%= i %></a>
		    <% } %>
		
		    <% if (endPage < totalPages) { %>
		        <a href="/sem5_job_portal/admin/list?mainContent=<%= mainContent %>&page=<%= totalPages %>"><%= totalPages %></a>
		        <% if (endPage < totalPages - 1) { %> <span>...</span> <% } %>
		    <% } %>
		
		    <% if (currentPage < totalPages) { %>
		        <a href="/sem5_job_portal/admin/list?mainContent=<%= mainContent %>&page=<%= currentPage + 1 %>">Tiếp »</a>
		    <% } %>
		</div>
    </div>

    <script src="${pageContext.request.contextPath}/assets/js/admin/dashboard.js"></script>
</body>

</html>