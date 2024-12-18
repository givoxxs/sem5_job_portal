package controllers.job;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Account;
import model.bean.Candidate;
import model.bean.Job;
import model.bo.CandidateBO;
import model.bo.JobBO;

@WebServlet("/job-detail")
public class JobDetailServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
    	System.out.println("JobDetailServlet.doGet()");
        String jobId = request.getParameter("id");

        try {
            Job job = JobBO.getInstance().getJobById(jobId);
            Account account = (Account) request.getSession().getAttribute("account");
            if (account != null) {
            	Candidate candidate = CandidateBO.getInstance().findCandidateByAccountId(account.getId());
            	request.setAttribute("candidate", candidate);
            }    
            
            if (job != null) {
                request.setAttribute("job", job);
                request.getRequestDispatcher("candidate/job-detail.jsp").forward(request, response); // Chuyển đến job-detail.jsp
            } else {
                // Xử lý trường hợp không tìm thấy công việc (ví dụ: hiển thị thông báo lỗi)
                response.getWriter().println("Job not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý exception
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}