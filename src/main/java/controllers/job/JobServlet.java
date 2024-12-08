package controllers.job;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Job;
import model.bo.JobBO;

@WebServlet("/jobs")
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String employer_id = session.getAttribute("employer_id").toString();
		String action = request.getParameter("action");
		
		if (employer_id == null) {
			response.sendRedirect("login.jsp");
		}
		
		//swith
		switch (action) {
		case "show":
			request.getRequestDispatcher("showjob").forward(request, response);
			break;
		case "update":
			request.getRequestDispatcher("updatejob").forward(request, response);
			break;
		case "notavaible":
			request.getRequestDispatcher("updatejob").forward(request, response);
			break;
		case "addjob":
			request.getRequestDispatcher("add_job").forward(request, response);
			break;
		case "job_application":
			request.getRequestDispatcher("job_application").forward(request, response);
		default:
			request.getRequestDispatcher("showjob").forward(request, response);
			break;
		}
	}
}