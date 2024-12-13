package controllers.job;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Job_Application;
import model.bo.JobApplicationBO;

@WebServlet("/job_applicationServlet")
public class job_applicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String destination = null;
		HttpSession session = request.getSession();
		String employerId = (String) session.getAttribute("employer_id");

		String action = request.getParameter("action");
		
		if	(action == null) {
            action = "showJob_applicationOfJob";
		}

		switch (action) {
			case "showJob_applicationOfJob":
				destination = "showjob_application";
				break;
			case "updateStatusJob_application":
				destination = "updatestatusjob_app";
				break;
			case "search":
                destination = "searchjob_application";
                break;
			default:
				destination = "showjob_application";
				break;
		}

		request.getRequestDispatcher(destination).forward(request, response);
	}

}
