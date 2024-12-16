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
	
	public List<Job> getTopLatestJobs(int limit) {
		System.out.println("JobBO - getTopLatestJobs");
		// in ra length
		System.out.println("limit: " + limit);
		// in ra danh sách các công việc mới nhất
		List<Job> jobs = jobDAO.getTopLatestJobs(limit);
		System.out.println("jobs: " + jobs);
		return jobDAO.getTopLatestJobs(limit);
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



	public List<Job> getJobByEmployerId(String employer_id) {
		// TODO Auto-generated method stub
        return jobDAO.getJobByEmployerId(employer_id);
	}

	public boolean updateJob(String jobid, String title, String description, String salaryRangeId, String location,
			String jobType, String experience) {
		// TODO Auto-generated method stub
		return jobDAO.updateJob(jobid, title, description, salaryRangeId, location, jobType, experience);
	}

	public boolean updateAvailableJob(String jobid, String status) {
		// TODO Auto-generated method stub
		return jobDAO.updateAvailableJob(jobid, status);
	}

	//search job
	public List<Job> searchJobs(String emp_id, String jobName, String salaryRangeId, String jobType, String experience,
			String location, int offset, int noOfRecords) {
		return JobDAO.getInstance().searchJobs(emp_id, jobName, salaryRangeId, jobType, experience, location, offset, noOfRecords);
	}
	
	public List<Job> searchJobsAll(String jobName, String salaryRangeId, String jobType, String experience,
		    String location) {
        // TODO Auto-generated method stub
        return jobDAO.searchJobsAll(jobName, salaryRangeId, jobType, experience, location);
	}
}