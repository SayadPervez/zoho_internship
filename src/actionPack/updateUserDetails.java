package actionPack;

import servicePack.DMLquery;

import java.util.Map;  

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  

public class updateUserDetails implements SessionAware{
	
	private String uname;
	private String emailid;
	private String myage;
	
	private SessionMap<String,Object> sessionMap;
	
	public String execute() {
		System.out.println("update user details : age uname emailid is "+getMyage()+" "+getUname()+" "+getEmailid() );
		if(((String) sessionMap.get("logged-in")).equals("true"))
		{
			String sessionUsername = (String)sessionMap.get("user-name") ;
			sessionMap.put("user-name", getUname());
			DMLquery dq = new DMLquery();
			try {
				int n = dq.run("UPDATE creds SET username=\""+getUname()+"\", emailid=\""+getEmailid()+"\", age="+getMyage()+" WHERE username=\""+sessionUsername+"\" OR emailid=\""+sessionUsername+"\"");
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

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}


	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}

	public String getMyage() {
		return myage;
	}

	public void setMyage(String myage) {
		this.myage = myage;
	}  

}
