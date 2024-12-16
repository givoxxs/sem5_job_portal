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
        HttpSession session = req.getSession(false); 
        HttpSession session = req.getSession(false); 

        if (session != null && session.getAttribute("role") != null) {
            String role = (String) session.getAttribute("role");

            switch (role) {
                case "admin":
                	resp.sendRedirect("/sem5_job_portal/admin/dashboard.jsp");
                    break;
                case "employer":
                	req.getRequestDispatcher("JobServlet").forward(req, resp);
                    break;
                case "candidate":
                	resp.sendRedirect("/sem5_job_portal/index.jsp");
                    break;
                default:
                    resp.sendRedirect("/sem5_job_portal/index.jsp");
            }
        } else {
//            req.getRequestDispatcher("jsp/index.jsp").forward(req, resp);
        	resp.sendRedirect("/sem5_job_portal/index.jsp"); //để test login
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/");
    }
}