package model.bean;

import java.sql.Timestamp;

public class Job {
	private String id;
    private String employerId;
    private String title;
    private String description;
    private String salaryRangeId; // ID của SalaryRange
    private String location;
    private String jobType; // part-time, full-time, internship
    private String experience; // Intern, Fresher, Junior, ...
    private Timestamp datePost;
    private boolean isAvailable;
    private String salaryRange;
    
	public Job() {

	}
	
	public Job(String id, String employerId, String title, String description, String salaryRangeId, String location,
			String jobType, String experience, Timestamp datePost, boolean isAvailable, String salaryRange) {
		this.id = id;
		this.employerId = employerId;
		this.title = title;
		this.description = description;
		this.salaryRangeId = salaryRangeId;
		this.location = location;
		this.jobType = jobType;
		this.experience = experience;
		this.datePost = datePost;
		this.isAvailable = isAvailable;
		this.salaryRange = salaryRange;
	}
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmployerId() {
		return employerId;
	}
	public void setEmployerId(String employerId) {
		this.employerId = employerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSalaryRangeId() {
		return salaryRangeId;
	}
	public void setSalaryRangeId(String salaryRangeId) {
		this.salaryRangeId = salaryRangeId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public java.sql.Timestamp getDatePost() {
		return datePost;
	}
	public void setDatePost(java.sql.Timestamp datePost) {
		this.datePost = datePost;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getSalaryRange() {
		return salaryRange;
	}
	public void setSalaryRange(String salaryRange) {
		this.salaryRange = salaryRange;
	}
}