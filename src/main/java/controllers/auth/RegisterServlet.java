package controllers.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Account;
import model.bo.AccountBO;

@WebServlet("/auth/register")
public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String role = request.getParameter("role");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			if (AccountBO.getInstance().findAccountByUsername(username) != null) {
				throw new Exception("Tên tài khoản đã tồn tại");
			}
			Account account = new Account(username, password, role);
			
			boolean isCreated = AccountBO.getInstance().createAccount(account);
			if (!isCreated) {	
				throw new Exception("Có lỗi xảy ra khi tạo tài khoản");
			}
			
			request.setAttribute("accountId", account.getId());
			
			if (account.getRole().equals("candidate"))
			{
				request.getRequestDispatcher("/register_candidate.jsp").forward(request, response);
				return;
			} else if (account.getRole().equals("employer")){
				request.getRequestDispatcher("/register_employer.jsp").forward(request, response);
				return;
			} else throw new Exception("Dữ liệu không hợp lệ!");
			
		} catch (Exception e){
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
	}
}
