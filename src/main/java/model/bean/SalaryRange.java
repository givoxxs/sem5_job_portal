package model.bean;

public class SalaryRange {
	String id;
	String salaryRange;
	
	public SalaryRange() {
		
	}
	
	public SalaryRange(String id, String salaryRange) {
		this.id = id;
		this.salaryRange = salaryRange;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSalaryRange() {
		return salaryRange;
	}

	public void setSalaryRange(String salaryRange) {
		this.salaryRange = salaryRange;
	}	
}