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

		int page = 1; // Trang mặc định là 1
		int recordsPerPage = 9; // Số mục hiển thị mỗi trang
		
		//Lấy trang jsp yêu cầu
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		String job_id = request.getParameter("job_id");
		
		List<Job_Application> fulljobApplications = JobApplicationBO.getInstance().getJobApplicationByJobId(job_id);
		int totalRecords = fulljobApplications.size();
		int noOfPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
        
        //lấy danh sách job cần thiết
        List<Job_Application> jobApplications = JobApplicationBO.getInstance().getJobApplicationByJobId(job_id,(page - 1) * recordsPerPage, recordsPerPage);
        
        //Gửi dữ liệu phân trang về JSP
        request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("currentPage", page);
        request.setAttribute("job_id", job_id);   
		request.setAttribute("listjobapplication", jobApplications);
		
		String destination = "employer/listjob_application.jsp";

		request.getRequestDispatcher(destination).forward(request, response);
	}
}
