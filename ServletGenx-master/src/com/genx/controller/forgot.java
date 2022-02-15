package com.genx.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.genx.utils.JDBCUtils;

/**
 * Servlet implementation class Forgot
 */
@WebServlet("/forgot")
public class forgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public forgot() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			String updateQuery = "update users set password=? where username=?";
			Connection connection = JDBCUtils.getConnection();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String confirm_password = request.getParameter("confirm_password");
			PreparedStatement pst = connection.prepareStatement(updateQuery);
			pst.setString(2, username);
			pst.setString(1, password);
			
			int i = pst.executeUpdate();
			if(i>0)
				response.sendRedirect("todo-application-jsp-servlet-jdbc-mysql-master/login/success.jsp");
			else
				response.sendRedirect("Error.jsp");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
