package dbassistPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class getTheatersByUID {
	
	public ArrayList<String> getJsonByUid(int uid) throws Exception
	{
		ArrayList<String> ret = new ArrayList<String>();
		
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
		ResultSet rs = st.executeQuery("SELECT * FROM theaters WHERE userid="+uid+"");
		
		while(rs.next())
		{
			ret.add((String)rs.getString("jsonString"));
		}
		
		st.close();
		con.close();
		
		return(ret);
	}
	
}
