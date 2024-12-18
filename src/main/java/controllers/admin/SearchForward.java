package controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Candidate;
import model.bean.Employer;
import model.bo.CandidateBO;
import model.bo.EmployerBO;

@WebServlet("/admin/search")
public class SearchForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CandidateBO candidateBO = CandidateBO.getInstance();
	private static EmployerBO employerBO = EmployerBO.getInstance();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	@Override 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			// Xác định cho trang jsp là đang search
			request.setAttribute("isSearch", true);
			
			String mainContent = (String) (request.getParameter("mainContent") != null ? request.getParameter("mainContent") : "manageCandidates");
			int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
			int recordsPerPage = 5;
			String searchText = (String) request.getParameter("searchText");
			if (searchText == null || searchText.equals("") ) {
				response.sendRedirect("/sem5_job_portal/admin/list");
			}
			
			if (mainContent.equals("manageCandidates")) {
				List<Candidate> candidates = candidateBO.searchCandidates(page, recordsPerPage, searchText);
				int totalPages = candidateBO.getSearchTotalPages(recordsPerPage, searchText);
				
				request.setAttribute("candidates", candidates);
				request.setAttribute("mainContent", mainContent);
				request.setAttribute("currentPage", page);
				request.setAttribute("totalPages", totalPages);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/dashboard.jsp");
				dispatcher.forward(request, response);
			}
			else if (mainContent.equals("manageEmployers")) {
				List<Employer> employers = employerBO.searchEmployers(page, recordsPerPage, searchText);
				int totalPages = employerBO.getSearchTotalPages(recordsPerPage, searchText);

				request.setAttribute("employers", employers);
				request.setAttribute("mainContent", mainContent);
				request.setAttribute("currentPage", page);
				request.setAttribute("totalPages", totalPages);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/dashboard.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
