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
    
	private CandidateDAO() {
		try {
			conn = DBConnect.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		PreparedStatement ps = conn.prepareStatement(SQL_CREATE_CANDIDATE);
		ps.setString(1, candidate.getId());
		ps.setString(2, candidate.getAccountId());
		ps.setString(3, candidate.getName());
		ps.setString(4, candidate.getEmail());
		ps.setString(5, candidate.getCvUrl());
		boolean result = ps.executeUpdate() > 0;
		return result;
    }
}