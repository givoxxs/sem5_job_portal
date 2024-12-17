package controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Account;
import model.bean.Candidate;
import model.bo.AccountBO;
import model.bo.CandidateBO;

@WebServlet("/admin/create-candidate")
public class CreateCandidate extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("CreateProfileServlet - doGet");
		doPost(req, resp);
	}

	@Override 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String role = "candidate";
			
			if (AccountBO.getInstance().findAccountByUsername(username) != null) {
				throw new Exception("Tên tài khoản đã tồn tại");
			}
			Account account = new Account(username, password, role);
			
			boolean isCreatedAccount = AccountBO.getInstance().createAccount(account);
			if (!isCreatedAccount) {	
				throw new Exception("Có lỗi xảy ra khi tạo tài khoản");
			}
	        
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String cvUrl = (String) request.getAttribute("imageUrl");
			
			Candidate candidate = new Candidate(account.getId(), name, email, cvUrl);
			System.out.println("id: " + candidate.getId());
			System.out.println("accountId: " + candidate.getAccountId());
			System.out.println("name: " + candidate.getName());
			System.out.println("email: " + candidate.getEmail());
			System.out.println("cvUrl: " + candidate.getCvUrl());
			boolean isCreatedProfile = CandidateBO.getInstance().createCandidate(candidate);
			if (!isCreatedProfile) 
			{
				throw new Exception("Có lỗi xảy ra khi thêm thông tin");
			}
			response.sendRedirect("/sem5_job_portal/admin/list");
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			response.sendRedirect("/sem5_job_portal/admin/list");
		}
	}
}