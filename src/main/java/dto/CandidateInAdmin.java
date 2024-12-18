package dto;

public class CandidateInAdmin {
    private String id;
    private String name;
    private String email;
    private int jobApplicationCount;
    
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
	public int getJobApplicationCount() {
		return jobApplicationCount;
	}
	public void setJobApplicationCount(int jobAplicationCount) {
		this.jobApplicationCount = jobAplicationCount;
	}
    
}
