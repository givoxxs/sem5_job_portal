package controllers.job;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.bean.Job;
import model.bean.Job_Application;
import model.bean.SalaryRange;
import model.bo.JobApplicationBO;
import model.bo.JobBO;
import model.bo.SalaryRangeBO;

@WebServlet("/search_job")
public class search_job extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String destination = null;
		 // Xử lý tìm kiếm
        String jobName = request.getParameter("jobName");
        String salaryRangeId = request.getParameter("salaryRange");
        String jobType = request.getParameter("jobType");
        String experience = request.getParameter("experience");
        String location = request.getParameter("location");
        
        HttpSession session = request.getSession();
        String employer_id = session.getAttribute("employer_id").toString();
        
        List<SalaryRange> salaryRanges = SalaryRangeBO.getInstance().getAllAvailableSalaryRanges();
		request.setAttribute("salaryRanges", salaryRanges);
        
        List<Job> searchResults = null;
        int page = 1;
        int totalPages = 1;
        int recordsPerPage = 9; // Số mục hiển thị mỗi trang
        
        if (jobName != null || salaryRangeId != null || jobType != null || experience != null || location != null ) {
        	
        	if(request.getParameter("page") != null)
        		page = Integer.parseInt(request.getParameter("page"));
        	
       	 	//Tính tổng số trang
       	 	searchResults = JobBO.getInstance().searchJobs(employer_id, jobName, salaryRangeId, jobType, experience, location, -1, -1);
	       	int totalRecords = searchResults.size();
			int noOfPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
			totalPages = noOfPages;
			
       	 	//Thưc hiện tìm kiếm
            searchResults = JobBO.getInstance().searchJobs( employer_id, jobName, salaryRangeId, jobType, experience, location, (page - 1) * recordsPerPage, recordsPerPage );


       } 
        
        //Gửi lên jsp
        request.setAttribute("search", true);
        request.setAttribute("noOfPages", totalPages);
		request.setAttribute("currentPage", page);
        request.setAttribute("listjob", searchResults);
        destination = "employer/showjob.jsp";
		request.getRequestDispatcher(destination).forward(request, response);
	}

}
