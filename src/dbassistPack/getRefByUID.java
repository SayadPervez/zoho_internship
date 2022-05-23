package dbassistPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class getRefByUID {
	public ArrayList<Integer> get(int uid) throws Exception
	{
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
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
			ret.add((int)rs.getInt("ref_id"));
		}
		
		st.close();
		con.close();
		
		return(ret);
	}
}
