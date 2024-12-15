package controllers.job;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import model.bean.Job_Application;
import model.bo.JobApplicationBO;

import javax.servlet.http.*;

@WebServlet("/searchjob_application")
public class searchjob_application extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String destination = null;
		String job_id = request.getParameter("job_id");
		String name = request.getParameter("name");
		if (name != null) {
			List<Job_Application> jobApplications = JobApplicationBO.getInstance().searchJobApplicationByName(job_id,name);
			request.setAttribute("listjobapplication", jobApplications);
			destination = "employer/listjob_application.jsp";
		}
		request.getRequestDispatcher(destination).forward(request, response);
	}

}
