package com.highradius.web.dao;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.highradius.web.model.Invoice;
import java.sql.*;
import java.util.ArrayList;  

public class InvoicesDao 
{
	public Connection getConnection()
	{
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			String URL = "jdbc:mysql://localhost:3306/Invoice";
			String username = "root";
			String password = "nm2001";
			return DriverManager.getConnection(URL, username, password);     
		}
		
		catch(Exception e)
		{ 
			System.out.println(e);
		}
		return null;  
	}
	
	public String getInvoices(int start, int limit) throws SQLException 
	{
		ArrayList<Invoice> invoices_list = new ArrayList<Invoice>();
		Connection con = getConnection();
		Statement stmt=con.createStatement();
		
		String query = "SELECT serial_no, name_customer, cust_number, invoice_id, total_open_amount, due_in_date, predicted_payment_date, notes FROM h2hbabba2697 ORDER BY serial_no LIMIT "+start+", "+limit;
		ResultSet rs=stmt.executeQuery(query);
		while(rs.next()) 
		{
			Invoice inv = new Invoice();
			inv.setSl_no(rs.getInt("sl_no"));
			inv.setBusiness_code(rs.getString("business_code"));
			inv.setCust_number(rs.getInt("cust_number"));
			inv.setName_customer(rs.getString("name_customer"));
			inv.setClear_date(rs.getString("clear_date"));
			inv.setBuisness_year(rs.getFloat("buisness_year"));
			inv.setDoc_id(rs.getString("doc_id"));
			inv.setPosting_date(rs.getString("posting_date"));
			inv.setDocument_create_date(rs.getString("document_create_date"));
			inv.setDocument_create_date1(rs.getString("document_create_date1"));
			inv.setDue_in_date(rs.getString("due_in_date"));
			inv.setInvoice_currency(rs.getString("invoice_currency"));
			inv.setDocument_type(rs.getString("document_type"));
			inv.setPosting_id(rs.getInt("posting_id"));
			inv.setArea_business(rs.getString("area_business"));
			inv.setDocument_create_date(rs.getString("document_create_date"));
			inv.setTotal_open_amount(rs.getDouble("total_open_amount"));
			inv.setBaseline_create_date(rs.getString("baseline_create_date"));
			inv.setIsOpen(rs.getInt("isOpen"));
			invoices_list.add(inv);
		}
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		// converting list to json string
		String jsonInvoiceList = gson.toJson(invoices_list);
		
		stmt.close();
		con.close();
		return jsonInvoiceList;
	}
	
	public void addInvoice(String name, String cust_no, String invoice_id, float amount, float due_date, String predicted_date, String notes) throws SQLException 
	{
		Connection con = getConnection();
		Statement stmt=con.createStatement();
		
		String values = "VALUES("+name+", "+cust_no+", "+invoice_id+", "+amount+", "+due_date+", "+predicted_date+", "+notes+")";
		String query = "INSERT INTO h2hbabba2697(name_customer, cust_number, invoice_id, total_open_amount, due_in_date, predicted_payment_date, notes) " + values;
		stmt.executeUpdate(query);
		stmt.close();
		con.close();
	}
	
	public void editInvoice(int serial_no, float amount, String notes) throws SQLException {		
		Connection con = getConnection();
		Statement stmt=con.createStatement();
		String query = "UPDATE h2hbabba2697 SET total_open_amount = "+amount+", notes = "+notes+" WHERE serial_no = "+serial_no;
		stmt.executeUpdate(query);
	}
	
	public void deleteInvoices(String toDelete) throws SQLException {
		Connection con = getConnection();
		Statement stmt=con.createStatement();
		String query = "DELETE FROM h2hbabba2697 WHERE serial_no IN "+toDelete;
		stmt.executeUpdate(query);
		
	}
}

