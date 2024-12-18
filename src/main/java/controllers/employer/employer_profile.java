package controllers.employer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Employer;
import model.bo.EmployerBO;

@WebServlet("/employer_profile")
public class employer_profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String employer_id = session.getAttribute("employer_id").toString();
		Employer emp = EmployerBO.getInstance().getEmployerProfile(employer_id);
		request.setAttribute("employer", emp);
		
		String destination = "employer/employer_profile.jsp";
		request.getRequestDispatcher(destination).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
