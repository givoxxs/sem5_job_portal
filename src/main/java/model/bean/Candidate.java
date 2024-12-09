package model.bean;

import java.util.UUID;

public class Candidate {
    private String id;
    private String accountId;
    private String name;
    private String email;
    private String cvUrl;
    
    public Candidate() {}
    public Candidate(String accountId, String name, String email, String cvUrl) {
    	this.id = "cand_" + UUID.randomUUID().toString();
    	this.accountId = accountId;
    	this.name = name;
    	this.email = email;
    	this.cvUrl = cvUrl;
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCvUrl() {
		return cvUrl;
	}
	public void setCvUrl(String cvUrl) {
		this.cvUrl = cvUrl;
	}

}