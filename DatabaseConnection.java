package com.highradius;
import java.sql.*;  

public class DatabaseConnection {
	public static Connection createConnect() 
	{
		String dbDriver = "com.mysql.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:3306/";
		String dbName = "Invoice";
		String dbUsername = "root";
		String dbPass = "abcd";
		Connection con = null;
		try
		{  
			try {
				Class.forName(dbDriver);
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			con = DriverManager.getConnection(dbURL+dbName, dbUsername, dbPass);
			
			Statement stmt=con.createStatement();  
			String query = "SELECT sl_no, business_code, cust_number, name_customer, clear_date, buisness_year, doc_id, posting_date, "
					+ "document_create_date, document_create_date.1, due_in_date, invoice_currency, document_type, "
					+ "posting_id, area_business, total_open_amount, baseline_create_date, cust_payement_terms, invoice_id, "
					+ "isOpen, is_deleted FROM model";
			
			ResultSet rs=stmt.executeQuery(query);
			rs.next();
			
			System.out.println(rs.getString("sl_no"));
			System.out.println(rs.getString("business_code"));
			System.out.println(rs.getString("cust_number"));
			System.out.println(rs.getString("name_customer"));
			System.out.println(rs.getFloat("clear_date"));
			System.out.println(rs.getString("buisness_year"));
			System.out.println(rs.getString("doc_id"));
			System.out.println(rs.getString("posting_date"));
			System.out.println(rs.getString("document_create_date"));
			System.out.println(rs.getString("document_create_date.1"));
			System.out.println(rs.getString("due_in_date"));
			System.out.println(rs.getString("invoice_currency"));
			System.out.println(rs.getString("document_type"));
			System.out.println(rs.getString("posting_id"));
			System.out.println(rs.getString("area_business"));
			System.out.println(rs.getString("total_open_amount"));
			System.out.println(rs.getString("baseline_create_date"));
			System.out.println(rs.getString("cust_payement_terms"));
			System.out.println(rs.getString("invoice_id"));
			System.out.println(rs.getString("isOpen"));
	
			stmt.close();
			con.close();  
			System.out.println("Establishing a DB Connection");
		}
		catch(Exception e)
		{ 
			System.out.println("Error Occurred");
			e.printStackTrace();
		}
		return con;
	}
}
