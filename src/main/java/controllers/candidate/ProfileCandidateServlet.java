package controllers.candidate;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bean.Account;
import model.bean.Candidate;
import model.bo.AccountBO;
import model.bo.CandidateBO;
import model.dao.AccountDAO;
import model.dao.CandidateDAO;
import utils.ImageUploadUtil;

@WebServlet("/profile-candidate")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10,      // 10MB
	    maxRequestSize = 1024 * 1024 * 50    // 50MB
	)
public class ProfileCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    // Do something like displaying user's profile
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("ProfileCandidateServlet.doGet()");
        try {
        	String username = (String) request.getSession().getAttribute("username");

            // Find the Account based on the logged-in username
            Account account = AccountBO.getInstance().findAccountByUsername(username);
            
            // Find the associated Candidate profile using the Account ID
    		Candidate candidate = CandidateBO.getInstance().findCandidateByAccountId(account.getId());
    		System.out.println("Account: " + account);
    		System.out.println("Candidate: " + candidate);

            // Set Account and Candidate as request attributes
            request.setAttribute("account", account);
            request.setAttribute("candidate", candidate);

            // Forward to profile.jsp to display the user's profile
            request.getRequestDispatcher("candidate/profile.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.jsp");
		}
    }

    // Do something like updating the user's profile
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	request.setCharacterEncoding("UTF-8");
        	response.setContentType("text/html;charset=UTF-8");
//        	String username = (String) request.getAttribute("username");
        	String username = (String) request.getParameter("username");
        	String fullname = (String) request.getParameter("fullname");
        	String email = (String) request.getParameter("email");
        	String cvUrl = (String) request.getParameter("cvUrl");
        	
			System.out.println("ProfileCandidateServlet.doPost()");
			System.out.println("username: " + username);
			System.out.println("fullname: " + fullname);
			System.out.println("email: " + email);
			System.out.println("cvUrl: " + cvUrl);
        	
        	 // Xử lý upload file ảnh
            Part avatarPart = request.getPart("avatar"); // File từ input type="file"
            String imageUrl = null;
            
            if (avatarPart != null && avatarPart.getSize() > 0) {
                // Upload ảnh lên cloud và lấy URL
                imageUrl = ImageUploadUtil.uploadImage(avatarPart);
            }
            
            System.out.println("imageUrl: " + imageUrl);
            
         // Cập nhật thông tin vào database
            Account account = (Account) request.getSession().getAttribute("account");
            Candidate candidate = (Candidate) CandidateBO.getInstance().findCandidateByAccountId(account.getId());
            
            if (account != null && candidate != null) {
                account.setUsername(username);
                candidate.setName(fullname);
                candidate.setEmail(email);
                candidate.setCvUrl(cvUrl);
                
                if (imageUrl != null) {
                    account.setAvatarUrl(imageUrl);
                }
                // Lưu thông tin cập nhật vào database (ví dụ gọi DAO)
                AccountBO.getInstance().updateAccount(account);
                CandidateBO.getInstance().updateCandidate(candidate);

                // Trả về thông tin cập nhật
                request.getSession().setAttribute("account", account);
                request.getSession().setAttribute("candidate", candidate);
                request.setAttribute("candidate", candidate);
                request.setAttribute("success", "Profile updated successfully!");
            }
            request.getRequestDispatcher("candidate/profile.jsp").forward(request, response );
            
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while updating the profile." + e.getMessage());
            request.getRequestDispatcher("candidate/profile.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while updating the profile." + e.getMessage());
            request.getRequestDispatcher("candidate/profile.jsp").forward(request, response);
        }
    }
}