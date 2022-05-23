package actionPack;

import java.util.Map;  
import java.sql.*;

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  

public class userWalletAction implements SessionAware{
	
	private SessionMap<String,Object> sessionMap;
	private String wallet;
	
	private void dql(String myquery) throws Exception{
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
		ResultSet rs = st.executeQuery(myquery);
		rs.next();
		setWallet(
				Integer.toString((Integer)rs.getInt("wallet"))
				);
	}
	
	public String execute(){
		if(((String)sessionMap.get("logged-in")).equals("true"))
		{
			String un = (String)sessionMap.get("user-name");
			try {
				dql("Select * from creds where emailid=\""+un+"\" OR username=\""+un+"\"");
				return("success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return("error");
			}
			
		}else {
			return("error");
		}
	}
	
	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}

	public String getWallet() {
		return wallet;
	}

	public void setWallet(String wallet) {
		this.wallet = wallet;
	} 
	
}
