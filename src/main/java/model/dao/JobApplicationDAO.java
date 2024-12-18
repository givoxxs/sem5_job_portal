package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.Job;
import model.bean.Job_Application;
import utils.DBConnect;
import utils.EmailUtils;

public class JobApplicationDAO {
	private static final String SQL_ALL_JOBS_BY_CANIDATE = "SELECT * FROM job_application WHERE candidate_id = ?";
	private static final String SQL_INSERT_JOB_APPLICATION = "INSERT INTO job_application (id, name, email, phone, job_id, cv_url, candidate_id, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
	
	//Get job application by job id
		public List<Job_Application> getJobApplicationByJob_Id(String job_id, int offset, int noOfRecords) {
			String query = "SELECT * FROM job_application WHERE job_id = ?"
					+ " LIMIT " + offset + ", " + noOfRecords;
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
		Job_Application jobApplication = null;
		try {
			while(rs.next()) {
				jobApplication = mapResultToJobApplication(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobApplication;
	}
	
	//Update job application
	public boolean updateStatusJobApplication(String id, String status) {
		String query = "UPDATE job_application SET status = ? WHERE id = ?";
		List<String> params = new ArrayList<>();
		params.add(status);
		params.add(id);
		boolean rs = DBConnect.getInstance().dataSQL(params, query);
		sendNotification(id, status);
		return rs;
	}
	
	//Search Job_Application by Name
		public List<Job_Application> searchJobApplicationByName(String jobid,String name) {
			String query = "SELECT * FROM job_application WHERE job_id = ? AND BINARY name LIKE ?";
			List<String> params = new ArrayList<>();
			params.add(jobid);
			params.add("%" + name + "%");
			return getListJobApplications(params, query);
		}
	
	//Search Job_Application by Name
	public List<Job_Application> searchJobApplicationByName(String jobid,String name,   int offset, int noOfRecords) {
		String query = "SELECT * FROM job_application WHERE job_id = ? AND BINARY name LIKE ?"
				+ " LIMIT " + offset + ", " + noOfRecords;
		List<String> params = new ArrayList<>();
		params.add(jobid);
		params.add("%" + name + "%");
		return getListJobApplications(params, query);
	}
	
	
	//Send notification by Gmail
	public void sendNotification(String id, String status) {
		// Send email
		Job_Application jobApplication = getJobApplicationById(id);
		Job job = JobDAO.getInstance().getJobById(jobApplication.getJob_id());
		String to = jobApplication.getEmail();
		String subject = "Notify Job Application Status";
		String body = "Kính gửi " + jobApplication.getName() + ",<br><br>"
	            + "Chúng tôi trân trọng cảm ơn bạn đã quan tâm và gửi hồ sơ ứng tuyển đến công ty chúng tôi. "
	            + "Dưới đây là thông tin về vị trí mà bạn đã ứng tuyển:<br><br>"
	            + "<strong>JOB ID:</strong> " + jobApplication.getJob_id() + "<br>"
	            + "<strong>JOB:</strong> " + job.getTitle() + "<br><br>"
	            + "Trạng thái hiện tại của hồ sơ: <strong>" + status + "</strong>.<br><br>"
	            + "Nếu có bất kỳ thắc mắc nào, xin vui lòng liên hệ với chúng tôi qua email này hoặc số điện thoại hotline.<br><br>"
	            + "Chúng tôi hy vọng sẽ có cơ hội hợp tác với bạn trong tương lai.<br><br>"
	            + "Trân trọng,<br>"
	            + "<strong>Phòng Nhân Sự</strong><br>"
	            + "Nhà tuyển dụng:" + job.getEmployerName() +" <br>";

		// Send email
		EmailUtils.getInstance().sendEmail(to, subject, body);
	}
	
	public List<Job_Application> getJobApplicationByCandidateId(String candidateId) {
		List<String> params = new ArrayList<>();
		params.add(candidateId);
		ResultSet rs = DBConnect.getInstance().selectSQL(params, SQL_ALL_JOBS_BY_CANIDATE);
		return mapResultToListJobApplication(rs);
	}
	
	public boolean insertJobApplication(Job_Application jobApplication) {
		List<String> params = new ArrayList<>();
		params.add(jobApplication.getId());
		params.add(jobApplication.getName());
		params.add(jobApplication.getEmail());
		params.add(jobApplication.getPhone());
		params.add(jobApplication.getJob_id());
		params.add(jobApplication.getCv_url());
		params.add(jobApplication.getCandidate_id());
		params.add(jobApplication.getStatus());
		System.out.println("params: " + params);
		return DBConnect.getInstance().dataSQL(params, SQL_INSERT_JOB_APPLICATION);
	}
	
	public static void main(String[] args) {
		
		//Test send notification
		JobApplicationDAO.getInstance().updateStatusJobApplication("JA02","Accept");
		
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