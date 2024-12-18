package controllers.admin;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CandidateInAdmin;
import dto.EmployerInAdmin;
import model.bo.CandidateBO;
import model.bo.EmployerBO;

@WebServlet("/admin/list")
public class ManageListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static CandidateBO candidateBO = CandidateBO.getInstance();
	private static EmployerBO employerBO = EmployerBO.getInstance();
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{	
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String mainContent = (String) (request.getParameter("mainContent") != null ? request.getParameter("mainContent") : "manageCandidates");
			int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
			int recordsPerPage = 5;
			if (mainContent.equals("manageCandidates")) {
				List<CandidateInAdmin> candidates = candidateBO.getCandidates(page, recordsPerPage);
				int totalPages = candidateBO.getTotalPages(recordsPerPage);
				
				request.setAttribute("candidates", candidates);
				request.setAttribute("mainContent", mainContent);
				request.setAttribute("currentPage", page);
				request.setAttribute("totalPages", totalPages);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/dashboard.jsp");
				dispatcher.forward(request, response);
			}
			else if (mainContent.equals("manageEmployers")) {
				List<EmployerInAdmin> employers = employerBO.getEmployers(page, recordsPerPage);
				int totalPages = employerBO.getTotalPages(recordsPerPage);

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
