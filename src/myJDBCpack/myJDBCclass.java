package myJDBCpack;

import java.sql.*;

public class myJDBCclass{
	public static void main(String args[]) throws Exception{
		
		String ipAddressMYSQL = "127.0.0.1";
		String portMYSQL = "3306";
		String databaseName = "testdb";
		String urlMYSQL = "jdbc:mysql://"+ipAddressMYSQL+":"+portMYSQL+"/"+databaseName;
		System.out.println(urlMYSQL);
		
		String usernameMYSQL = "root";
		String passwordMYSQL = "root123";
		
		Class.forName("com.mysql.cj.jdbc.Driver");	
		Connection con = DriverManager.getConnection(urlMYSQL,usernameMYSQL,passwordMYSQL);
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select * from zoho_si");
		
		while(rs.next())
		{
			String name = rs.getString("name");
			int uid = rs.getInt("uid");
			String sid = rs.getString("sid");
			System.out.println("Query Output : "+uid+" -> "+name+":"+sid);
		}
		
		
		
		st.close();
		con.close();
		
		System.out.println("NO ERRORS");
	}
}
