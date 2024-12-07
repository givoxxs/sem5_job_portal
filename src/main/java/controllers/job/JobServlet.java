package controllers.job;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Job;
import model.bo.JobBO;

@WebServlet("/jobs")
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JobBO jobService = JobBO.getInstance();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Job> jobs = jobService.getAllAvailableJobs();
		req.setAttribute("jobs", jobs);
		String url = "/index.jsp";
		req.getRequestDispatcher(url).forward(req, resp);
	}
}