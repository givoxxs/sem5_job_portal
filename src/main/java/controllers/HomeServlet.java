package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // Kiểm tra session hiện tại (nếu có)

        if (session != null && session.getAttribute("role") != null) {
            String role = (String) session.getAttribute("role");

            switch (role) {
                case "admin":
                	req.getRequestDispatcher("admin/dashboard.jsp").forward(req, resp);
                    break;
                case "employer":
                	req.getRequestDispatcher("JobServlet").forward(req, resp);
                    break;
                case "candidate":
                	req.getRequestDispatcher("index.jsp").forward(req, resp);
                    break;
                default:
                    resp.sendRedirect("index.jsp");
            }
        } else {
//            req.getRequestDispatcher("jsp/index.jsp").forward(req, resp);
        	resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/");
    }
}