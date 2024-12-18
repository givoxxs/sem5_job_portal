package controllers.candidate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Job_Application;
import model.bo.JobApplicationBO;
import utils.EmailUtils;

@WebServlet("/apply-job-candidate")
public class ApplyJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		doPost(req, resp);
	}
	
	// Apply for a job
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String job_id = request.getParameter("job_id");
		System.out.println("ApplyJobServlet.doPost()");
		try {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String cv_url = request.getParameter("cv_url");
			
			// Get the candidate ID
			String candidate_id = request.getParameter("candidate_id");
			
			Job_Application job = new Job_Application(name, email, phone, job_id, cv_url, candidate_id, "Pending");
			System.out.println("ApplyJobServlet.doPost() job: " + job);
			// Insert the job application into the database
			boolean resule = JobApplicationBO.getInstance().insertJobApplication(job);
			System.out.println("ApplyJobServlet.doPost() resule: " + resule);
			
			if (resule) {
				// Send an email to the candidate
				String candidateEmail = email;
				String candidateName = name;
				String subject = "Job Application Confirmation";
				String body = "Dear " + candidateName + ",\n\n"
						+ "Your job application has been submitted successfully.\n\n"
						+ "Thank you for your interest in our company.\n\n" + "Best regards,\n" + "HR Department";
//				EmailUtils.getInstance().sendEmail(candidateEmail, subject, body);
				// Create a new thread for sending email
				Thread emailThread = new Thread(() -> {
					try {
						EmailUtils.getInstance().sendEmail(candidateEmail, subject, body);
						System.out.println("Email sent successfully to " + candidateEmail);
					} catch (Exception e) {
						System.err.println("Failed to send email: " + e.getMessage());
					}
				});
				emailThread.start();
			} else {
				request.setAttribute("error", "Failed to apply for the job");
			}
			System.out.println("ApplyJobServlet.doPost() job_id: " + job_id);
			// Redirect to the job details page
			request.getRequestDispatcher("job-detail?id=" + job_id).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Failed to apply for the job " + e.getMessage());
			request.getRequestDispatcher("job-detail?id=" + job_id).forward(request, response);
		}
	}
}