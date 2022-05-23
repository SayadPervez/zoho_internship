package actionPack;


import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject; 
import java.sql.*;

public class getMyShowsOwner implements SessionAware{
	
	private SessionMap<String,Object> sessionMap;
	
	private String tref;
	private String message;
	
	public String execute() throws Exception
	{
		if(
				((String) sessionMap.get("logged-in")).equals("true")
				&& 
				((String) sessionMap.get("user-type")).equals("owner")
			)
		{
			JSONObject jsonObj = new JSONObject();
			int refid = Integer.parseInt((String)getTref());
			
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
			ResultSet rs = st.executeQuery("SELECT * FROM shows WHERE theater_ref_id="+refid);
			
			int index = 0;
			
			while(rs.next())
			{
				JSONObject job = new JSONObject(rs.getString("jsonString"));
				jsonObj.put(Integer.toString(index), job);
				index+=1;
			}
			
			st.close();
			con.close();
			
			setMessage(jsonObj.toString());
			return("message");
		}
		else 
		{
			return("login");
		}
	}
	
	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}

	public String getTref() {
		return tref;
	}

	public void setTref(String tref) {
		this.tref = tref;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
