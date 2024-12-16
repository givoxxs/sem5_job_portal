package controllers.candidate;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Account;
import model.bean.Candidate;
import model.bo.AccountBO;
import model.bo.CandidateBO;
import model.dao.AccountDAO;
import model.dao.CandidateDAO;

@WebServlet("/profile-candidate")
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
        	String username = (String) request.getSession().getAttribute("username");
            AccountDAO accountDAO = AccountDAO.getInstance();
            CandidateDAO candidateDAO = CandidateDAO.getInstance();

            // Get parameters from the form to update the profile
            String newName = request.getParameter("name");
            String newEmail = request.getParameter("email");
            String newCvUrl = request.getParameter("cvUrl");
            String newAvatarUrl = request.getParameter("avatarUrl");

            // Update Account information
            Account account = accountDAO.findAccountByUsername(username);
            account.setAvatarUrl(newAvatarUrl); // Update avatar URL

            // Update Candidate information
            Candidate candidate = candidateDAO.findCandidateByAccountId(account.getId());
            candidate.setName(newName);
            candidate.setEmail(newEmail);
            candidate.setCvUrl(newCvUrl); // Update CV URL

            // Save changes to the database
            boolean accountUpdated = AccountBO.getInstance().updateAccount(account);
            boolean candidateUpdated = CandidateBO.getInstance().updateCandidate(candidate);

            if (accountUpdated && candidateUpdated) {
                response.sendRedirect("profile?success=true");
            } else {
                response.sendRedirect("profile?error=true");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}