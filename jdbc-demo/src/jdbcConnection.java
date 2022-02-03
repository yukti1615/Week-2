
import java.sql.*;
public class jdbcConnection {

	public static void main(String[] args) throws SQLException
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class Loaded");			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=shelljell123");
			System.out.println("Connetion Establish with db server");
			
			//Select Statement
			String query="select * from employees.employee where gender='female'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				String name = rs.getString("name");
				int id = rs.getInt("ID");
				String role = rs.getString("Role");
				System.out.println(name);
				System.out.println(id);
				System.out.println(role);
			}	
			
			//Insert Statement
			String insertQuery = "insert into employees.employee (ID, Name ,Role, gender) values (6,'Hardik', 'Development', 'male' )";
			PreparedStatement pst = con.prepareStatement(insertQuery);

			int rowsInserted = pst.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("A new user was inserted successfully!");
			}
			
			
			//Update statement 
			String updateQuery = "update employees.employee set role='software development' where role='development' ";
			 
			PreparedStatement ust = con.prepareStatement(updateQuery);
			int rowsUpdated = ust.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An existing user was updated successfully!");
			}
			
			//Delete Statement
			String deleteQuery = "DELETE FROM employees.employee WHERE  Role='HR' ";
			 
			PreparedStatement dst = con.prepareStatement(deleteQuery);
			
			 
			int rowsDeleted = dst.executeUpdate();
			if (rowsDeleted > 0) {
			    System.out.println("A user was deleted successfully!");
			}
		}
		catch(ClassNotFoundException|SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(con!=null)
			{
				try {
					con.close();
					System.out.println("Closed All Resources");
				}catch(SQLException e)
				{
					e.printStackTrace();
				}
				
			}
		}
		

	}

}
