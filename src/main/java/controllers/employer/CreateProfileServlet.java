package controllers.employer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Account;
import model.bean.Employer;
import model.bo.AccountBO;
import model.bo.EmployerBO;

@WebServlet("/employer/create/profile")
public class CreateProfileServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {			
			String accountId = request.getParameter("accountId");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String link = request.getParameter("link");
			String description = request.getParameter("description");
			
			Employer employer = new Employer(accountId, name, address, email, link, description);
			boolean isCreated = EmployerBO.getInstance().createEmployer(employer);
			if (!isCreated) 
			{
				throw new Exception("Có lỗi xảy ra khi thêm thông tin");
			}
			response.sendRedirect("/sem5_job_portal/login.jsp");
		} catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/register_employer.jsp").forward(request, response);
		}
	}
}