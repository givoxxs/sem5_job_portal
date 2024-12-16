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
	private static JobDAO instance;
	private Connection conn;
	
	// get all available jobs
    private static final String SQL_GET_ALL_AVAILABLE_JOBS = "SELECT j.*, sr.salary_range " +
            "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id " +
            "WHERE j.is_available = true";
//	private static final String SQL_SEARCH_JOB = "SELECT j.*, sr.salary_range "
//			+ "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id "
//			+ "WHERE j.is_available = true AND (j.title LIKE ? OR j.description LIKE ?)";
	private static final String SQL_GET_TOP_LATEST_JOBS = "SELECT j.*, sr.salary_range "
			+ "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id "
			+ "WHERE j.is_available = true ORDER BY j.date_post DESC LIMIT ?";
	private static final String SQL_GET_RANDOM_JOBS = "SELECT j.*, sr.salary_range " +
            "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id " +
            "WHERE j.is_available = true ORDER BY RAND() LIMIT ?";
	private static final String SQL_SEARCH_JOB = "SELECT j.*, sr.salary_range "
			+ "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id "
			+ "WHERE j.is_available = true "
				+ "AND (j.title LIKE ? OR j.description LIKE ? "
				+ "OR j.salary_range_id = ? OR j.job_type = ? "
				+ "OR j.experience = ? OR j.location = ?)";
	
	private static final String SQL_GET_JOB_BY_ID = "SELECT j.*, sr.salary_range "
            + "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id "
            + "WHERE j.id = ?";
	
    
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
	
	public Job getJobById(String id) {
        Job job = null;
        try {
            PreparedStatement ps = conn.prepareStatement(SQL_GET_JOB_BY_ID);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                job = mapResultToJob(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job;
	}
	
	public List<Job> getTopLatestJobs(int top) {
        List<Job> jobs = new ArrayList<Job>();
        try {
            PreparedStatement ps = conn.prepareStatement(SQL_GET_TOP_LATEST_JOBS);
            ps.setInt(1, top);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Job job = mapResultToJob(rs);
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
	}
	
	public List<Job> getRandomJobs(int number) {
		List<Job> jobs = new ArrayList<Job>();
		try {
			PreparedStatement ps = conn.prepareStatement(SQL_GET_RANDOM_JOBS);
			ps.setInt(1, number);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Job job = mapResultToJob(rs);
				jobs.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jobs;
	}
	
	public List<Job> searchJobs(String jobName, String salaryRangeId, String jobType, String experience, String location, int page) {
        List<Job> jobs = new ArrayList<>();
        int offset = (page - 1) * 10; // 10 jobs per page

        String sql = "SELECT j.*, sr.salary_range " +
                "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id " +
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
        

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
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
	
	public int getTotalPages(String jobName, String salaryRangeId, String jobType, String experience, String location) {
        int totalJobs = 0;
        String sql = "SELECT COUNT(*) FROM job j WHERE j.is_available = true ";
        List<String> conditions = new ArrayList<>(); // Tạo list conditions
        List<Object> parameters = new ArrayList<>();// Tạo list parameters

        // Tương tự như phương thức searchJobs, xây dựng câu sql query động
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
        

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Set parameters cho PreparedStatement
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalJobs = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (int) Math.ceil((double) totalJobs / 10);
    }
}