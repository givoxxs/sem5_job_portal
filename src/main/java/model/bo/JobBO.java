package model.bo;

import java.util.List;

import model.bean.Job;
import model.dao.JobDAO;

public class JobBO {
	// instance
	private static JobBO instance;
	private JobDAO jobDAO = JobDAO.getInstance();
	private JobBO() {

	}

	public static JobBO getInstance() {
		if (instance == null) {
			instance = new JobBO();
		}
		return instance;
	}
	
	public List<Job> getAllAvailableJobs() {
		return jobDAO.getAllAvailableJobs();
	}
}