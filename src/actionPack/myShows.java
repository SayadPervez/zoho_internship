package actionPack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;
import java.sql.*;

import dbassistPack.UID;

public class myShows implements SessionAware{
	private SessionMap<String,Object> sessionMap;
	
	private String problem;
	private String message;
	
	public String execute() throws Exception{
		if(
				((String) sessionMap.get("logged-in")).equals("true")
				&& 
				((String) sessionMap.get("user-type")).equals("customer")
			)
		{
			UID uid = new UID();
			try {
				int userid = uid.getUID((String)sessionMap.get("user-name"));
				ArrayList<String> sids = new ArrayList<String>();
				ArrayList<String> jsonli = new ArrayList<String>();
				
				String ipAddressMYSQL = "127.0.0.1";
				String portMYSQL = "3306";
				String databaseName = "tms";
				String urlMYSQL = "jdbc:mysql://"+ipAddressMYSQL+":"+portMYSQL+"/"+databaseName;
				
				
				String usernameMYSQL = "root";
				String passwordMYSQL = "root123";
				
				Class.forName("com.mysql.cj.jdbc.Driver");	
				Connection con = DriverManager.getConnection(urlMYSQL,usernameMYSQL,passwordMYSQL);
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM bookings WHERE userid="+userid);
				
				while(rs.next())
				{
					sids.add(rs.getString("showid"));
				}
				
				System.out.println("userid"+userid);
				System.out.println("sids: "+sids);
				
				for(String s:sids)
				{
					Statement st0 = con.createStatement();
					ResultSet rs0 = st.executeQuery("SELECT * FROM shows WHERE showid=\'"+s+"\'");
					rs0.next();
					jsonli.add(rs0.getString("jsonString"));
					st0.close();
				}
				
				
				
				st.close();
				con.close();
				System.out.println("returning message");
				setMessage(String.join("$-$", jsonli));
				System.out.println("^^^^^^^^^^^^^");
				return("message");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				setProblem("DB exception");
				e.printStackTrace();
				return("error");
			}
		}
		else {
			return("login");
		}
	}
	
	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
