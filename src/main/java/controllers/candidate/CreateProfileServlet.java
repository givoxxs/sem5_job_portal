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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			String accountId = request.getParameter("accountId");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String cvUrl = (String) request.getAttribute("imageUrl");
			
			Candidate candidate = new Candidate(accountId, name, email, cvUrl);
			boolean isCreated = CandidateBO.getInstance().createCandidate(candidate);
			if (!isCreated) 
			{
				throw new Exception("Có lỗi xảy ra khi thêm thông tin");
			}
			response.sendRedirect("login.jsp");
		} catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("register_candidate.jsp").forward(request, response);
		}
	}
}
