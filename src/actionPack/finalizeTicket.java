package actionPack;

import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject; 
import java.sql.*;
import servicePack.makeTransaction;

import servicePack.mailer;
import dbassistPack.getEmailID;

import dbassistPack.UID;

public class finalizeTicket implements SessionAware{
	private SessionMap<String,Object> sessionMap;
	
	private String sid;
	private String myseats;
	private String totalcost;
	
	public String execute() throws Exception
	{
		if(
				((String) sessionMap.get("logged-in")).equals("true")
				&& 
				((String) sessionMap.get("user-type")).equals("customer")
			) {
			System.out.println(sid);
			System.out.println(myseats);
			System.out.println(totalcost);
			int Amount = Integer.parseInt(totalcost); 
			
			UID uid = new UID();
			int uidCustomer = uid.getUID((String) sessionMap.get("user-name"));
			
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
			String tempy = sid.substring(0, sid.indexOf('$'));
			System.out.println("tempy : "+tempy);
			ResultSet rs = st.executeQuery("SELECT * FROM theaters WHERE ref_id="+tempy);
			
			rs.next();
			int uidOwner = rs.getInt("userid");
			
			Statement st1 = con.createStatement();
			int rs1 = st1.executeUpdate("INSERT INTO bookings (userid,showid,myseats,cost) VALUES(\'"+uidCustomer+"\',\'"+sid.replace('$', '|')+"\',\'"+myseats+"\',"+Amount+")");
			
			Statement st12 = con.createStatement();
			ResultSet rs12 = st12.executeQuery("Select * from shows where showid=\'"+sid.replace('$', '|')+"\'");
			
			rs12.next();
			String booked = rs12.getString("bookedseats");
			if(booked.equals("-"))
			{
				booked=myseats;
			}
			else
			{
				booked += ","+myseats;
			}
			
			Statement st13 = con.createStatement();
			int q = st13.executeUpdate("Update shows set bookedseats=\'"+booked+"\' where showid=\'"+sid.replace('$', '|')+"\'");

			st12.close();
			st13.close();
			st1.close();
			st.close();
			con.close();
			
			System.out.println("Amount = "+Amount);
			System.out.println("uidOwner = "+uidOwner);
			
			System.out.println("uidCustomer = "+uidCustomer);
			makeTransaction mt = new makeTransaction();
			if(mt.commit(uidCustomer, uidOwner, Amount))
				{
				mailer m = new mailer();
				getEmailID ge = new getEmailID();
				m.sendEmail(ge.fetchEmailID((String) sessionMap.get("user-name")), "TICKET CONFIRMATION", "<h1>Tickets Confirmed</h1><br>Your Seats are :<span color=\"red\">"+myseats+"</span>", true);
				return("success");
				}
			else
			{
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

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getMyseats() {
		return myseats;
	}

	public void setMyseats(String myseats) {
		this.myseats = myseats;
	}

	public String getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(String totalcost) {
		this.totalcost = totalcost;
	}
}
