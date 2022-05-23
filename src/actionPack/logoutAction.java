package actionPack;

import java.util.Map;  

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  

public class logoutAction implements SessionAware{

	private SessionMap<String,Object> sessionMap; 
	
	public String execute()
	{
		String x = (String)sessionMap.get("user-type");
		if(x==null || x=="")
			return("success");
		sessionMap.clear();
		sessionMap.put("user-type", x);
		return("success");
	}
	
	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	} 
}
