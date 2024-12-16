package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.Account;
import utils.DBConnect;

public class AccountDAO {
    private Connection conn;
    // create Instance
    private static AccountDAO instance;
    private static final String SQL_FIND_ACCOUNT_BY_USERNAME = "SELECT * FROM account WHERE username = ? AND is_deleted = false";
    private static final String SQL_CREATE_ACCOUNT = "INSERT INTO account (id, username, password, role, avatar_url, is_deleted) VALUES (?, ?, ?, ?, ?, ?)";
    
	private AccountDAO() {
		try {
			conn = DBConnect.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static AccountDAO getInstance() {
		try {
			if (instance == null || instance.conn.isClosed()){
				synchronized(AccountDAO.class) {
					if (instance == null || instance.conn.isClosed()) {
						instance = new AccountDAO();
					}	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return instance;
	}
	
	public Account mapResultToAccount(ResultSet rs) throws SQLException {
		Account account = new Account();
        account.setId(rs.getString("id"));
        account.setUsername(rs.getString("username"));
        account.setPassword(rs.getString("password"));
        account.setRole(rs.getString("role"));
        account.setAvatarUrl(rs.getString("avatar_url"));
        account.setDeleted(rs.getBoolean("is_deleted"));
        return account;
	}
	
	public Account findAccountByUsername(String username) throws SQLException {
		Account account = null;
		PreparedStatement ps = conn.prepareStatement(SQL_FIND_ACCOUNT_BY_USERNAME);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			account = mapResultToAccount(rs);
		}
		return account;
	}
	
	
	public boolean createAccount(Account account) throws SQLException {
		String id = "acct_" + UUID.randomUUID().toString();
		PreparedStatement ps = conn.prepareStatement(SQL_CREATE_ACCOUNT);
		ps.setString(1, account.getId());
		ps.setString(1, account.getId());
		ps.setString(2, account.getUsername());
		ps.setString(3, account.getPassword());
		ps.setString(4, account.getRole());
		ps.setString(5, account.getAvatarUrl());
		ps.setBoolean(6, account.isDeleted());
		boolean result = ps.executeUpdate() > 0;
		return result;
	}
	
	
}
