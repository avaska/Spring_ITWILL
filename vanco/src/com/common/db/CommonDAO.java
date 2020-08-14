package com.common.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
 * CommonDAO Î™®ÎëêÍ∞? Í≥µÌÜµ?úºÎ°? ?Ç¨?ö©?ïò?äî DAO (?ã±Í∏??Ü§ ?å®?Ñ¥ Íµ¨ÌòÑ)
 */

public class CommonDAO {

	private final String driverName = "oracle.jdbc.driver.OracleDriver";
	private final String url = "jdbc:oracle:thin:localhost:1521:XE";
	
	private final String db_id = "board";
	private final String db_pw = "12345";
	
	private Connection con = null;
	private Statement stmt = null;
	
	public Statement openConnection(){
		try {
			
			Class.forName(driverName);
			con = DriverManager.getConnection(url,db_id,db_pw);
			stmt = con.createStatement();
			
		} catch (Exception e) {
			
			System.out.println("Oracle Database Connection Problem");
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		
		return stmt;
		
	}
	
	public void closeConnection() {
		try {
			
			if(!con.isClosed()) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
