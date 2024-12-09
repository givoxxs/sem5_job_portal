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

@WebServlet("/showjob")
public class showjob extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("showjob");
		String destination = null;
		HttpSession session = request.getSession();
		
		String employer_id = session.getAttribute("employer_id").toString();
		String jobid = request.getParameter("jobid");
		
		if (jobid == null) {
			List<Job> jobs = JobBO.getInstance().getJobByEmployerId(employer_id);
			request.setAttribute("listjob", jobs);
			destination = "employer/showjob.jsp";
		} else {
			Job job = JobBO.getInstance().getJobById(jobid);
			request.setAttribute("job", job);
			destination = "employer/jobdetail.jsp";
		}
		request.getRequestDispatcher(destination).forward(request, response);
	}
}
