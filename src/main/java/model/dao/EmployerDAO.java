package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.Employer;
import utils.DBConnect;

public class EmployerDAO{
	private Connection conn;
    // create Instance
    private static EmployerDAO instance;
    private static final String SQL_CREATE_EMPLOYER= "INSERT INTO employer_profile (id, account_id, name, address, email, link, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
	private EmployerDAO() {
		try {
			conn = DBConnect.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static EmployerDAO getInstance() {
		try {
			if (instance == null || instance.conn.isClosed()){
				synchronized(EmployerDAO.class) {
					if (instance == null || instance.conn.isClosed()) {
						instance = new EmployerDAO();
					}	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return instance;
	}
	
	public Employer mapResultToEmployer(ResultSet rs) throws SQLException {
	    Employer employer = new Employer();
	    employer.setId(rs.getString("id"));
	    employer.setAccountId(rs.getString("account_id"));
	    employer.setName(rs.getString("name"));
	    employer.setAddress(rs.getString("address"));
	    employer.setEmail(rs.getString("email"));
	    employer.setLink(rs.getString("link"));
	    employer.setDescription(rs.getString("description"));
	    return employer;
	}

	public boolean createEmployer(Employer employer) throws SQLException {
	    PreparedStatement ps = conn.prepareStatement(SQL_CREATE_EMPLOYER);
	    ps.setString(1, employer.getId());
	    ps.setString(2, employer.getAccountId());
	    ps.setString(3, employer.getName());
	    ps.setString(4, employer.getAddress());
	    ps.setString(5, employer.getEmail());
	    ps.setString(6, employer.getLink());
	    ps.setString(7, employer.getDescription());
	    boolean result = ps.executeUpdate() > 0;
	    return result;
	}
}