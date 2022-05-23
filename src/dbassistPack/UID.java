package dbassistPack;

import java.sql.*;

public class UID {
	public int getUID(String uname) throws Exception
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
		ResultSet rs = st.executeQuery("SELECT * FROM creds WHERE username=\""+uname+"\" OR emailid=\""+uname+"\"");
		
		rs.next();
		int uid = rs.getInt("uid");
		
		st.close();
		con.close();
		
		System.out.println("NO ERRORS in running dbassisPack.UID.java");
		return(uid);
	}
}
