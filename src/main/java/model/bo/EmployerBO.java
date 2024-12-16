package model.bo;

import java.sql.SQLException;

import model.bean.Employer;
import model.dao.EmployerDAO;

public class EmployerBO {

	//Create instance
	private static EmployerBO instance;

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

}