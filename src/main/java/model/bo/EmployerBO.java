package model.bo;

import java.sql.SQLException;
import java.util.List;

import model.bean.Employer;
import model.dao.EmployerDAO;

public class EmployerBO {

	//Create instance
	private static EmployerBO instance;
	private static EmployerDAO employerDAO = EmployerDAO.getInstance();

	private EmployerBO() {
	}

	public static EmployerBO getInstance() {
		if (instance == null) {
			instance = new EmployerBO();
		}
		return instance;
	}
	
	//get profie by id
	public Employer getEmployerProfile(String id) {
		return EmployerDAO.getInstance().getEmployerProfile(id);
	}
	
	//update profile
	public boolean updateEmployerProfile(String id, String name, String email, String address,
			String link, String description) {
		return EmployerDAO.getInstance().updateEmployerProfile(id, name, email, address, link, description);
	}

    public boolean createEmployer(Employer employer) throws SQLException {
        return EmployerDAO.getInstance().createEmployer(employer);
    }
    
    //Get employer by account id
	public String getEmployerByAccountId(String accountId) {
		return EmployerDAO.getInstance().getEmployerIdByAccountId(accountId);
	}
	
	public boolean deleteEmployerById(String id) {
		return EmployerDAO.getInstance().deleteEmployerById(id);
	}
	
	public List<Employer> getEmployers(int page, int recordsPerPage) throws SQLException{
		int start = (page - 1) * recordsPerPage;
		return employerDAO.getEmployers(start, recordsPerPage);
	}
	
	public int getTotalPages(int recordsPerPage) throws SQLException{
		return (int) Math.ceil((double) employerDAO.getTotalRecords() / recordsPerPage);
	}
}