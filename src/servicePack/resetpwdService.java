package servicePack;

import java.sql.*;

public class resetpwdService {
	public Boolean execute_(String emailid,String newpwd) throws Exception
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
		int rs = st.executeUpdate("UPDATE creds SET password=\""+newpwd+"\"  WHERE emailid=\""+emailid+"\"");
		
		st.close();
		con.close();
		
		System.out.println("NO ERRORS in jdbc logincheck");
		
		if(rs==1)
			return(true);
		else
			return(false);
	}
}
