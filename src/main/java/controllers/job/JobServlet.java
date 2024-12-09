package controllers.job;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/JobServlet")
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("JobServlet");
		HttpSession session = request.getSession();
		String destination = null;
		String employer_id = "EMP01"; session.setAttribute("employer_id",employer_id);
		String action = request.getParameter("action");
		if (action == null) {
			destination = "employer/employer_index.jsp";
			action = "null";
		}
		
//		if (employer_id == null) {
//			response.sendRedirect("login.jsp");
//		}
		
		//swith
		switch (action) {
			case "showjob":
				destination = "showjob";
				break;
			case "update":
				destination = "updatejob";
				break;
			case "notavaible":
				destination = "updatejob";
				break;
			case "formaddjob":
				destination = "employer/Form_add_job.jsp";
				break;
			case "addjob":
				destination = "add_job";
				break;
			case "job_application":
				destination = "job_application";
			default:
				destination = "employer/employer_index.jsp";
				break;
		}
		
		request.getRequestDispatcher(destination).forward(request, response);
	}
}