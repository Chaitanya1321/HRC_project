package com.highradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet implementation class AddInvoice
 */
@WebServlet("/AddInvoice")
public class AddInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /*
     * @see HttpServlet#HttpServlet()
     */
    public AddInvoice() {
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
			String query = "insert into model values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(query);
			
			st.setString(1, request.getParameter("business_code"));
			st.setInt(2, Integer.valueOf(request.getParameter("cust_number")));
			st.setString(3, request.getParameter("clear_date"));
			st.setString(4, request.getParameter("buisness_year"));
			st.setString(5, request.getParameter("doc_id"));
			st.setString(6, request.getParameter("posting_date"));
			st.setString(7, request.getParameter("document_create_date"));
			st.setString(8, request.getParameter("due_in_date"));
			st.setString(9, request.getParameter("invoice_currency"));
			st.setString(10, request.getParameter("document_type"));
			st.setInt(11, Integer.valueOf(request.getParameter("posting_id")));
			st.setDouble(12, Double.valueOf(request.getParameter("total_open_amount")));
			st.setString(13, request.getParameter("baseline_create_date"));
			st.setString(14, request.getParameter("cust_payment_terms"));
			st.setInt(15, Integer.valueOf(request.getParameter("invoice_id")));
			st.executeUpdate();
			
			st.close();
			con.close();
			
			PrintWriter out = response.getWriter();
			out.println("<html><body><b>Sucessfully Inserted" + "</b></body></html>");
			

		}
		catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println("<html><body><b>Error" + "</b></body></html>");
			e.printStackTrace();
		}
	}

}