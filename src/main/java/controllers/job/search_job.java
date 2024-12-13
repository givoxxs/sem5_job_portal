package controllers.job;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.bean.Job;
import model.bean.Job_Application;
import model.bo.JobApplicationBO;

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
		
		System.out.println("searchjob.java");
		String destination = null;
		String job_id = request.getParameter("job_id");
		String name = request.getParameter("name");
		 // Xử lý tìm kiếm
        String jobName = request.getParameter("jobName");
        String salaryRangeId = request.getParameter("salaryRange");
        String jobType = request.getParameter("jobType");
        String experience = request.getParameter("experience");
        String location = request.getParameter("location");
        
        List<Job> searchResults = null;
        int page = 1;
        int totalPages = 1;
        
        if (jobName != null || salaryRangeId != null || jobType != null || experience != null || location != null ) {
       	 try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }
       	 
       	 	//Thưc hiện tìm kiếm
            searchResults = jobBO.searchJobs(jobName, salaryRangeId, jobType, experience, location, page);
            totalPages = jobBO.getTotalPages(jobName, salaryRangeId, jobType, experience, location);

            request.setAttribute("jobName", jobName);
            request.setAttribute("salaryRangeId", salaryRangeId);
            request.setAttribute("jobType", jobType);
            request.setAttribute("experience", experience);
            request.setAttribute("location", location);


       } else {
          // Không có tham số tìm kiếm, hiển thị trang index mặc định
          page = 1;
          totalPages=1;
       }
        
        request.setAttribute("searchResults", searchResults);
        destination = "employer/showjob.jsp";
		request.getRequestDispatcher(destination).forward(request, response);
	}

}
