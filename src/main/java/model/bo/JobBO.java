package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.bean.Job;
import model.dao.JobDAO;

public class JobBO {
	// instance
	public static JobBO instance;
	public JobDAO jobDAO = JobDAO.getInstance();
	public JobBO() {}

	public static JobBO getInstance() {
		if (instance == null) {
			instance = new JobBO();
		}
		return instance;
	}
	
	//select all jobs available
	public List<Job> getAllAvailableJobs() {
		return jobDAO.getAllAvailableJobs();
	}
	
	public Job getJobById(String jobId) {
		return jobDAO.getJobById(jobId);
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

	public List<Job> getTopLatestJobs(int limit) {
		return jobDAO.getTopLatestJobs(limit);
	}
	
	public List<Job> getRandomJobs(int num) {
		return jobDAO.getRandomJobs(num);
	}
	
	public List<String> getDistinctLocations() {
	    // Khởi tạo danh sách các địa điểm cứng định
	    ArrayList<String> locations = new ArrayList<String>();
	    locations.add("Hà Nội");
	    locations.add("Hồ Chí Minh");
	    locations.add("Đà Nẵng");
	    locations.add("Khác");

	    return locations;
	}

    public List<Job> searchJobs(String jobName, String salaryRangeId, String jobType, String experience, String location, int page) {
        return jobDAO.searchJobs(jobName, salaryRangeId, jobType, experience, location, page);
    }

    public int getTotalPages(String jobName, String salaryRangeId, String jobType, String experience, String location) {
        return jobDAO.getTotalPages(jobName, salaryRangeId, jobType, experience, location);
    }
}