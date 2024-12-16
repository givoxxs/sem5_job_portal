package model.bo;

import java.util.List;

import model.bean.Job;
import model.dao.JobDAO;

public class JobBO {
	// instance
	private static JobBO instance;
	private JobBO() {

	}
	public static JobBO getInstance() {
		if (instance == null) {
			instance = new JobBO();
		}
		return instance;
	}
	
	//select all jobs available
	public List<Job> getAllAvailableJobs() {
		return JobDAO.getInstance().getAllAvailableJobs();
	}
	
	//select all job
	public List<Job> getAllJobs() {
		return JobDAO.getInstance().getAllJobs();
	}
	
	//select job by employer id
	public List<Job> getJobByEmployerId(String id) {
		return JobDAO.getInstance().getJobsByEmployerId(id);
	}
	
	//select job by employer id
	public List<Job> getJobByEmployerId(String id, int offset, int noOfRecords) {
		return JobDAO.getInstance().getJobsByEmployerId(id, offset, noOfRecords);
	}
	
	//select job by id
	public Job getJobById(String id) {
		return JobDAO.getInstance().getJobById(id);
	}
	
	//add job
	public boolean addJob(String employerId, String title, String description, String salaryRangeId, String location, String jobType, String experience) {
		return JobDAO.getInstance().addJob(employerId, title, description, salaryRangeId, location, jobType, experience);
	}
	
	//update job
	public boolean updateJob(String id, String title, String description, String salaryRangeId, String location,
			String jobType, String experience) {
		return JobDAO.getInstance().updateJob(id, title, description, salaryRangeId, location, jobType, experience);
	}
	
	//Update available job
	public boolean updateAvailableJob(String id, String status) {
		boolean result;
		if (status.equals("true")) {
			result = true;
		} else {
			result = false;
		}
		return JobDAO.getInstance().updateJobAvailable(id, result);
	}
	
	//search job
	public List<Job> searchJobs(String emp_id, String jobName, String salaryRangeId, String jobType, String experience,
			String location, int offset, int noOfRecords) {
		return JobDAO.getInstance().searchJobs(emp_id, jobName, salaryRangeId, jobType, experience, location, offset, noOfRecords);
	}
	

	
	
}