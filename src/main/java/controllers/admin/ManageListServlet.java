package controllers.admin;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Account;
import model.bo.AccountBO;

@WebServlet("/admin/list")
public class ManageListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static AccountBO accountBO = AccountBO.getInstance();
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		String mainContent = (String) (request.getParameter("mainContent") != null ? request.getParameter("mainContent") : "manageUsers");
		int page = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
		int recordsPerPage = 5;
		
		try {
			if (mainContent.equals("manageUsers")) {
				List<Account> users = accountBO.getUsers(page, recordsPerPage);
				int totalPages = accountBO.getTotalPages(recordsPerPage);

				request.setAttribute("users", users);
				request.setAttribute("mainContent", mainContent);
				request.setAttribute("currentPage", page);
				request.setAttribute("totalPages", totalPages);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/dashboard.jsp");
				dispatcher.forward(request, response);
			}
			else if (mainContent.equals("manageJobs")) {
				List<Job> jobs = jobBO.getJobs(page, recordsPerPage);
				int totalPages = jobBO.totalPages(recordsPerPage);

				request.setAttribute("jobs", jobs);
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
