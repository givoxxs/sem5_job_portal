package model.dao;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.bean.Employer;
import utils.DBConnect;

public class EmployerDAO {

	// Crete instance
	private static EmployerDAO instance;

	private EmployerDAO() {
	}

	public static EmployerDAO getInstance() {
		if (instance == null) {
			instance = new EmployerDAO();
		}
		return instance;
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
	public boolean updateEmployerProfile(String id, String name, String email, String phone, String address,
			String avatarUrl) {
		String query = "UPDATE employer_profile SET name = ?, email = ?, phone = ?, address = ?, avatar_url = ? WHERE id = ?";
		List<String> params = new ArrayList<>();
		params.add(name);
		params.add(email);
		params.add(phone);
		params.add(address);
		params.add(avatarUrl);
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
			employer.setAddress(rs.getString("address"));
	        employer.setLink(rs.getString("link"));
	        employer.setDescription(rs.getString("description"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employer;
	}
	
	public static void main(String[] args) {
		Employer employee = getInstance().getEmployerProfile("EM02");
		System.out.println(employee.getName());
	}
	
}