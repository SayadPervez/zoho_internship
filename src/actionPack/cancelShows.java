package actionPack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject; 
import java.sql.*;

import servicePack.mailer;
import servicePack.makeTransaction;

import dbassistPack.UID;
import dbassistPack.getEmailID;

public class cancelShows implements SessionAware{
	private SessionMap<String,Object> sessionMap;
	
	private String sid;
	private String message;
	
	public String execute() throws Exception{
		if(
				((String) sessionMap.get("logged-in")).equals("true")
				&& 
				((String) sessionMap.get("user-type")).equals("customer")
			) {
			UID uid = new UID();
			String customerUID = Integer.toString(uid.getUID((String) sessionMap.get("user-name"))) ;
			System.out.println(getSid()+" -->> SID FROM cancelShows.java");
			
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
			ResultSet rs = st.executeQuery("SELECT * FROM bookings WHERE showid=\'"+getSid()+"\'");
			
			rs.next();
			int Amount = Integer.parseInt(rs.getString("cost"));
			String [] mySeats = (rs.getString("myseats")).split(",");
			
			Statement st0 = con.createStatement();
			st0.executeUpdate("DELETE FROM bookings WHERE showid=\'"+getSid()+"\'");
			
			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery("SELECT * FROM shows WHERE showid=\'"+getSid()+"\'");
			
			rs1.next();
			String [] allSeats = (rs1.getString("bookedseats")).split(",");
			
			ArrayList<String> mine = new ArrayList<String>(Arrays.asList(mySeats));
			ArrayList<String> every = new ArrayList<String>(Arrays.asList(allSeats));
			every.removeAll(mine);
			
			String upd = "-";
			if(every.size()!=0)
			{
				upd = String.join(",", every);
			}
			
			Statement st2 = con.createStatement();
			st2.executeUpdate("UPDATE shows SET bookedseats=\'"+upd+"\' WHERE showid=\'"+getSid()+"\'");
			
			Statement st3 = con.createStatement();
			String refid = getSid().substring(0, getSid().indexOf('|'));
			System.out.println("refid :"+refid);
			ResultSet rs3 = st3.executeQuery("SELECT * FROM theaters WHERE ref_id="+refid+"");
			rs3.next();
			String ownerUID = rs3.getString("userid");
			
			System.out.println(customerUID);
			System.out.println(ownerUID);
			
			Statement st_0 = con.createStatement();
			ResultSet q1 = st_0.executeQuery("SELECT * FROM creds");
			
			int ownerBalance = 0;
			int customerBalance = 0;
			
			while(q1.next())
			{
				String xuid = q1.getString("uid");
				if(xuid.equals(customerUID))
				{
					Double tempx = q1.getDouble("wallet");
					customerBalance = tempx.intValue();
				}
				else if(xuid.equals(ownerUID))
				{
					Double tempx = q1.getDouble("wallet");
					ownerBalance = tempx.intValue();
				}
			}
			
			int customerUpdated = customerBalance + Amount;
			int ownerUpdated = ownerBalance - Amount;
			
			Statement st_1 = con.createStatement();
			st_1.executeUpdate("UPDATE creds SET wallet="+customerUpdated+" WHERE uid="+customerUID);
			
			Statement st_2 = con.createStatement();
			st_2.executeUpdate("UPDATE creds SET wallet="+ownerUpdated+" WHERE uid="+ownerUID);
			
			st.close();
			con.close();
			
			setMessage("Refund amount processed !!  "+Amount);
			mailer m = new mailer();
			getEmailID ge = new getEmailID();
			m.sendEmail(ge.fetchEmailID((String) sessionMap.get("user-name")), "TICKET Cancelled", "<h1>Tickets Confirmed</h1><br>Amount refunded :<strong style=\"color:red;\">"+Amount+"</strong>", true);
			return("message");
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
