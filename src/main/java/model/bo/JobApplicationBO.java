package model.bo;

import java.util.List;

import model.bean.Job_Application;
import model.dao.JobApplicationDAO;

public class JobApplicationBO {
	
	//create instance
	private static JobApplicationBO instance;

	private JobApplicationBO() {
		// TODO Auto-generated constructor stub
	}
	
	public static JobApplicationBO getInstance() {
		if (instance == null) {
			instance = new JobApplicationBO();
		}
		return instance;
	}
	
	//get list job application
	public List<Job_Application> getListJobApplications(List<String> params, String query) {
		return JobApplicationDAO.getInstance().getListJobApplications(params, query);
	}
	
	//get one job application by id
	public Job_Application getJobApplicationById(String id) {
		return JobApplicationDAO.getInstance().getJobApplicationById(id);
	}
	
	//Get job application by job id
	public List<Job_Application> getJobApplicationByJobId(String jobId) {
		return JobApplicationDAO.getInstance().getJobApplicationByJob_Id(jobId);
	}
	
	//update job application
	public boolean updateJobApplication(String id, String status) {
		return JobApplicationDAO.getInstance().updateJobApplication(id, status);
	}
}