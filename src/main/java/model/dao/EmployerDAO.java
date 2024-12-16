package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
  
  	// Create id
	public String createId(String str) {
		String rs = str.substring(0, 2).toUpperCase();
		// Lấy thời gian hiện tại
		LocalDateTime now = LocalDateTime.now();

		// Định dạng thời gian theo mẫu
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDate = now.format(formatter);
		rs += formattedDate;
		return rs;
	}
	
	//Update employer profile
	public boolean updateEmployerProfile(String id, String name, String email, String address,
			String link, String description) {
		String query = "UPDATE employer_profile SET name = ?, email = ?, address = ?, link = ?, description = ? WHERE id = ?";
		List<String> params = new ArrayList<>();
		params.add(name);
		params.add(email);
		params.add(address);
		params.add(link);
		params.add(description);
		params.add(id);
		return DBConnect.getInstance().dataSQL(params, query);
	}
	
	//Get profile
	public Employer getEmployerProfile(String id) {
		String query = "SELECT * FROM employer_profile WHERE id = ?";
		List<String> params = new ArrayList<>();
		params.add(id);
		ResultSet rs = DBConnect.getInstance().selectSQL(params, query);
		
		if (rs == null) {
			return null;
		}
		return mapResultToEmployer(rs);
	}
	
	//rs to employer
	public Employer mapResultToEmployer(ResultSet rs) {
		
		Employer employer = new Employer();
		try {
			rs.next();
			employer.setId(rs.getString("id"));
			employer.setAccountId(rs.getString("account_id"));
			employer.setName(rs.getString("name"));
			employer.setEmail(rs.getString("email"));
			employer.setAddress(rs.getString("address"));
	        employer.setLink(rs.getString("link"));
	        employer.setDescription(rs.getString("description"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employer;
	}

}