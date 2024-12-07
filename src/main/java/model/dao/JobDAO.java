package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.Job;

import utils.DBConnect;

public class JobDAO {
	private static JobDAO instance;
	private Connection conn;
	
	// get all available jobs
//	private static final String SQL_GET_ALL_AVAILABLE_JOBS = "SELECT * FROM job WHERE is_available = 1";
    private static final String SQL_GET_ALL_AVAILABLE_JOBS = "SELECT j.*, sr.salary_range " +
            "FROM job j JOIN salary_range sr ON j.salary_range_id = sr.id " +
            "WHERE j.is_available = true";
	
	private JobDAO() {
		try {
			conn = DBConnect.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static JobDAO getInstance() {
		if (instance == null) {
			instance = new JobDAO();
		}
		return instance;
	}
	
	public Job mapResultToJob(ResultSet rs) throws SQLException {
		Job job = new Job();
		job.setId(rs.getString("id"));
		job.setEmployerId(rs.getString("employer_id"));
		job.setTitle(rs.getString("title"));
		job.setDescription(rs.getString("description"));
		job.setSalaryRangeId(rs.getString("salary_range_id"));
		job.setLocation(rs.getString("location"));
		job.setJobType(rs.getString("job_type"));
		job.setExperience(rs.getString("experience"));
		job.setDatePost(rs.getTimestamp("date_post"));
		job.setAvailable(rs.getBoolean("is_available"));
		job.setSalaryRange(rs.getString("salary_range"));
		return job;
	}
	
	public List<Job> getAllAvailableJobs() {
        List<Job> jobs = new ArrayList<Job>();
        try {
            PreparedStatement ps = conn.prepareStatement(SQL_GET_ALL_AVAILABLE_JOBS);
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