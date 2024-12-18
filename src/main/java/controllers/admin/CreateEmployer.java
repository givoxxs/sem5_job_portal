package controllers.admin;

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

@WebServlet("/admin/create-employer")
public class CreateEmployer extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
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
			
			String accountId = account.getId();
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
			response.sendRedirect("/sem5_job_portal/admin/list");
		} catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			response.sendRedirect("/sem5_job_portal/admin/list");
		}
	}
}