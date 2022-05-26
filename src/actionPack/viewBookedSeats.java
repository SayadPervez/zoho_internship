package actionPack;

import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject; 
import java.sql.*;

public class viewBookedSeats implements SessionAware{
	
	private SessionMap<String,Object> sessionMap;
	
	private String sid;
	
	private String seatingjson;
	private String bookedseats;
	private String mywallet;
	
	public String execute() throws Exception
	{
		if(
				((String) sessionMap.get("logged-in")).equals("true")
				&& 
				((String) sessionMap.get("user-type")).equals("owner")
			)
		{
		
		String tref = getSid().substring(0, getSid().indexOf('$'));
		String uname = (String) sessionMap.get("user-name");
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
		System.out.println(tref);
		ResultSet rs = st.executeQuery("SELECT * FROM theaters WHERE ref_id="+tref);
		
		rs.next();
		setSeatingjson(rs.getString("seating"));
		System.out.println(getSeatingjson());
		
		Statement st2 = con.createStatement();
		ResultSet rs2 = st.executeQuery("SELECT * FROM shows WHERE showid=\'"+getSid().replace("$", "|")+"\'");
		rs2.next();
		setBookedseats(rs2.getString("bookedseats"));
		
		Statement st3 = con.createStatement();
		ResultSet rs3 = st3.executeQuery("SELECT * FROM creds WHERE username=\'"+uname+"\' OR emailid=\'"+uname+"\'");
		rs3.next();
		setMywallet(rs3.getString("wallet"));		
		
		st.close();
		st2.close();
		con.close();
		
		return("success");
		}
		else {
			return("login");
		}
	}

	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}
	
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSeatingjson() {
		return seatingjson;
	}

	public void setSeatingjson(String seatingjson) {
		this.seatingjson = seatingjson;
	}

	public String getBookedseats() {
		return bookedseats;
	}

	public void setBookedseats(String bookedseats) {
		this.bookedseats = bookedseats;
	}

	public String getMywallet() {
		return mywallet;
	}

	public void setMywallet(String mywallet) {
		this.mywallet = mywallet;
	}
}
