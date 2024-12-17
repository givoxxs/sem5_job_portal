package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Job;
import model.bean.SalaryRange;
import model.bo.JobBO;
import model.bo.SalaryRangeBO;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HomeServlet - doGet");
        List<Job> recentJobs = JobBO.getInstance().getTopLatestJobs(6);
        req.setAttribute("recentJobs", recentJobs);
        List<Job> randomJobs = JobBO.getInstance().getRandomJobs(6);
        req.setAttribute("randomJobs", randomJobs);
        List<SalaryRange> salaryRanges = SalaryRangeBO.getInstance().getAllAvailableSalaryRanges();
        req.setAttribute("salaryRanges", salaryRanges);
        req.setAttribute("locations", new ArrayList<>(List.of("Ha Noi", "Ho Chi Minh", "Da Nang", "Others")));
		HttpSession session = req.getSession(false); 

        if (session != null && session.getAttribute("role") != null) {
            String role = (String) session.getAttribute("role");

            switch (role) {
                case "admin":
                	resp.sendRedirect("/sem5_job_portal/admin/dashboard.jsp");
                    break;
                case "employer":
                	req.getRequestDispatcher("JobServlet").forward(req, resp);
                    break;
                case "candidate":
//                	resp.sendRedirect("/sem5_job_portal/index.jsp");
                	req.getRequestDispatcher("index_home.jsp").forward(req, resp);
                    break;
                default:
//                    resp.sendRedirect("/sem5_job_portal/index.jsp");
                	req.getRequestDispatcher("index_home.jsp").forward(req, resp);
            }
        } else {
        	req.getRequestDispatcher("index_home.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}