package actionPack;

import java.util.Map;  

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;

public class changePassword implements SessionAware{
	
	private SessionMap<String,Object> sessionMap;
	
	public String execute() {
		if(((String)sessionMap.get("logged-in")).equals("true"))
			{
			sessionMap.put("resetMail-id",(String)sessionMap.get("user-email"));
			return("success");}
		else {
			sessionMap.remove("resetMail-id");
			return("failure");
		}
	}
	
	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}  
	
}
