package model.bo;

import java.sql.SQLException;
import java.util.List;

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
		return AccountDAO.getInstance().updateAccount(account);
	}

	public List<Account> getUsers(int page, int recordsPerPage) throws SQLException{
		int start = (page - 1) * recordsPerPage;
		return accountDAO.getUsers(start, recordsPerPage);
	}
	
	public int getTotalPages(int recordsPerPage) throws SQLException{
		return (int) Math.ceil((double) accountDAO.getTotalRecords() / recordsPerPage);
	}
}