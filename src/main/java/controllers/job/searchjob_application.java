package controllers.job;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import model.bean.Job_Application;
import model.bo.JobApplicationBO;

import javax.servlet.http.*;

@WebServlet("/searchjob_application")
public class searchjob_application extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int page = 1; // Trang mặc định là 1
		int recordsPerPage = 8; // Số mục hiển thị mỗi trang
		
		//Lấy trang jsp yêu cầu
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		String destination = null;
		String job_id = request.getParameter("job_id");
		String name = request.getParameter("name");
		
		if (name != null) {
			//tính toán số trang
			List<Job_Application> fulljobApplications = JobApplicationBO.getInstance().searchJobApplicationByName(job_id,name);
			int totalRecords = fulljobApplications.size();
			int noOfPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
			
			//Lấy danh sách theo trang
			List<Job_Application> jobApplications = JobApplicationBO.getInstance().searchJobApplicationByName(job_id,name, (page - 1) * recordsPerPage, recordsPerPage);
			
			// Gửi dữ liệu phân trang về JSP
			request.setAttribute("search", true);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("listjobapplication", jobApplications);
			destination = "employer/listjob_application.jsp";
		}
		request.getRequestDispatcher(destination).forward(request, response);
	}

}
