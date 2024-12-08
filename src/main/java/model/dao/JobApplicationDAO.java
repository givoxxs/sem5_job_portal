package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.Job_Application;
import utils.DBConnect;

public class JobApplicationDAO {
	
	//Create a singleton pattern
	private static JobApplicationDAO instance;
	private JobApplicationDAO() {
		// TODO Auto-generated constructor stub
	}

	public static JobApplicationDAO getInstance() {
		if (instance == null) {
			instance = new JobApplicationDAO();
		}
		return instance;
	}
	
	//Chane result to job application
	public Job_Application mapResultToJobApplication(ResultSet rs) {
		Job_Application jobApplications = new Job_Application();
		try {
		jobApplications.setId(rs.getString("id"));
		jobApplications.setJob_id(rs.getString("job_id"));
		jobApplications.setName(rs.getString("name"));
		jobApplications.setEmail(rs.getString("email"));
		jobApplications.setPhone(rs.getString("phone"));
		jobApplications.setCv_url(rs.getString("cv_url"));
		jobApplications.setCandidate_id(rs.getString("candidate_id"));
		jobApplications.setStatus(rs.getString("status"));
		}catch (Exception e) {
            e.printStackTrace();
            }
		return jobApplications;
	}
	
	//Change result to list job application
	public List<Job_Application> mapResultToListJobApplication(ResultSet rs) {
		List<Job_Application> jobApplications = new ArrayList<Job_Application>();
		try {
			while(rs.next()) {
				jobApplications.add(mapResultToJobApplication(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobApplications;
	}
	
	//Get list job application
	public List<Job_Application> getListJobApplications(List<String> params, String query) {
		ResultSet rs = DBConnect.getInstance().selectSQL(params, query);
		return mapResultToListJobApplication(rs);
	}
	
	//Get job application by job id
	public List<Job_Application> getJobApplicationByJob_Id(String job_id) {
		String query = "SELECT * FROM job_application WHERE job_id = ?";
		List<String> params = new ArrayList<>();
		params.add(job_id);
		return getListJobApplications(params, query);
	}
	
	//Get one job application by id
	public Job_Application getJobApplicationById(String id) {
		String query = "SELECT * FROM job_application WHERE id = ?";
		List<String> params = new ArrayList<>();
		params.add(id);
		ResultSet rs = DBConnect.getInstance().selectSQL(params, query);
		return mapResultToJobApplication(rs);
	}
	
	//Update job application
	public boolean updateJobApplication(String id, String status) {
		String query = "UPDATE job_application SET status = ? WHERE id = ?";
		List<String> params = new ArrayList<>();
		params.add(status);
		params.add(id);
		boolean rs = DBConnect.getInstance().dataSQL(params, query);
		sendNotification(id, status);
		return rs;
	}
	
	
	//Send notification by Gmail
	public void sendNotification(String id, String status) {
		// Send email
		Job_Application jobApplication = getJobApplicationById(id);
		String to = jobApplication.getEmail();
		String subject = "Job Application Status";
		String body = "Your job application has been " + status;
		
		// Send email
	}
	
	
	public static void main(String[] args) {

        List<Job_Application> jobApplications = JobApplicationDAO.getInstance().getJobApplicationByJob_Id("JO2024-12-08 17:36:33");
        for (Job_Application jobApplication : jobApplications) {
        	System.out.println(jobApplication.getId());
            System.out.println(jobApplication.getName());
            System.out.println(jobApplication.getEmail());
            System.out.println(jobApplication.getPhone());
            System.out.println(jobApplication.getCv_url());
            System.out.println(jobApplication.getCandidate_id());
            System.out.println(jobApplication.getStatus());
            System.out.println("\n");
        }
	}
	
}