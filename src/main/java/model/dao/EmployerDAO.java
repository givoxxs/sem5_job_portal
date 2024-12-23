package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dto.EmployerInAdmin;
import model.bean.Employer;
import utils.DBConnect;

public class EmployerDAO{
    // create Instance
    private static EmployerDAO instance;
    private static final String SQL_CREATE_EMPLOYER= "INSERT INTO employer_profile (id, account_id, name, address, email, link, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
	private EmployerDAO() {

	}

	public static EmployerDAO getInstance() {
		if (instance == null ){
			synchronized(EmployerDAO.class) {
				if (instance == null) {
					instance = new EmployerDAO();
				}	
			}
		}

		return instance;
	}

	public boolean createEmployer(Employer employer) throws SQLException {
		 PreparedStatement ps =  null;
		try (Connection conn = DBConnect.getConnection()) {
			ps = conn.prepareStatement(SQL_CREATE_EMPLOYER);
		    ps.setString(1, employer.getId());
		    ps.setString(2, employer.getAccountId());
		    ps.setString(3, employer.getName());
		    ps.setString(4, employer.getAddress());
		    ps.setString(5, employer.getEmail());
		    ps.setString(6, employer.getLink());
		    ps.setString(7, employer.getDescription());
		    boolean result = ps.executeUpdate() > 0;
		    return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	    
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
	
	public List<EmployerInAdmin> getEmployers(int start, int recordsPerPage) throws SQLException{
		try (Connection conn = DBConnect.getConnection()) {
		    List<EmployerInAdmin> employers = new ArrayList<>();
		    String sql = "SELECT ep.*, COUNT(j.id) AS job_count " +
		             "FROM employer_profile ep " +
		             "LEFT JOIN job j ON ep.id = j.employer_id " +
		             "GROUP BY ep.id " +
		             "LIMIT ?, ?";
	        PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setInt(1, start);
	        preparedStatement.setInt(2, recordsPerPage);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	        	EmployerInAdmin employer = new EmployerInAdmin();
	            employer.setId(resultSet.getString("ep.id"));
	            employer.setName(resultSet.getString("ep.name"));
	            employer.setAddress(resultSet.getString("ep.address"));
	            employer.setEmail(resultSet.getString("ep.email"));
	            employer.setJobCount(resultSet.getInt("job_count"));
	            employers.add(employer);
		        }
		    return employers;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int getTotalRecords() throws SQLException{
		try (Connection conn = DBConnect.getConnection()) {
		    String sql = "SELECT COUNT(*) FROM employer_profile";
		    PreparedStatement preparedStatement = conn.prepareStatement(sql);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            return resultSet.getInt(1);
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return 0;
	}
	
	public List<EmployerInAdmin> searchEmployers(int start, int recordsPerPage, String searchText) throws SQLException{
	    List<EmployerInAdmin> employers = new ArrayList<>();
	    String sql = "SELECT ep.*, COUNT(j.id) AS job_count " +
	             "FROM employer_profile ep " +
	             "LEFT JOIN job j ON ep.id = j.employer_id " +
	             "WHERE ep.name LIKE ? OR ep.address LIKE ? OR ep.email LIKE ? " +
	             "GROUP BY ep.id " +
	             "LIMIT ?, ?";
	    try (Connection conn = DBConnect.getConnection()) {
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, "%" + searchText + "%");
        preparedStatement.setString(2, "%" + searchText + "%");
        preparedStatement.setString(3, "%" + searchText + "%");
        preparedStatement.setInt(4, start);
        preparedStatement.setInt(5, recordsPerPage);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
        	EmployerInAdmin employer = new EmployerInAdmin();
            employer.setId(resultSet.getString("ep.id"));
            employer.setName(resultSet.getString("ep.name"));
            employer.setAddress(resultSet.getString("ep.address"));
            employer.setEmail(resultSet.getString("ep.email"));
            employer.setJobCount(resultSet.getInt("job_count"));
            employers.add(employer);
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return employers;
	}

	public int getSearchTotalRecords(String searchText) throws SQLException{
	    String sql = "SELECT COUNT(*) FROM employer_profile WHERE name LIKE ? OR address LIKE ? OR email LIKE ?";
	    try (Connection conn = DBConnect.getConnection()) {
		    PreparedStatement preparedStatement = conn.prepareStatement(sql);
		    preparedStatement.setString(1, "%" + searchText + "%");
	        preparedStatement.setString(2, "%" + searchText + "%");
	        preparedStatement.setString(3, "%" + searchText + "%");
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            return resultSet.getInt(1);
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return 0;
	}
}