package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.connection.ConnectionProvider;
import com.model.User;

public class UserDAO {

	private static final String Insert_User = "insert into users(name, email, country) values(?,?,?)";

	private static final String Select_User_ByID = "select * from users where id=?";
	private static final String Select_All_Users = "select * from users";
	private static final String Delete_User = "delete from users where id=?";
	private static final String Update_User = "update users set name=?, email=?, password=? where id=?";

	static Connection con = ConnectionProvider.createConnection();

	public static void insertUser(User user) {

		try {

			PreparedStatement ps = con.prepareStatement(Insert_User);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getCountry());

			ps.executeUpdate();
			ps.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean updateUser(User user) {
		boolean rowUpdated = false;
		try {

			PreparedStatement ps = con.prepareStatement(Update_User);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getCountry());

			rowUpdated = ps.executeUpdate() >0 ;
			ps.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

	public static boolean deleteUser(int id) {
		
		boolean rowDeleted = false;
		try {

			PreparedStatement ps = con.prepareStatement(Delete_User);
			ps.setInt(1, id);

			rowDeleted = ps.executeUpdate() >0 ;
			ps.close();

		}
		catch(Exception e){
			e.printStackTrace();
		}
		return rowDeleted;
	}

	public static User selectUserByID(int id) {
		
		User user = null;
		try {

			PreparedStatement ps = con.prepareStatement(Select_User_ByID);
			ps.setInt(1, id);

			ResultSet set = ps.executeQuery();
			
			while(set.next()) {
				String name = set.getString(2);
				String email = set.getString(3);
				String country = set.getString(4);
				user = new User(id, name, email, country);
			}
			ps.close();

		}
		catch(Exception e){
			e.printStackTrace();
		}

		return user;
	}

	public static List<User> showAllUsers() {
		
		List<User> users = new ArrayList<User>(); 
		try {

			Statement st = con.createStatement();
			ResultSet set = st.executeQuery(Select_All_Users);

			while(set.next()) {
				int id = set.getInt(1);
				String name = set.getString(2);
				String email = set.getString(3);
				String country = set.getString(4);
				users.add(new User(id, name, email, country));
			}

			st.close();
			set.close();

		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return users;
	}

}