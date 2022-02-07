package login_form.database;

import java.sql.*;
import login_form.web.*;
public class LoginDao
{
	public boolean validate(Login login) throws ClassNotFoundException
	{
		Connection con = null;
		boolean status = false;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Class Loaded");			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=shelljell123");
			System.out.println("Connetion Establish with db server");
			
			String selectQuery="select * from employees2.login where username=? and password=? ";
			PreparedStatement pst = con.prepareStatement(selectQuery);
			pst.setString(1, login.getUsername());
			pst.setString(2, login.getPassword());
			
			System.out.println(pst);
            ResultSet rs = pst.executeQuery();
            status = rs.next();
			
		}
		catch (SQLException e) {
            // process sql exception
            System.out.println(e);
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
        return status;
	}
	

}
