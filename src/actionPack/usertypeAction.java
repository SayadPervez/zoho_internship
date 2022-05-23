package actionPack;

import java.util.Map;  

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware; 

import dbassistPack.userfunctions;

public class usertypeAction implements SessionAware{
	
	private SessionMap<String,Object> sessionMap; 
	
	public String execute()
	{
		/*
		 * Returns logged in user's type
		 * */
		userfunctions uf = new userfunctions();
		String temp = uf.getUserType((String)sessionMap.get("user-name"));
		if(temp.equals("owner") || temp.equals("customer"))
			return(temp);
		else {
			System.out.println("Undefined usertype : "+temp);
			return("error");
		}
	}
	
	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	} 
}
