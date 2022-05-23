package actionPack;

import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject; 
import java.sql.*;

public class getAllShows implements SessionAware{

	private SessionMap<String,Object> sessionMap;
	
	private String message;
	
	public String execute() throws Exception{
		if(
				((String) sessionMap.get("logged-in")).equals("true")
				&& 
				((String) sessionMap.get("user-type")).equals("customer")
			)
		{
			JSONObject jsonObj = new JSONObject();
			setMessage("It works !!");
			
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
			ResultSet rs = st.executeQuery(
					"SELECT s.moviename, s.theatername, t.location, s.screen as screennumber, s.timeslot as slot, s.duration, s.date, s.theater_ref_id as theaternumber, t.seating as seats, s.bookedseats from shows as s inner join theaters as t on s.theater_ref_id = t.ref_id"
					);
			
			int count = 0;
			
			while(rs.next())
			{
				JSONObject job = new JSONObject();
				job.put("moviename", rs.getString("moviename"));
				job.put("theatername", rs.getString("theatername"));
				job.put("location", rs.getString("location"));
				job.put("screennumber", rs.getString("screennumber"));
				job.put("slot", rs.getString("slot"));
				job.put("duration", rs.getString("duration"));
				job.put("date", rs.getString("date"));
				job.put("theaternumber", rs.getString("theaternumber"));
				job.put("seats", rs.getString("seats"));
				job.put("bookedseats", rs.getString("bookedseats"));
				
				jsonObj.put(Integer.toString(count), job.toString());
				count+=1;
			}
			setMessage(jsonObj.toString());
			return("message");
		}
		else
			return("login");
	}
	
	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
