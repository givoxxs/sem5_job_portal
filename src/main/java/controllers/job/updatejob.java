package controllers.job;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bo.JobBO;

@WebServlet("/updatejob")
public class updatejob extends HttpServlet {
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

		String jobid = request.getParameter("jobid");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String salaryRangeId = request.getParameter("salaryRangeId");
		String location = request.getParameter("location");
		String jobType = request.getParameter("jobType");
		String experience = request.getParameter("experience");
		String status = request.getParameter("status");
		
		if (status == null) {
			if( jobid != null || title != null || description != null || salaryRangeId != null || location != null || jobType != null || experience != null) {
				// Update job
				boolean rs = JobBO.getInstance().updateJob(jobid, title, description, salaryRangeId, location, jobType, experience);
				if(rs) {
                    request.setAttribute("message", "Update job success");
				} else {
					request.setAttribute("message", "Update job failed");
				}
				destination = "JobServlet?action=showjob";
			}else {
                destination = "form_updatejob.jsp";
			}
		} else {
			JobBO.getInstance().updateAvailableJob(jobid, status);
			destination = "JobServlet?action=showjob";
		}
		request.getRequestDispatcher(destination).forward(request, response);
	}

}
