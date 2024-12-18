package controllers.employer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EmployerServlet")
public class EmployerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		 // Thiết lập mã hóa UTF-8 cho request và response
        request.setCharacterEncoding("UTF-8"); // Đảm bảo request sử dụng UTF-8
        response.setContentType("text/html; charset=UTF-8"); // Thiết lập phản hồi (response) sử dụng UTF-8
        response.setCharacterEncoding("UTF-8"); // Đảm bảo response sử dụng UTF-8
        
		HttpSession session = request.getSession();
		String destination = null;
		String employer_id = session.getAttribute("employer_id").toString();
		String action = request.getParameter("action");
		if (action == null) {
			destination = "employer/employer_index.jsp";
			action = "null";
		}
		
		//swith
		switch (action) {
			case "showprofile":
				destination = "employer_profile";
				break;
			case "update":
				destination = "update_employer_profile";
				break;
			default:
				destination = "employer/employer_index.jsp";
				break;
		}
		
		request.getRequestDispatcher(destination).forward(request, response);
	}
}
