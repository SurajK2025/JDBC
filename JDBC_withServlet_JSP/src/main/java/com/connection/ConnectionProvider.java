package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	public static Connection createConnection() {
		String user="root", password = "FR}hF.3!", url = "jdbc:mysql://localhost:3306/student_manage";
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
