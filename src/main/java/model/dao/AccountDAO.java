package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
			conn = DBConnect.getConnection();
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
		ps.setString(2, account.getUsername());
		ps.setString(3, account.getPassword());
		ps.setString(4, account.getRole());
		ps.setString(5, account.getAvatarUrl());
		ps.setBoolean(6, account.isDeleted());
		boolean result = ps.executeUpdate() > 0;
		return result;
	}
	
	public Account getAccountById(String id) throws SQLException {
		Account account = null;
		String sql = "SELECT * FROM account WHERE id = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			account = mapResultToAccount(rs);
		}
		return account;
	}
	
	public boolean changePassword(String id, String newPassword) throws SQLException {
		String sql = "UPDATE account SET password = ? WHERE id = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, newPassword);
		preparedStatement.setString(2, id);
		boolean result = preparedStatement.executeUpdate() > 0;
		return result;
	}
	
	public List<Account> getUsers(int start, int recordsPerPage) throws SQLException{
	    List<Account> users = new ArrayList<>();
	    String sql = "SELECT * FROM account LIMIT ?, ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, start);
        preparedStatement.setInt(2, recordsPerPage);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Account user = new Account();
            user.setId(resultSet.getString("id"));
            user.setUsername(resultSet.getString("username"));
            user.setRole(resultSet.getString("role"));
            users.add(user);
	        }
	    return users;
	}

	public int getTotalRecords() throws SQLException{
	    String sql = "SELECT COUNT(*) FROM account";
	    PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
	    return 0;
	}

}
