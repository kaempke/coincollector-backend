package de.hypoport.caching.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	
	private Statement statement;

	public DbConnection(String db_connect_string, String db_userid, String db_password) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager.getConnection(db_connect_string, db_userid, db_password);
			System.out.println("connected");
			statement = conn.createStatement();
//			String queryString = "select * from f_premien('Daniel','Salazar Drabert','daniel.salazar@hypoport.de',200)";
//			ResultSet rs = statement.executeQuery(queryString);
//			while (rs.next()) {
//				System.out.println(rs.getString(1));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void dbConnect(String db_connect_string, String db_userid, String db_password) {
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			Connection conn = DriverManager.getConnection(db_connect_string, db_userid, db_password);
//			System.out.println("connected");
//			Statement statement = conn.createStatement();
//			String queryString = "select * from f_premien('Daniel','Salazar Drabert','daniel.salazar@hypoport.de',200)";
//			ResultSet rs = statement.executeQuery(queryString);
//			while (rs.next()) {
//				System.out.println(rs.getString(1));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public ResultSet execute(String queryString) {
		try {
			return statement.executeQuery(queryString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
