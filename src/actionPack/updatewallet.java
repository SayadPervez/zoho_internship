package actionPack;

import servicePack.DMLquery;

import java.util.Map;  

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  

public class updatewallet implements SessionAware{
	
	private String wallet;
	
	private SessionMap<String,Object> sessionMap;
	
	public String execute() {
		System.out.println("update wallet details : wallet is "+ getWallet());
		if(((String) sessionMap.get("logged-in")).equals("true"))
		{
			String sessionUsername = (String)sessionMap.get("user-name") ;
			DMLquery dq = new DMLquery();
			try {
				int n = dq.run("UPDATE creds SET wallet="+getWallet()+" WHERE username=\""+sessionUsername+"\" OR emailid=\""+sessionUsername+"\"");
				System.out.println("DML n: "+n);
				if(n==1)
				{
					System.out.println("updateUserDetails.java returns success");
					return("success");
				}
				else {System.out.println("updateUserDetails.java returns error");
					return("error");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("updateUserDetails.java returns exception error");
				return("error");
			}
		}
		else {
			System.out.println("updateUserDetails.java returns login");
			return("login");
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
