package model.bean;

import java.util.UUID;

public class Job_Application {
	private String id;
	private String name;
	private String email;
	private String phone;
	private String job_id;
	private String cv_url;
	private String candidate_id;
	private String status;
	
	public Job_Application() {

	}
	
	public Job_Application(String name, String email, String phone, String job_id, String cv_url, String candidate_id,
			String status) {
		this.id = "JA_" + UUID.randomUUID().toString();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.job_id = job_id;
		this.cv_url = cv_url;
		this.candidate_id = candidate_id;
		this.status = status;
	}
	
	public Job_Application(String id, String name, String email, String phone, String job_id, String cv_url,
			String candidate_id, String status) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.job_id = job_id;
		this.cv_url = cv_url;
		this.candidate_id = candidate_id;
		this.status = status;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getCv_url() {
		return cv_url;
	}
	public void setCv_url(String cv_url) {
		this.cv_url = cv_url;
	}
	public String getCandidate_id() {
		return candidate_id;
	}
	public void setCandidate_id(String candidate_id) {
		this.candidate_id = candidate_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
	
}