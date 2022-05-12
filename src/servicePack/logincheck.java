package servicePack;

import java.sql.*;

public class logincheck {
	public Boolean check(String __uname, String __password,String __type) throws Exception{
		
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
		ResultSet rs = st.executeQuery("Select * FROM "+__type+"_creds WHERE username=\""+__uname+"\"");
		
		rs.next();
			String username = rs.getString("username");
			String emailid = rs.getString("emailid");
			int uid = rs.getInt("uid");
			int age = rs.getInt("age");
			String password = rs.getString("password");
			System.out.println("Query Output : "+uid+" -> "+username+":"+emailid+":"+age+":"+password);
		
		
		
		
		st.close();
		con.close();
		
		System.out.println("NO ERRORS in jdbc logincheck");
		if(password.equals(__password))
			return(true);
		else
			return(false);
	}
}
