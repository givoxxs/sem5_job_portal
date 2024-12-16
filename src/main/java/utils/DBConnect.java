package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBConnect {
	//Create ínstance of connection
	private DBConnect() {
	}
	private static DBConnect instance;

	public static DBConnect getInstance() {
		if (instance == null) {
			instance = new DBConnect();
		}
		try {
			getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;
	}
	
	
	private static final String URL = "jdbc:mysql://localhost:3306/job_portal";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;
	
    public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (ClassNotFoundException e) {
				System.err.println("JDBC Driver not found: " + e.getMessage());
	            throw new SQLException("JDBC Driver not found", e);
			} catch (SQLException e) {
	            System.err.println("Failed to connect to the database: " + e.getMessage());
	            throw e;
	        }
		}
		
		return connection;
    }
    
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Log the error appropriately
            }
        }
    }
    
  //update database
  	public	boolean dataSQL(List<String> params, String query) {
  		    try {
  		        PreparedStatement pr = connection.prepareStatement(query);

  		        // Gán các tham số từ danh sách vào PreparedStatement
  		        for (int i = 0; i < params.size(); i++) {
  		            pr.setString(i + 1, params.get(i)); // Tham số PreparedStatement bắt đầu từ 1
  		        }

  		        // Thực hiện câu lệnh query
  		        int rowsAffected = pr.executeUpdate();
  		        
  		        return rowsAffected > 0; // Trả về true nếu có ít nhất một hàng bị ảnh hưởng
  		    } catch (SQLException e) {
  		        e.printStackTrace();
  		        return false;
  		    }
  		}
  	
  //Select database
  	public ResultSet selectSQL(List<String> params, String query) {
  		ResultSet rs = null;
  		try {
  			PreparedStatement pr = connection.prepareStatement(query);
  			if (params != null) {
  				for (int i = 0; i < params.size(); i++) {
  					pr.setString(i + 1, params.get(i));
  				}
  			}
  			rs = pr.executeQuery();
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}
  		return rs;
  	}
}