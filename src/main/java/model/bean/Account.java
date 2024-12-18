package model.bean;

import java.util.UUID;

public class Account {
    private String id; //auto generate
    private String username;
    private String password;
    private String role; // admin, employer, candidate
    private String avatarUrl; //default avatar
    private boolean isDeleted;
    
	public Account() {

	}
	
	public Account(String username, String password, String role) {
		this.id = "acct_" + UUID.randomUUID().toString();
		this.username = username;
		this.password = password;
		this.role = role;
		this.avatarUrl = "https://i.ibb.co/P9B37Bq/default-avatar.jpg"; //default avatar
		this.isDeleted = false; // default
	}
	
	public Account(String id, String username, String password, String role, String avatarUrl, boolean isDeleted) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.avatarUrl = avatarUrl;
		this.isDeleted = isDeleted;
	}
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


}