package controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Employer;
import model.bo.AccountBO;
import model.bo.EmployerBO;

@WebServlet("/admin/update-employer")
public class UpdateEmployer extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static AccountBO accountBO = AccountBO.getInstance();
	private static EmployerBO employerBO = EmployerBO.getInstance();
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	@Override 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {			
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			String employerId = request.getParameter("employerId");
			Employer employer = employerBO.getEmployerProfile(employerId);
			
			String password = request.getParameter("password");
			
			if (password != null && !password.equals("")) {
				if (accountBO.changePassword(employer.getAccountId(), password)) {
					throw new Exception("Error in change Password!");
				}
			}
			
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String link = request.getParameter("link");
			String description = request.getParameter("description");

			boolean isUpdated= employerBO.updateEmployerProfile(employer.getId(), name, address, email, link, description);
			if (!isUpdated) 
			{
				throw new Exception("Có lỗi xảy ra khi thêm thông tin");
			}
			
			response.sendRedirect("/sem5_job_portal/admin/list");
		} catch (Exception e)
		{
			e.printStackTrace();
//			request.setAttribute("error", e.getMessage());
//			request.getRequestDispatcher("/error.jsp").forward(request, response);
			response.sendRedirect("/sem5_job_portal/admin/list");
		}
	}
}