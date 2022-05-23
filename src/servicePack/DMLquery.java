package servicePack;

import java.sql.*;

public class DMLquery {
	public int run(String myquery) throws Exception
	{
		String ipAddressMYSQL = "127.0.0.1";
		String portMYSQL = "3306";
		String databaseName = "tms";
		String urlMYSQL = "jdbc:mysql://"+ipAddressMYSQL+":"+portMYSQL+"/"+databaseName;
		System.out.println(urlMYSQL);
		
		String usernameMYSQL = "root";
		String passwordMYSQL = "root123";
		
		Class.forName("com.mysql.cj.jdbc.Driver");	
		Connection con = DriverManager.getConnection(urlMYSQL,usernameMYSQL,passwordMYSQL);
		
		Statement st = con.createStatement();
		int rs = st.executeUpdate(myquery);
		
		st.close();
		con.close();
		
		System.out.println("NO ERRORS in running DML Query");
		return(rs);
	}
}
