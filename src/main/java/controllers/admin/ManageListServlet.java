package controllers.admin;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Candidate;
import model.bean.Employer;
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
		String mainContent = (String) (request.getParameter("mainContent") != null ? request.getParameter("mainContent") : "manageCandidates");
		int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
		int recordsPerPage = 5;
		
		try {
			if (mainContent.equals("manageCandidates")) {
				List<Candidate> candidates = candidateBO.getCandidates(page, recordsPerPage);
				int totalPages = candidateBO.getTotalPages(recordsPerPage);
				
				request.setAttribute("candidates", candidates);
				request.setAttribute("mainContent", mainContent);
				request.setAttribute("currentPage", page);
				request.setAttribute("totalPages", totalPages);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/dashboard.jsp");
				dispatcher.forward(request, response);
			}
			else if (mainContent.equals("manageEmployers")) {
				List<Employer> employers = employerBO.getEmployers(page, recordsPerPage);
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
