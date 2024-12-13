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
        String job_id = request.getParameter("job_id");
        request.setAttribute("job_id", job_id);
                
		List<Job_Application> jobApplications = JobApplicationBO.getInstance().getJobApplicationByJobId(job_id);
		request.setAttribute("listjobapplication", jobApplications);
		destination = "employer/listjob_application.jsp";

		request.getRequestDispatcher(destination).forward(request, response);
	}
}
