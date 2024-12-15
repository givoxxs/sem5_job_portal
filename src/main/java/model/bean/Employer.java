package model.bean;

import java.util.UUID;

public class Employer {
    private String id;
    private String accountId;
    private String name;
    private String address;
    private String email;
    private String link; // websites, social media
    private String description;
    public Employer() {}
    public Employer(String accountId, String name, String address, String email, String link, String description) {
    	this.id = "ER_" + UUID.randomUUID().toString();
    	this.accountId = accountId;
    	this.name = name;
    	this.address = address;
    	this.email = email;
    	this.link = link;
    	this.description = description;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}