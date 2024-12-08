package model.bo;

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
	public boolean updateEmployerProfile(String id, String name, String email, String phone, String address,
			String avatarUrl) {
		return EmployerDAO.getInstance().updateEmployerProfile(id, name, email, phone, address, avatarUrl);
	}
	
	
}