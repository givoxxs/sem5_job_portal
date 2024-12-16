package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.bean.Job;

import utils.DBConnect;

public class JobDAO {
	
	//Crete instance
	private static JobDAO instance;	
	private JobDAO() {
	}
	public static JobDAO getInstance() {
		if (instance == null) {
			instance = new JobDAO();
		}
		return instance;
	}
	
	//Create id
		public String createId(String str) {
			String rs = str.substring(0, 2).toUpperCase();
			// Lấy thời gian hiện tại
	        LocalDateTime now = LocalDateTime.now();
	        
	        // Định dạng thời gian theo mẫu
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedDate = now.format(formatter);
	        rs += formattedDate;
			return rs;
		}
	
	//Change result to job
	public Job mapResultToJob(ResultSet rs) throws SQLException {
		Job job = new Job();
		job.setId(rs.getString("id"));
		job.setEmployerName(rs.getString("name"));
		job.setTitle(rs.getString("title"));
		job.setDescription(rs.getString("description"));
		job.setSalaryRange(rs.getString("salary_range"));
		job.setLocation(rs.getString("location"));
		job.setJobType(rs.getString("job_type"));
		job.setExperience(rs.getString("experience"));
		job.setDatePost(rs.getTimestamp("date_post"));
		job.setAvailable(rs.getBoolean("is_available"));
		job.setSalaryRange(rs.getString("salary_range"));
		return job;
	}
	
	//Select job
	public List<Job> getJOB( List<String> params, String query) {
        List<Job> jobs = new ArrayList<Job>();
        ResultSet rs = DBConnect.getInstance().selectSQL(params, query);
        try {
            
            while (rs.next()) {
                Job job = mapResultToJob(rs);
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }
	
	//Select all job avaible
	public List<Job> getAllAvailableJobs() {
        String query = "SELECT j.*, s.salary_range, e.name "
        		+ "FROM job j "
        		+ "JOIN salary_range s ON j.salary_range_id = s.id "
        		+ "JOIN employer_profile e ON j.employer_id = e.id  "
        		+ "WHERE j.is_available = 1";
        return getJOB(null, query);
    }
	
	//Select all job
	public List<Job> getAllJobs() {
		String query = "SELECT j.*, s.salary_range, e.name "
				+ "FROM job j "
				+ "JOIN salary_range s ON j.salary_range_id = s.id "
				+ "JOIN employer_profile e ON j.employer_id = e.id";
		return getJOB(null, query);
	}
	
	//Select job by enployer_id theo trang
	public List<Job> getJobsByEmployerId(String employerId, int offset, int noOfRecords ) {
		String query = "SELECT j.*, s.salary_range, e.name "
				+ "FROM job j "
				+ "JOIN salary_range s ON j.salary_range_id = s.id "
				+ "JOIN employer_profile e ON j.employer_id = e.id "
				+ "WHERE j.employer_id = ?"
				+ "LIMIT "+offset+","+noOfRecords;
		List<String> params = new ArrayList<String>();
		params.add(employerId);
		return getJOB(params, query);
	}
	
	//Select job by enployer_id
		public List<Job> getJobsByEmployerId(String employerId) {
			String query = "SELECT j.*, s.salary_range, e.name "
					+ "FROM job j "
					+ "JOIN salary_range s ON j.salary_range_id = s.id "
					+ "JOIN employer_profile e ON j.employer_id = e.id "
					+ "WHERE j.employer_id = ?";
			List<String> params = new ArrayList<String>();
			params.add(employerId);
			
			
			return getJOB(params, query);
		}
	
	//Select job by id
	public Job getJobById(String id) {
		String query = "SELECT j.*, s.salary_range, e.name " + "FROM job j "
				+ "JOIN salary_range s ON j.salary_range_id = s.id "
				+ "JOIN employer_profile e ON j.employer_id = e.id " 
				+ "WHERE j.id = ?";
		List<String> params = new ArrayList<String>();
		params.add(id);
		List<Job> jobs = getJOB(params, query);
		if (jobs.size() > 0) {
			return jobs.get(0);
		}
		return null;
	}
	
	
	//Add job
	public boolean addJob(String employerId, String title, String description, String salaryRangeId, String location, String jobType, String experience) {
		String query = "INSERT INTO job (id, employer_id, title, description, salary_range_id, location, job_type, experience, date_post, is_available) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
		
		//Create time
		LocalDateTime now = LocalDateTime.now();
        
        // Định dạng thời gian theo mẫu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        
		List<String> params = new ArrayList<String>();
		params.add(createId(title));
		params.add(employerId);
		params.add(title);
		params.add(description);
		params.add(salaryRangeId);
		params.add(location);
		params.add(jobType);
		params.add(experience);
		params.add(formattedDate);

		return DBConnect.getInstance().dataSQL(params, query);
	}
	
	//Update job
	public boolean updateJob(String id, String title, String description, String salaryRangeId, String location,
			String jobType, String experience) {
		String query = "UPDATE job SET title = ?, description = ?, salary_range_id = ?, location = ?, job_type = ?, experience = ? WHERE id = ?";
		List<String> params = new ArrayList<String>();
		params.add(title);
		params.add(description);
		params.add(salaryRangeId);
		params.add(location);
		params.add(jobType);
		params.add(experience);
		params.add(id);
		return DBConnect.getInstance().dataSQL(params, query);
	}
	
	//Update avaible job
	public boolean updateJobAvailable(String id, boolean isAvailable) {
		String query = "UPDATE job SET is_available = ? WHERE id = ?";
		List<String> params = new ArrayList<String>();
		params.add(isAvailable ? "1" : "0");
		params.add(id);
		return DBConnect.getInstance().dataSQL(params, query);
	}	
	
	public List<Job> searchJobs(String jobName, String salaryRangeId, String jobType, String experience, String location, int page) {
        List<Job> jobs = new ArrayList<>();
        int offset = (page - 1) * 10; // 10 jobs per page

        String sql = "SELECT j.*, sr.salary_range " +
                "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id " +
        		"FROM employer_profile e ON j.employer_id = e.id " +
                "WHERE j.is_available = true ";

        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        if (jobName != null && !jobName.isEmpty()) {
            conditions.add("(j.title LIKE ? OR j.description LIKE ?)");
            parameters.add("%" + jobName + "%");
            parameters.add("%" + jobName + "%");
        }
        if (salaryRangeId != null && !salaryRangeId.isEmpty()) {
            conditions.add("j.salary_range_id = ?");
            parameters.add(salaryRangeId);
        }
        if (jobType != null && !jobType.isEmpty()) {
            conditions.add("j.job_type = ?");
            parameters.add(jobType);
        }
        if (experience != null && !experience.isEmpty()) {
            conditions.add("j.experience = ?");
            parameters.add(experience);
        }
        if (location != null && !location.isEmpty()) {
            conditions.add("j.location = ?");
            parameters.add(location);
        }

        if (!conditions.isEmpty()) {
            sql += "AND (" + String.join(" AND ", conditions) + ") ";
        }

        sql += "ORDER BY j.date_post DESC LIMIT 10 OFFSET ?";
        parameters.add(offset);
        

//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    jobs.add(mapResultToJob(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobs;
    }
	
	//search job od employer
	public List<Job> searchJobs(String emp_id, String jobName, String salaryRangeId, String jobType, String experience, String location, int offset, int noOfRecords) {
        List<Job> jobs = new ArrayList<>();

        String sql = "SELECT j.*, sr.salary_range, e.name " +
                "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id " +
        		"JOIN employer_profile e ON j.employer_id = e.id " +
                "WHERE j.employer_id = ?";

        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();
        parameters.add(emp_id);

        if (jobName != null && !jobName.isEmpty()) {
            conditions.add("(j.title LIKE ? OR j.description LIKE ?)");
            parameters.add("%" + jobName + "%");
            parameters.add("%" + jobName + "%");
        }
        if (salaryRangeId != null && !salaryRangeId.isEmpty()) {
            conditions.add("j.salary_range_id = ?");
            parameters.add(salaryRangeId);
        }
        if (jobType != null && !jobType.isEmpty()) {
            conditions.add("j.job_type = ?");
            parameters.add(jobType);
        }
        if (experience != null && !experience.isEmpty()) {
            conditions.add("j.experience = ?");
            parameters.add(experience);
        }
        if (location != null && !location.isEmpty()) {
            conditions.add("j.location = ?");
            parameters.add(location);
        }

        if (!conditions.isEmpty()) {
            sql += "AND (" + String.join(" AND ", conditions) + ") ";
        }

        sql += "ORDER BY j.date_post DESC ";
        if(offset != -1 && noOfRecords != -1) {
        	sql += "LIMIT " + offset + ", " + noOfRecords;
        }

        
        Connection connection = null;
		try {
			connection = DBConnect.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    jobs.add(mapResultToJob(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobs;
    }
}