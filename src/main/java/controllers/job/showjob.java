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
		int page = 1; // Trang mặc định là 1
		int recordsPerPage = 8; // Số mục hiển thị mỗi trang
		
		//Lấy trang jsp yêu cầu
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		String destination = null;
		HttpSession session = request.getSession();
		
		String employer_id = session.getAttribute("employer_id").toString();
		String jobid = request.getParameter("jobid");
		
		if (employer_id == null) {
		    System.out.println("Employer_id is null");
		    return;
		}
		
		if (jobid == null) {
			
			//tính toán số trang
			List<Job> fulljobs = JobBO.getInstance().getJobByEmployerId(employer_id);
			int totalRecords = fulljobs.size();
			int noOfPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

			//lấy danh sách job cần thiết
			List<Job> jobs = JobBO.getInstance().getJobByEmployerId(employer_id,(page - 1) * recordsPerPage, recordsPerPage);
			
			// Gửi dữ liệu phân trang về JSP
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
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
