package controllers.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Account;
import model.bean.Job;
import model.bean.SalaryRange;
import model.bo.JobBO;
import model.bo.SalaryRangeBO;

@WebServlet("/job-search")
public class JobSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int JOBS_PER_PAGE = 6;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		System.out.println("JobSearchServlet: doGet");
		// in ra url hiênj tai
		System.out.println(request.getRequestURL());
    	Account account = (Account) request.getSession().getAttribute("account");
        if (account != null) {
            request.setAttribute("account", account);
        }
        
        JobBO jobBO = JobBO.getInstance();
        List<Job> searchResults = null;
        int totalPages = 1;

        String jobName = request.getParameter("jobName");
        String salaryRangeId = request.getParameter("salaryRange");
        String jobType = request.getParameter("jobType");
        String experience = request.getParameter("experience");
        String location = request.getParameter("location");
        
        System.out.println("jobName: " + jobName);
        System.out.println("salaryRangeId: " + salaryRangeId);
        System.out.println("jobType: " + jobType);
        System.out.println("experience: " + experience);
        System.out.println("location: " + location);

        // Xử lý tìm kiếm
        if (jobName != null || salaryRangeId != null || jobType != null || experience != null || location != null) {
            int currentPage = 1;
            try {
                currentPage = Integer.parseInt(request.getParameter("page"));
                request.setAttribute("page", Integer.toString(currentPage));
            } catch (NumberFormatException e) {
                currentPage = 1;
            }

            searchResults = jobBO.searchJobsAll(jobName, salaryRangeId, jobType, experience, location);
            System.out.println("searchResults: " + searchResults.size());
            totalPages = (int) Math.ceil((double) searchResults.size() / JOBS_PER_PAGE);
            System.out.println("totalPages: " + totalPages);
            
            int fromIndex = (currentPage - 1) * JOBS_PER_PAGE;
            int toIndex = Math.min(fromIndex + JOBS_PER_PAGE, searchResults.size());
            System.out.println("fromIndex: " + fromIndex);
            System.out.println("toIndex: " + toIndex);
            searchResults = searchResults.subList(fromIndex, toIndex);
            System.out.println("searchResults: " + searchResults);
        }
        
     // Lấy các dữ liệu khác cho JSP
        List<Job> recentJobs = jobBO.getTopLatestJobs(6);
        List<Job> randomJobs = jobBO.getRandomJobs(3);
        List<SalaryRange> salaryRanges = SalaryRangeBO.getInstance().getAllAvailableSalaryRanges();

        // Lưu trữ thông tin vào request
        request.setAttribute("recentJobs", recentJobs);
        request.setAttribute("randomJobs", randomJobs);
        request.setAttribute("salaryRanges", salaryRanges);
        request.setAttribute("locations", new ArrayList<>(List.of("Ha Noi", "Ho Chi Minh", "Da Nang")));
        request.setAttribute("searchResults", searchResults);
//        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalPages", Integer.toString(totalPages));
        request.getRequestDispatcher("index_home.jsp").forward(request, response);
    }
}