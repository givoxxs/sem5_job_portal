package controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Candidate;
import model.bo.AccountBO;
import model.bo.CandidateBO;

@WebServlet("/admin/update-candidate")
public class UpdateCandidate extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static AccountBO accountBO = AccountBO.getInstance();
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
			
			String candidateId = request.getParameter("candidateId");
			Candidate candidate = candidateBO.getCandidateProfile(candidateId);
			
			String password = request.getParameter("password");
			
			if (password != null && !password.equals("")) {
				if (!accountBO.changePassword(candidate.getAccountId(), password)) {
					throw new Exception("Error in change Password!");
				}
			}
	        
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			// Admin không update CV của candidate;
			
			boolean isUpdated= candidateBO.updateCandidateProfile(candidate.getId(), name, email);
			if (!isUpdated) 
			{
				throw new Exception("Có lỗi xảy ra khi thêm thông tin");
			}

			response.sendRedirect("/sem5_job_portal/admin/list");
		} catch (Exception e)
		{
			e.printStackTrace();
//			request.setAttribute("error", e.getMessage());
//			request.getRequestDispatcher("/error.jsp").forward(request, response);
			response.sendRedirect("/sem5_job_portal/admin/list");
		}
	}
}