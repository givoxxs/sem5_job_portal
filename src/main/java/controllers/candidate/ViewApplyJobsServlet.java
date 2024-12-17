package controllers.candidate;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Account;
import model.bean.Candidate;
import model.bean.Job_Application;
import model.bo.CandidateBO;
import model.bo.JobApplicationBO;

@WebServlet("/view_apply_jobs")

public class ViewApplyJobsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// Get the current user
		Account account = (Account) request.getSession().getAttribute("account");
		Candidate candidate = CandidateBO.getInstance().findCandidateByAccountId(account.getId());
		// Get the list of jobs that the user has applied to
		List<Job_Application> jobs = JobApplicationBO.getInstance().getJobApplicationByCandidateId(candidate.getId());
		request.setAttribute("jobs_apply", jobs);
//		List<Job_Application> jobs = JobApplicationBO.getInstance()
//			    .getDetailedJobApplicationsByCandidateId(candidate.getId());
//			request.setAttribute("jobs_apply", jobs);
		request.getRequestDispatcher("/candidate/view_apply_jobs.jsp").forward(request, response);
	}
}