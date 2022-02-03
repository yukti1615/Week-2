
import java.sql.*;
//1. import package, java.sql
//2. load and register driver
//3. create a connection
//4. Create a statement
//5. execute the query
//6. process the results
//7. close the connection


public class JdbcConnectivity {

	public static void main(String[] args) throws Exception {
		String url="jdbc:mysql:U+202A://localhost:3306//employees?useSSL=false" ;
		
		String uname="root";
		String pass = "shelljell123";
		String query="select * from employee where gender='female'";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, uname, pass);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		String name = rs.getString("name");
		System.out.println(name);
		
		st.close();
		con.close();

	}

}
