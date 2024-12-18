package controllers.job;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Account;
import model.bo.EmployerBO;

@WebServlet("/JobServlet")
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 // Thiết lập mã hóa UTF-8 cho request và response
        request.setCharacterEncoding("UTF-8"); // Đảm bảo request sử dụng UTF-8
        response.setContentType("text/html; charset=UTF-8"); // Thiết lập phản hồi (response) sử dụng UTF-8
        response.setCharacterEncoding("UTF-8"); // Đảm bảo response sử dụng UTF-8
        
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		String employer_id = EmployerBO.getInstance().getEmployerByAccountId(account.getId());
		session.setAttribute("employer_id",employer_id);
		String destination = null;
		//tạo session demo
//		String employer_id = "EMP01"; session.setAttribute("employer_id",employer_id);
		
		//get action
		String action = request.getParameter("action");
		if (action == null) {
			destination = "employer/employer_index.jsp";
			action = "null";
		}
		
		if (employer_id == null) {
			response.sendRedirect("login.jsp");
		}
		
//		if(action == null) {
//			action = "null";
//			System.out.println("action is null");
//		}
		//swith
		switch (action) {
			case "showjob":
				destination = "showjob";
				break;
			case "update":
				destination = "updatejob";
				break;
			case "updateavaible":
				destination = "updatejob";
				break;
			case "formaddjob":
				destination = "employer/Form_add_job.jsp";
				break;
			case "addjob":
				destination = "add_job";
				break;
			case "search":
				destination = "search_job";
				break;
			default:
				destination = "employer/employer_index.jsp";
				break;
		}
		
		request.getRequestDispatcher(destination).forward(request, response);
	}
}