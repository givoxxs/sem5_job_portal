package controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.CandidateBO;
import model.bo.EmployerBO;

@WebServlet("/admin/delete")
public class DeleteForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CandidateBO candidateBO = CandidateBO.getInstance();
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
			
			String id = (String) request.getParameter("id");
			if (id == null) {
				throw new Exception ("Bad Request");
			}
			String role = (String) request.getParameter("role");
			
			if (role.equals("employer")) {
				boolean isDeleted = employerBO.deleteEmployerById(id);
				if (!isDeleted) throw new Exception("Internal Error");
				response.sendRedirect("/sem5_job_portal/admin/list");
			} else if (role.equals("candidate")) {
				boolean isDeleted = candidateBO.deleteCandidateById(id);
				if (!isDeleted) throw new Exception("Internal Error");
				response.sendRedirect("/sem5_job_portal/admin/list");
			} else {
				throw new Exception ("Bad Request");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
//			request.setAttribute("error", e.getMessage());
//			request.getRequestDispatcher("/error.jsp").forward(request, response);
			response.sendRedirect("/sem5_job_portal/admin/list");
		}
	}
}
