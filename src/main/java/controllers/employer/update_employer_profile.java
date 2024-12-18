package controllers.employer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.EmployerBO;

@WebServlet("/update_employer_profile")
public class update_employer_profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String employr_id = request.getParameter("id");
		String destination = "employer_profile";
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String addr = request.getParameter("address");
		String link = request.getParameter("link");
		String description = request.getParameter("description");
		boolean update = EmployerBO.getInstance().updateEmployerProfile(employr_id, name, email, addr, link, description);
		if(update) {
			request.setAttribute("message", "Update success");
		} else {
			request.setAttribute("message", "Update failed");
		}
		request.getRequestDispatcher(destination).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
