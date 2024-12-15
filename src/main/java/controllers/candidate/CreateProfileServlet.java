package controllers.candidate;

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

@WebServlet("/candidate/create/profile")
public class CreateProfileServlet extends HttpServlet{
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
			System.out.println("CreateProfileServlet - doPost");
			
			String accountId = request.getParameter("accountId");
	        if (accountId == null || accountId.isEmpty()) {
	            throw new Exception("ID tài khoản bị thiếu.");
	        }
	        
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String cvUrl = (String) request.getAttribute("imageUrl");
			
			Candidate candidate = new Candidate(accountId, name, email, cvUrl);
			System.out.println("id: " + candidate.getId());
			System.out.println("accountId: " + candidate.getAccountId());
			System.out.println("name: " + candidate.getName());
			System.out.println("email: " + candidate.getEmail());
			System.out.println("cvUrl: " + candidate.getCvUrl());
			boolean isCreated = CandidateBO.getInstance().createCandidate(candidate);
			if (!isCreated) 
			{
				throw new Exception("Có lỗi xảy ra khi thêm thông tin");
			}
			request.getRequestDispatcher("/auth/login").forward(request, response);
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			response.sendRedirect(request.getContextPath() + "/register.jsp");
		}
	}
}