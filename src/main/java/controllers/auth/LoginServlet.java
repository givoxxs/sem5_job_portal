package controllers.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Account;
import model.bo.AccountBO;
import utils.PasswordUtils;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("RegisterServlet: redirect to register.jsp");
		System.out.println(req.getContextPath() + "/login.jsp");
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("role") != null) {
			// req.getRequestDispatcher(req.getContextPath() + "/").forward(req, resp);
			resp.sendRedirect("/sem5_job_portal/index.jsp");
		} else {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet: doPost");
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
        	
        	Account account = AccountBO.getInstance().findAccountByUsername(username);
			if (account == null || !PasswordUtils.checkPassword(password, account.getPassword())) {
				throw new Exception("Tài khoản hoặc mật khẩu sai!");
			}

			HttpSession session = request.getSession();
			
			session.setAttribute("account", account);
			session.setAttribute("username", account.getUsername());
			session.setAttribute("accountId", account.getId());
			session.setAttribute("role", account.getRole());
			session.setAttribute("avatarUrl", account.getAvatarUrl());
			
			response.sendRedirect(request.getContextPath() + "/home");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
	}
}