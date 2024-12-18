package controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Job_Application;
import model.bo.JobApplicationBO;

@WebServlet("/admin/view_candidate")

public class ViewCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String candidateId = (String) request.getParameter("candidateId");

		// Get the list of jobs that the user has applied to
		List<Job_Application> jobs = JobApplicationBO.getInstance().getJobApplicationByCandidateId(candidateId);
		request.setAttribute("jobs_apply", jobs);
//		List<Job_Application> jobs = JobApplicationBO.getInstance()
//			    .getDetailedJobApplicationsByCandidateId(candidate.getId());
//			request.setAttribute("jobs_apply", jobs);
		request.getRequestDispatcher("/candidate/view_apply_jobs.jsp").forward(request, response);
	}
}