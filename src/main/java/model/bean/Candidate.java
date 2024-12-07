package model.bean;

public class Candidate {
    private String id;
    private String accountId;
    private String name;
    private String email;
    private String cvUrl;
    
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