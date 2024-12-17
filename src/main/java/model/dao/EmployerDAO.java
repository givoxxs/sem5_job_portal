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
		try {
			return mapResultToEmployer(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//Update employer profile
	public boolean deleteEmployerById(String id) {
		String query = "DELETE FROM employer_profile WHERE id = ?";
		List<String> params = new ArrayList<>();
		params.add(id);
		return DBConnect.getInstance().dataSQL(params, query);
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
		return employer;
	}
	
	//Get employer_ID by account_id
	public String getEmployerIdByAccountId(String accountId) {
		String query = "SELECT id FROM employer_profile WHERE account_id = ?";
		List<String> params = new ArrayList<>();
		params.add(accountId);
		ResultSet rs = DBConnect.getInstance().selectSQL(params, query);
		if (rs == null) {
			return null;
		}
		try {
			rs.next();
			return rs.getString("id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Employer> getEmployers(int start, int recordsPerPage) throws SQLException{
	    List<Employer> employers = new ArrayList<>();
	    String sql = "SELECT * FROM employer_profile LIMIT ?, ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, start);
        preparedStatement.setInt(2, recordsPerPage);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Employer employer = new Employer();
            employer.setId(resultSet.getString("id"));
            employer.setName(resultSet.getString("name"));
            employer.setAddress(resultSet.getString("address"));
            employer.setEmail(resultSet.getString("email"));
            employers.add(employer);
	        }
	    return employers;
	}

	public int getTotalRecords() throws SQLException{
	    String sql = "SELECT COUNT(*) FROM employer_profile";
	    PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
	    return 0;
	}
}