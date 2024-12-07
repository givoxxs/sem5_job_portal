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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        try {
        	
        	Account account = AccountBO.getInstance().findAccountByUsername(username);
			if (account == null) {
				req.getRequestDispatcher("login.jsp?error=Invalid credentials").forward(req, resp);
				return;
			}
			
			if (!PasswordUtils.checkPassword(password, account.getPassword())) {
				req.getRequestDispatcher("login.jsp?error=Invalid credentials").forward(req, resp);
				return;
			}
			
			HttpSession session = req.getSession();
			
			session.setAttribute("account", account);
			resp.sendRedirect("jobs");
			if (account.getRole().equals("admin")) {
				req.getRequestDispatcher("admin").forward(req, resp);
			} else if (account.getRole().equals("employer")) {
				req.getRequestDispatcher("jobs").forward(req, resp);
			} else {
//				resp.sendRedirect();
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
        } catch (Exception e) {
            e.printStackTrace();
            req.getRequestDispatcher("login.jsp?error=Internal error").forward(req, resp);
        }
	}
}