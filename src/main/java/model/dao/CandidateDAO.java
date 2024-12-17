package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.Candidate;
import utils.DBConnect;

public class CandidateDAO{
	private Connection conn;
    // create Instance
    private static CandidateDAO instance;
    private static final String SQL_CREATE_CANDIDATE= "INSERT INTO candidate_profile (id, account_id, name, email, cv_url) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_FIND_CANDIDATE_BY_ACCOUNT_ID = "SELECT * FROM candidate_profile WHERE account_id = ?";
    private static final String SQL_UPDATE_CANDIDATE = "UPDATE candidate_profile SET name = ?, email = ?, cv_url = ? WHERE id = ?";
    
	private CandidateDAO() {
	}

	public static CandidateDAO getInstance() {
		try {
			if (instance == null || instance.conn.isClosed()){
				synchronized(EmployerDAO.class) {
					if (instance == null || instance.conn.isClosed()) {
						instance = new CandidateDAO();
					}	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return instance;
	}
	
    public Candidate mapResultToCandidate(ResultSet rs) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setId(rs.getString("id"));
        candidate.setAccountId(rs.getString("account_id"));
        candidate.setName(rs.getString("name"));
        candidate.setEmail(rs.getString("email"));
        candidate.setCvUrl(rs.getString("cv_url"));
        return candidate;
    }
    
    public boolean createCandidate(Candidate candidate) throws SQLException{
    	System.out.println("CandidateDAO - createCandidate");
		try (Connection conn = DBConnect.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(SQL_CREATE_CANDIDATE);
			ps.setString(1, candidate.getId());
			ps.setString(2, candidate.getAccountId());
			ps.setString(3, candidate.getName());
			ps.setString(4, candidate.getEmail());
			ps.setString(5, candidate.getCvUrl());
			// tôi muốn in ra câu lệnh sql sau khi chèn của ps
			System.out.println("ps: " + ps);
			boolean result = ps.executeUpdate() > 0;
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    }

	public Candidate findCandidateByAccountId(String id) {
		System.out.println("CandidateDAO - findCandidateByAccountId");
		Candidate candidate = null;
//		try {
		try (Connection conn = DBConnect.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(SQL_FIND_CANDIDATE_BY_ACCOUNT_ID);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				candidate = mapResultToCandidate(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return candidate;
	}

	public boolean updateCandidate(Candidate candidate) {
		System.out.println("CandidateDAO - updateCandidate");
//		try {
		try (Connection conn = DBConnect.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_CANDIDATE);
			ps.setString(1, candidate.getName());
			ps.setString(2, candidate.getEmail());
			ps.setString(3, candidate.getCvUrl());
			ps.setString(4, candidate.getId());
			boolean result = ps.executeUpdate() > 0;
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}