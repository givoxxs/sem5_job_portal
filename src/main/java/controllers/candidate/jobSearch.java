package controllers.candidate;

import model.bean.Account;
import model.bean.Job;
import model.bean.SalaryRange;
import model.bo.JobBO;
import model.bo.SalaryRangeBO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/search-job")
public class jobSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public jobSearch() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account != null) {
            request.setAttribute("account", account);
        }

        // Lấy các filter từ request
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
        

        // Gọi BO để lấy dữ liệu
        JobBO jobBO = JobBO.getInstance();
        List<Job> searchResults = null;
        int totalPages = 1;

        // Xử lý tìm kiếm
        if (jobName != null || salaryRangeId != null || jobType != null || experience != null || location != null) {
            int currentPage = 1;
            try {
                currentPage = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                currentPage = 1;
            }

            searchResults = jobBO.searchJobsAll(jobName, salaryRangeId, jobType, experience, location);
            System.out.println("searchResults: " + searchResults);
            totalPages = (int) Math.ceil((double) searchResults.size() / 10);
            System.out.println("totalPages: " + totalPages);

            int fromIndex = (currentPage - 1) * 10;
            int toIndex = Math.min(fromIndex + 10, searchResults.size());
            searchResults = searchResults.subList(fromIndex, toIndex); // Cắt dữ liệu theo trang
        }

        // Lấy các dữ liệu khác cho JSP
        List<Job> recentJobs = jobBO.getTopLatestJobs(6);
        List<Job> randomJobs = jobBO.getRandomJobs(3);
        List<SalaryRange> salaryRanges = SalaryRangeBO.getInstance().getAllAvailableSalaryRanges();

        // Lưu trữ thông tin vào request
        request.setAttribute("recentJobs", recentJobs);
        request.setAttribute("randomJobs", randomJobs);
        request.setAttribute("salaryRanges", salaryRanges);
        request.setAttribute("locations", new ArrayList<>(List.of("Ha Noi", "Ho Chi Minh", "Da Nang", "Others")));
        request.setAttribute("searchResults", searchResults);
        request.setAttribute("totalPages", totalPages);

        // Chuyển đến JSP
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
