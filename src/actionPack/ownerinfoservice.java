package actionPack;

import java.util.Map;  

import java.sql.*;

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;

public class ownerinfoservice implements SessionAware{
	
	private String username;
	private String emailid;
	private String age;
	private String type;
	private String wallet;
	
	private SessionMap<String,Object> sessionMap;  
	
	public String execute() throws Exception
	{
		String x = (String)sessionMap.get("user-name");
		
		
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
		ResultSet rs = st.executeQuery("SELECT * from creds where emailid=\""+x+"\" OR username=\""+x+"\"");
		
		while(rs.next())
		{
			setUsername((String)rs.getString("username"));
			String temp_ = (String)rs.getString("emailid");
			sessionMap.put("user-email", temp_);
			setEmailid(temp_);
			setType((String)rs.getString("type"));
			setAge(
					Integer.toString(
							(
									(Integer)rs.getInt("age")
									)
							)
					);
			setWallet(
					String.valueOf(
							(
									(Double)rs.getDouble("wallet")
									)
							)
					);
		}
		
		st.close();
		con.close();
		if(getType().equals("owner"))
		{
			return("success");
		}
		else
			return("successcustomer");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWallet() {
		return wallet;
	}

	public void setWallet(String wallet) {
		this.wallet = wallet;
	}
	
	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	} 
	
}
