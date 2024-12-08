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

@WebServlet("/job_application")
public class job_application extends HttpServlet {
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
		
		if (employerId == null) {
			response.sendRedirect("login.jsp");
		}
		
		switch (action) {
		case "showJob_applicationOfEmployer":
			request.getRequestDispatcher("showjob_application").forward(request, response);
			break;
		case "updateStatusJob_application":
			request.getRequestDispatcher("updatestatusjob_app").forward(request, response);
			break;
		}
//		if (jobid != null) {
//			List<Job_Application> jobApplications = JobApplicationBO.getInstance().getJobApplicationByJobId(jobid);
//			request.setAttribute("listjob_application", jobApplications);
//			destination = "listjob_application.jsp";
//		} else {
//
//		}
		request.getRequestDispatcher(destination).forward(request, response);
	}

}
