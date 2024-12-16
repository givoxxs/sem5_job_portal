package model.bo;

import java.sql.SQLException;

import model.bean.Account;
import model.dao.AccountDAO;
import utils.PasswordUtils;

public class AccountBO {
	// instance
	private static AccountBO instance;
	private AccountDAO accountDAO = AccountDAO.getInstance();
	
	private AccountBO() {
		
	}
	
	public static AccountBO getInstance() {
		if (instance == null) {
			synchronized(AccountBO.class) {
				instance = new AccountBO();
			}
		}
		return instance;
	}
	
	public Account findAccountByUsername(String username) throws SQLException {
		return accountDAO.findAccountByUsername(username);
	}
	
	public boolean createAccount(Account account) throws SQLException {
		account.setPassword(PasswordUtils.hashPassword(account.getPassword()));
		return accountDAO.createAccount(account);
	}
	
	public boolean updateAccount(Account account) throws SQLException {
		return accountDAO.updateAccount(account);
	}
}