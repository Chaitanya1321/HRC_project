package com.highradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet implementation class EditInvoice
 */
@WebServlet("/EditInvoice")
public class EditInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /*
     * @see HttpServlet#HttpServlet()
     */
    public EditInvoice() {
        super();
        // TODO Auto-generated constructor stub
    }

	/*
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/*
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Connection con = DatabaseConnection.createConnect();
			String query = "UPDATE model SET invoice_currency = ?, cust_payment_terms = ? WHERE sl_no = ?";
			PreparedStatement st = con.prepareStatement(query);
			
//			int field = Integer.parseInt(fieldValue);
			
			st.setString(1, request.getParameter("invoice_currency"));
			st.setString(2, request.getParameter("cust_payment_terms"));
			st.setInt(3, Integer.valueOf(request.getParameter("sl_no")));
			
			st.executeUpdate();
			con.close();
		}
		
		catch(Exception e) {
			
		}
	}
}