package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.SalaryRange;
import utils.DBConnect;

public class SalaryRangeDAO {
    // SQL query
    private static final String SQL_GET_ALL_AVAILABLE_SALARY_RANGES = "SELECT * FROM salary_range";
    private static volatile SalaryRangeDAO instance;

    private SalaryRangeDAO() {
        // Private constructor to prevent instantiation
    }

    public static SalaryRangeDAO getInstance() {
        if (instance == null) {
            synchronized (SalaryRangeDAO.class) {
                if (instance == null) {
                    instance = new SalaryRangeDAO();
                }
            }
        }
        return instance;
    }

    public List<SalaryRange> getAllAvailableSalaryRanges() {
        List<SalaryRange> salaryRanges = new ArrayList<>();
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_GET_ALL_AVAILABLE_SALARY_RANGES);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SalaryRange salaryRange = new SalaryRange();
                salaryRange.setId(rs.getString("id"));
                salaryRange.setSalaryRange(rs.getString("salary_range"));
                salaryRanges.add(salaryRange);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching salary ranges: " + e.getMessage());
            throw new RuntimeException("Database error occurred while fetching salary ranges.", e);
        }
        return salaryRanges;
    }
}
