package servicePack;

import java.sql.*;

public class DQLquery {
	public String run(String myquery,String s) throws Exception
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
		ResultSet rs = st.executeQuery(myquery);
		
		String ret = "";
		
		while(rs.next())
		{
			ret = rs.getString(s);
			break;
		}
		
		st.close();
		con.close();
		
		System.out.println("NO ERRORS in running DML Query");
		return(ret);
	}
	
	public int run(String myquery,int n) throws Exception
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
		ResultSet rs = st.executeQuery(myquery);
		
		int ret=-69420;
		
		if(n<0)
		{
				int count = 0;
				while(rs.next())
				{
					count+=1;
				}
				ret = count;
		}
		else {
				while(rs.next())
				{
					ret = rs.getInt(n);
					break;
				}
		}
		st.close();
		con.close();
		
		System.out.println("NO ERRORS in running DQL Query");
		return(ret);
	}
}
