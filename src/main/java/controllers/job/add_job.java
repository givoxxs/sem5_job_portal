package controllers.job;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bo.JobBO;

@WebServlet("/add_job")
public class add_job extends HttpServlet {
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

		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String salaryRangeId = request.getParameter("salaryRangeId");
		String location = request.getParameter("location");
		String jobType = request.getParameter("jobType");
		String experience = request.getParameter("experience");
		if (title != null) {
			// Add job
			 boolean rs = JobBO.getInstance().addJob(employerId, title, description, salaryRangeId,
			 location, jobType, experience);
			 if (rs) {
					request.setAttribute("message", "Add job success");
				} else {
					request.setAttribute("message", "Add job fail");
			    }
			destination = "JobServlet?action=showjob";
		} else {
			destination = "form_addjob.jsp";
		}
		request.getRequestDispatcher(destination).forward(request, response);
	}

}
