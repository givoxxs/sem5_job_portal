package controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.EmployerBO;
import model.bean.Account;
import model.bean.Candidate;
import model.bean.Employer;
import model.bo.AccountBO;
import model.bo.CandidateBO;


@WebServlet("/admin/update")
public class UpdateForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AccountBO accountBO = AccountBO.getInstance();
	private static EmployerBO employerBO = EmployerBO.getInstance();
	private static CandidateBO candidateBO = CandidateBO.getInstance();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	@Override 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String id = (String) request.getParameter("id");
			if (id == null) {
				throw new Exception ("Bad Request");
			}
			String role = (String) request.getParameter("role");
			
			if (role.equals("employer")) {
				Employer employer = employerBO.getEmployerProfile(id);
				Account account = accountBO.getAccountById(employer.getAccountId());
				request.setAttribute("employer", employer);
				request.setAttribute("employerAccount", account);
				request.getRequestDispatcher("/admin/update_employer.jsp").forward(request, response);
			} else if (role.equals("candidate")) {
				Candidate candidate = candidateBO.getCandidateProfile(id);
				Account account = accountBO.getAccountById(candidate.getAccountId());
				System.out.println(candidate.getName());
				request.setAttribute("candidate", candidate);
				request.setAttribute("candidateAccount", account);
				request.getRequestDispatcher("/admin/update_candidate.jsp").forward(request, response);
			} else {
				throw new Exception ("Bad Request");
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
//			request.setAttribute("error", e.getMessage());
//			request.getRequestDispatcher("/error.jsp").forward(request, response);
			response.sendRedirect("/sem5_job_portal/admin/list");
		}
	}
}
