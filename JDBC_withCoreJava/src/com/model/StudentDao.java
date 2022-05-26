package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentDao {
	
	static Connection con = ConnectionProvider.createConnection();

	public static boolean insertStudentToDB(Students std) {
		
		boolean status = false;
		try {

			String q = "insert into students(name, phone, city) values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(q);
			ps.setString(1, std.getStudentName());
			ps.setString(2, std.getStudentPhone());
			ps.setString(3, std.getStudentCity());
			
			ps.executeUpdate();
			ps.close();
			status = true; 

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static boolean deleteStudent(int id) {
		boolean status = false;
		
		try {
			
			String q = "delete from students where id = ?";
			PreparedStatement ps = con.prepareStatement(q);
			ps.setInt(1, id);
			
			ps.executeUpdate();
			ps.close();
			status = true; 
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static boolean showAllStudennts() {
		boolean status = false;
		
		try {
			
			String q = "select * from students";
			Statement st = con.createStatement();
			ResultSet set = st.executeQuery(q);
			
			while(set.next()) {
				int id = set.getInt(1);
				String name = set.getString(2);
				String phone = set.getString(3);
				String city = set.getString(4);
				
				System.out.println("ID :"+id);
				System.out.println("Name :"+name);
				System.out.println("Phone :"+phone);
				System.out.println("City :"+city);
				System.out.println("-------------------------------");
			}
			
			st.close();
			set.close();
			status = true; 
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return status;
	}
}
