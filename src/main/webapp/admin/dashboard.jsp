<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@ page import="model.bean.Account" %>
<%@page import="dto.CandidateInAdmin" %>
<%@page import="dto.EmployerInAdmin" %>
<%
Account account = (Account) request.getSession().getAttribute("account");
if (account != null) {
	request.setAttribute("account", account);
} else {
	response.sendRedirect("/sem5_job_portal/login.jsp");
}
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bảng điều khiển admin</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/dashboard.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/pagination.css">
    <%@include file="../includes/header.jsp"%>
</head>

<body>
	<%@include file="../includes/navbar.jsp"%>
    <div class="dashboardContainer">
        <!-- Sidebar -->
        <aside class="sidebar">
            <ul>
                <li class="active"><a href="/sem5_job_portal/admin/list?mainContent=manageCandidates" id="manageCandidatesTab">Manage Candidates</a></li>
                <li><a href="/sem5_job_portal/admin/list?mainContent=manageEmployers" id="manageEmployersTab">Manage Employers</a></li>
            </ul>
        </aside>
        <!-- Main Content -->
        <div class="main-content">
        	<%
        		String mainContent = (String) request.getAttribute("mainContent");
        		if (mainContent != null) 
        		{
        			if(mainContent.equals("manageCandidates")){ 
        		
        	%>
			            <div id="manageCandidates" class="content-section active">
							<div class="utility">
						        <button class="add-btn" onclick="window.location.href='/sem5_job_portal/admin/create_candidate.jsp'">Add Candidate</button>
						        <div class="search-and-filter">
						            <div class="search-bar">
						                <input type="text" id="searchInput" placeholder="Tìm kiếm..." value=<% if (request.getParameter("searchText") != null) {%> <%= request.getParameter("searchText")%> <%} %>>
						                <button id="searchButton" class="search-btn" onclick="window.location.href='/sem5_job_portal/admin/search?role=candidate&searchText=' + document.getElementById('searchInput').value">
						                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 50 50">
						                        <path
						                            d="M 21 3 C 11.601563 3 4 10.601563 4 20 C 4 29.398438 11.601563 37 21 37 C 24.355469 37 27.460938 36.015625 30.09375 34.34375 L 42.375 46.625 L 46.625 42.375 L 34.5 30.28125 C 36.679688 27.421875 38 23.878906 38 20 C 38 10.601563 30.398438 3 21 3 Z M 21 7 C 28.199219 7 34 12.800781 34 20 C 34 27.199219 28.199219 33 21 33 C 13.800781 33 8 27.199219 8 20 C 8 12.800781 13.800781 7 21 7 Z">
						                        </path>
						                    </svg>
						                </button>
						            </div>
						        </div>
						    </div>
						
						    <table>
						        <thead>
						            <tr>
						                <th>ID</th>
						                <th>Name</th>
						                <th>Email</th>
						                <th>Job Application Count</th>
						                <th>Action</th>
						            </tr>
						        </thead>
						        <tbody>

						            <%
							            List<CandidateInAdmin> candidates = (List<CandidateInAdmin>)request.getAttribute("candidates");
							           	for (CandidateInAdmin candidate : candidates) {
							        %>
							        		<tr>
						        			  	<td><%= candidate.getId() %></td>
									            <td><%= candidate.getName() %></td>
									            <td><%= candidate.getEmail() %></td>
									            <td><%= candidate.getJobApplicationCount() %>
								                <td style="display: flex; gap: 10px;">
							                        <button class="view-btn" onclick="window.location.href='/sem5_job_portal/admin/view_candidate?candidateId=<%= candidate.getId()%>'">
							                            <i class="fas fa-eye"></i> <!-- Biểu tượng Xem -->
							                        </button>
							                        <button class="edit-btn" onclick="window.location.href='/sem5_job_portal/admin/update?role=candidate&id=<%= candidate.getId()%>'">
							                            <i class="fas fa-edit"></i> <!-- Biểu tượng Chỉnh sửa -->
							                        </button>
							                        <button class="delete-btn" 
													        onclick="if(confirm('Bạn có chắc chắn muốn xóa không?')) {
													                    window.location.href='/sem5_job_portal/admin/delete?role=candidate&id=<%= candidate.getId()%>';
													                 }">
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
	        <%		} else if (mainContent.equals("manageEmployers")){ %>
				            <div id="manageEmployers" class="content-section active">
								<div class="utility">
							        <button class="add-btn" onclick="window.location.href='/sem5_job_portal/admin/create_employer.jsp'">Add Employer</button>
							        <div class="search-and-filter">
							            <div class="search-bar">
							                <input type="text" id="searchInput" placeholder="Tìm kiếm..." value=<% if (request.getParameter("searchText") != null) {%> <%= request.getParameter("searchText")%> <%} %>>
							                <button id="searchButton" class="search-btn" onclick="window.location.href='/sem5_job_portal/admin/search?mainContent=manageEmployers&role=employer&searchText=' + document.getElementById('searchInput').value">
							                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 50 50">
							                        <path
							                            d="M 21 3 C 11.601563 3 4 10.601563 4 20 C 4 29.398438 11.601563 37 21 37 C 24.355469 37 27.460938 36.015625 30.09375 34.34375 L 42.375 46.625 L 46.625 42.375 L 34.5 30.28125 C 36.679688 27.421875 38 23.878906 38 20 C 38 10.601563 30.398438 3 21 3 Z M 21 7 C 28.199219 7 34 12.800781 34 20 C 34 27.199219 28.199219 33 21 33 C 13.800781 33 8 27.199219 8 20 C 8 12.800781 13.800781 7 21 7 Z">
							                        </path>
							                    </svg>
							                </button>
							            </div>
							        </div>
							    </div>
							
							    <table>
							        <thead>
							            <tr>
							                <th>ID</th>
							                <th>Name</th>
							                <th>Address</th>
							                <th>Email</th>
							                <th>Job Count</th>
							                <th>Action</th>
							            </tr>
							        </thead>
							        <tbody>
							            <%
							            List<EmployerInAdmin> employers = (List<EmployerInAdmin>)request.getAttribute("employers");
							           	for (EmployerInAdmin employer : employers) {
							        %>
							        		<tr>
						        			  	<td><%= employer.getId() %></td>
									            <td><%= employer.getName() %></td>
									            <td><%= employer.getAddress() %></td>
									            <td><%= employer.getEmail() %></td>
									            <td><%= employer.getJobCount() %></td>
								                <td style="display: flex; gap: 10px;">
 													<button class="view-btn" onclick="window.location.href='/sem5_job_portal/admin/view_employer?employerId=<%= employer.getId()%>'">
							                            <i class="fas fa-eye"></i> <!-- Biểu tượng Xem -->
							                        </button>
							                        <button class="edit-btn" onclick="window.location.href='/sem5_job_portal/admin/update?role=employer&id=<%= employer.getId()%>'">
							                            <i class="fas fa-edit"></i> <!-- Biểu tượng Chỉnh sửa -->
							                        </button>
							                        <button class="delete-btn" 
													        onclick="if(confirm('Bạn có chắc chắn muốn xóa không?')) {
													                    window.location.href='/sem5_job_portal/admin/delete?role=employer&id=<%= employer.getId()%>';
													                 }">
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
		    
		    boolean isSearch = (request.getAttribute("isSearch") != null) ? (boolean) request.getAttribute("isSearch") : false; 
		%>
		<div id="pagination-container" data-is-search="<%= isSearch %>" data-main-content="<%= mainContent %>">
		    <div class="pagination">
		        <% if (currentPage > 1) { %>
		            <a href="#" data-page="<%= currentPage - 1 %>" class="pagination-link">« Previous</a>
		        <% } %>
		    
		        <% if (startPage > 1) { %>
		            <a href="#" data-page="1" class="pagination-link">1</a>
		            <% if (startPage > 2) { %> <span>...</span> <% } %>
		        <% } %>
		    
		        <% for (int i = startPage; i <= endPage; i++) { %>
		            <a href="#" data-page="<%= i %>" class="pagination-link <%= i == currentPage ? "active" : "" %>"><%= i %></a>
		        <% } %>
		    
		        <% if (endPage < totalPages) { %>
		            <a href="#" data-page="<%= totalPages %>" class="pagination-link"><%= totalPages %></a>
		            <% if (endPage < totalPages - 1) { %> <span>...</span> <% } %>
		        <% } %>
		    
		        <% if (currentPage < totalPages) { %>
		            <a href="#" data-page="<%= currentPage + 1 %>" class="pagination-link">Next »</a>
		        <% } %>
	    </div>
	</div>
		
    </div>
	<%@include file="../includes/footer.jsp"%>
    <script src="${pageContext.request.contextPath}/assets/js/admin/dashboard.js"></script>
</body>

</html>