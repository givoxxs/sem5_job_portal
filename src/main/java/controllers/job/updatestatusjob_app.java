package controllers.job;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bo.JobApplicationBO;

@WebServlet("/updatestatusjob_app")
public class updatestatusjob_app extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String destination = null;
        String jobapp_id = request.getParameter("jobapp_id");
        String status = request.getParameter("status");
		if (jobapp_id != null) {
			boolean rs = JobApplicationBO.getInstance().updateStatusJobApplication(jobapp_id, status);
			if (rs) {
				request.setAttribute("message", "Update status successfully!");
			} else {
				request.setAttribute("message", "Update status failed!");
			}
			destination = "JobServlet?action=showjob";
		}
		request.getRequestDispatcher(destination).forward(request, response);
	}

}
