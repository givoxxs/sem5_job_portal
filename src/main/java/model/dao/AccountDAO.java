package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import model.bean.Account;
import utils.DBConnect;

public class AccountDAO {
    private Connection conn;
    private static AccountDAO instance;
    private static final String SQL_FIND_ACCOUNT_BY_USERNAME = "SELECT * FROM account WHERE username = ? AND is_deleted = false";
    private static final String SQL_CREATE_ACCOUNT = "INSERT INTO account (id, username, password, role, avatar_url, is_deleted) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_ACCOUNT = "UPDATE account SET username = ?, password = ?, role = ?, avatar_url = ?, is_deleted = ? WHERE id = ?";

    private AccountDAO() {
        try {
            conn = DBConnect.getConnection(); // Ensure DB connection is initialized only once
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static AccountDAO getInstance() {
        if (instance == null) {
            synchronized (AccountDAO.class) {
                if (instance == null) {
                    instance = new AccountDAO();
                }
            }
        }
        try {
            if (instance.conn == null || instance.conn.isClosed()) {
                instance.conn = DBConnect.getConnection();  // Reinitialize the connection if closed
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
        PreparedStatement ps = null;
        ResultSet rs = null;
//        try {
        try (Connection conn = DBConnect.getConnection();) {
            ps = conn.prepareStatement(SQL_FIND_ACCOUNT_BY_USERNAME);
            ps.setString(1, username);
            System.out.println("SQL_FIND_ACCOUNT_BY_USERNAME: " + ps.toString());
            rs = ps.executeQuery();
            if (rs.next()) {
                account = mapResultToAccount(rs);
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error finding account by username");
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return account;
    }

    public boolean createAccount(Account account) throws SQLException {
        boolean result = false;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_CREATE_ACCOUNT);
            ps.setString(1, account.getId());
            ps.setString(2, account.getUsername());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getRole());
            ps.setString(5, account.getAvatarUrl());
            ps.setBoolean(6, account.isDeleted());
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error creating account");
        } finally {
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return result;
    }

	public boolean updateAccount(Account account) {
		boolean result = false;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(SQL_UPDATE_ACCOUNT);
			ps.setString(1, account.getUsername());
			ps.setString(2, account.getPassword());
			ps.setString(3, account.getRole());
			ps.setString(4, account.getAvatarUrl());
			ps.setBoolean(5, account.isDeleted());
			ps.setString(6, account.getId());
			result = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return result;
	}
}