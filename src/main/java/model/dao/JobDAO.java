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
	public Connection conn;
    // gộp 3 bảng job, salary_range, employer_profile để lấy thông tin của job
	private static final String SQL_GET_ALl_AVAILABLE_JOBS = "SELECT j.*, sr.salary_range, e.name "
			+ "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id "
			+ "JOIN employer_profile e ON j.employer_id = e.id " + "WHERE j.is_available = true";
	private static final String SQL_GET_TOP_LATEST_JOBS = "SELECT j.*, sr.salary_range, e.name "
			+ "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id "
			+ "JOIN employer_profile e ON j.employer_id = e.id "
			+ "WHERE j.is_available = true ORDER BY j.date_post DESC LIMIT ?";
	private static final String SQL_GET_RANDOM_JOBS = "SELECT j.*, sr.salary_range, e.name "
			+ "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id "
			+ "JOIN employer_profile e ON j.employer_id = e.id "
			+ "WHERE j.is_available = true ORDER BY RAND() LIMIT ?";
//	private static final String SQL_SEARCH_JOB = "SELECT j.*, sr.salary_range, e.name "
//			+ "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id "
//			+ "JOIN employer_profile e ON j.employer_id = e.id " 
//			+ "WHERE j.is_available = true "
//				+ "AND (j.title LIKE ? OR j.description LIKE ? " 
//				+ "OR j.salary_range_id = ? OR j.job_type = ? "
//				+ "OR j.experience = ? OR j.location = ?)";
	
	private static final String SQL_GET_JOB_BY_ID = "SELECT j.*, sr.salary_range, e.name "
			+ "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id "
			+ "JOIN employer_profile e ON j.employer_id = e.id " 
			+ "WHERE j.id = ?";
	
	private static final String SQL_CREATE_JOB = "INSERT INTO job (id, employer_id, title, "
			+ "description, salary_range_id, location, job_type, experience) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_GET_JOB_BY_EMPLOYER_ID = "SELECT j.*, sr.salary_range "
			+ "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id " + "WHERE j.employer_id = ?";
    
	private static final String SQL_UPDATE_JOB = "UPDATE job SET title = ?, description = ?, salary_range_id = ?, location = ?, job_type = ?, experience = ? WHERE id = ?";
	
	private static final String SQL_UPDATE_AVAILABLE_JOB = "UPDATE job SET is_available = ? WHERE id = ?";
	
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
			String rs;
			if(str.length() < 2) {
				rs = str.toUpperCase();
			}else {
				rs = str.substring(0, 2).toUpperCase();
			}
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
		job.setTitle(rs.getString("title"));
		job.setDescription(rs.getString("description"));
        job.setEmployerName(rs.getString("name"));
		job.setLocation(rs.getString("location"));
		job.setJobType(rs.getString("job_type"));
		job.setExperience(rs.getString("experience"));
		job.setDatePost(rs.getTimestamp("date_post"));
		job.setAvailable(rs.getBoolean("is_available"));
		job.setSalaryRange(rs.getString("salary_range"));
		try {		
	        job.setEmployerName(rs.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	public List<Job> getTopLatestJobs(int top) {
        List<Job> jobs = new ArrayList<Job>();
        try (Connection conn = DBConnect.getConnection()) {
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
	

	//Update avaible job
	public boolean updateJobAvailable(String id, boolean isAvailable) {
		
		String query = "UPDATE job SET is_available = ? WHERE id = ?";
		List<String> params = new ArrayList<String>();
		params.add(isAvailable ? "1" : "0");
		params.add(id);
		return DBConnect.getInstance().dataSQL(params, query);
	}	

	public List<Job> getRandomJobs(int number) {
		List<Job> jobs = new ArrayList<Job>();
//		try {
		try	(Connection conn = DBConnect.getConnection()){
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
            parameters.add("%" + location + "%");
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
        

//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
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

	public List<Job> getJobByEmployerId(String employer_id) {
		List<Job> jobs = new ArrayList<Job>();
//		try {
		try (Connection conn = DBConnect.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(SQL_GET_JOB_BY_EMPLOYER_ID);
			ps.setString(1, employer_id);
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
	public boolean updateJob(String jobid, String title, String description, String salaryRangeId, String location,
			String jobType, String experience) {
		boolean rs = false;
//		try {
		try (Connection conn = DBConnect.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_JOB);
			ps.setString(1, title);
			ps.setString(2, description);
			ps.setString(3, salaryRangeId);
			ps.setString(4, location);
			ps.setString(5, jobType);
			ps.setString(6, experience);
			ps.setString(7, jobid);
			rs = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean updateAvailableJob(String jobid, String status) {
		boolean rs = false;
//		try {
		try (Connection conn = DBConnect.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_AVAILABLE_JOB);
			ps.setString(1, status);
			ps.setString(2, jobid);
			rs = ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public List<Job> searchJobsAll(String jobName, String salaryRangeId, String jobType, String experience,
			String location) {
		List<Job> jobs = new ArrayList<Job>();
		String sql = "SELECT j.*, sr.salary_range, e.name "
				+ "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id "
				+ "JOIN employer_profile e ON j.employer_id = e.id " + "WHERE j.is_available = true ";
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
//			conditions.add("j.location = ?");
			conditions.add("j.location LIKE ?");
			parameters.add("%" + location + "%");
		}
		if (!conditions.isEmpty()) {
			sql += "AND (" + String.join(" AND ", conditions) + ") ";
		}
		//		try {
		try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			for (int i = 0; i < parameters.size(); i++) {
				ps.setObject(i + 1, parameters.get(i));
			}
			System.out.println("ps searchJobsAll" + ps);
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
}