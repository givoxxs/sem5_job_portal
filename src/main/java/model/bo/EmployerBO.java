package model.bo;

import java.sql.SQLException;

import model.bean.Employer;
import model.dao.EmployerDAO;

public class EmployerBO {
	private static EmployerBO instance;
    private EmployerDAO employerDAO = EmployerDAO.getInstance();

    private EmployerBO() {	}

    public static EmployerBO getInstance() {
        if (instance == null) {
        	synchronized(EmployerBO.class) {
        		instance = new EmployerBO();
        	}
        }
        return instance;
    }

    public boolean createEmployer(Employer employer) throws SQLException {
        return employerDAO.createEmployer(employer);
    }
}