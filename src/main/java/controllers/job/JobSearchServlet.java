package controllers.job;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Job;
import model.bean.SalaryRange;
import model.bo.JobBO;
import model.bo.SalaryRangeBO;

@WebServlet("/job-search")
public class JobSearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        JobBO jobBO = JobBO.getInstance();
        SalaryRangeBO salaryRangeBO = SalaryRangeBO.getInstance();

        List<Job> recentJobs = jobBO.getTopLatestJobs(5);
        List<Job> randomJobs = jobBO.getRandomJobs(10);
        List<SalaryRange> salaryRanges = salaryRangeBO.getAllAvailableSalaryRanges();
        List<String> locations = jobBO.getDistinctLocations();

        request.setAttribute("recentJobs", recentJobs);
        request.setAttribute("randomJobs", randomJobs);
        request.setAttribute("salaryRanges", salaryRanges);
        request.setAttribute("locations", locations);

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
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);



        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}