package servicePack;

import java.sql.*;

public class makeTransaction {
	public Boolean commit(int from,int to,int amount) throws Exception
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
		
		Statement st0 = con.createStatement();
		ResultSet rs0 = st0.executeQuery("Select * from creds where uid="+from);
		rs0.next();
		int customer_balance = rs0.getInt("wallet");
		int customer_updated = customer_balance - amount;
		
		Statement st1 = con.createStatement();
		int rs1 = st1.executeUpdate("Update creds set wallet="+customer_updated+" where uid="+from);
		
		Statement st2 = con.createStatement();
		ResultSet rs2 = st2.executeQuery("Select * from creds where uid="+to);
		rs2.next();
		int owner_balance = rs2.getInt("wallet");
		int owner_updated = owner_balance + amount;
		
		Statement st3 = con.createStatement();
		int rs3 = st3.executeUpdate("Update creds set wallet="+owner_updated+" where uid="+to);
		
		st0.close();
		st1.close();
		st2.close();
		con.close();
		if(rs1==rs3 && rs1==1)
			return(true);
		else
			return(false);
	}
}
