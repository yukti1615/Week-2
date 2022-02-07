package registration_form.dao;
import registration_form.model.Employee;
import java.sql.*;

public class EmployeeDao
{
	public int registerEmployee(Employee employee) throws Exception
	{
		Connection con = null;
		
		int result = 0;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class Loaded");			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=shelljell123");
			System.out.println("Connetion Establish with db server");
			String insertQuery = "insert into employees2.employee" + 
					"(id, firstname, lastname, username, password, contact) Values"+
					"(1, employee.getFirstname(), employee.getLastName(), employee.getUsername(), employee.getPassword()"
					+ ", employee.getContact());";
			PreparedStatement pst = con.prepareStatement(insertQuery);
			
			System.out.println("Values has been inserted");
			System.out.println(employee.getFirstname());
			

		}
		catch(ClassNotFoundException|SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con!=null)
			{
				try
				{
					con.close();
					System.out.println("Closed All Resources");
				}catch(SQLException e)
				{
					e.printStackTrace();
				}
				
			}
		}
		return result;
		
	}

}
