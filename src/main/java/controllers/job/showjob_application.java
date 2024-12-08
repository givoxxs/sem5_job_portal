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

@WebServlet("/showjob_application")
public class showjob_application extends HttpServlet {
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
        String job_app_id = request.getParameter("job_app_id");
        String job_id = request.getParameter("job_id");
        
		if (job_id != null) {
			List<Job_Application> jobApplications = JobApplicationBO.getInstance().getJobApplicationByJobId(job_id);
			request.setAttribute("listjobapplication", jobApplications);
			destination = "listjob_application.jsp";
		} else if (job_app_id != null) {
			Job_Application jobApplication = JobApplicationBO.getInstance().getJobApplicationById(job_app_id);
			request.setAttribute("jobApplication", jobApplication);
			destination = "detail_job_app";
		} 
		request.getRequestDispatcher(destination).forward(request, response);
	}
}
