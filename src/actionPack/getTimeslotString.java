package actionPack;

import java.util.Map;  

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;

import servicePack.DQLquery;

public class getTimeslotString implements SessionAware{
	private SessionMap<String,Object> sessionMap; 
	
	private String refid;
	private String message;
	
	public String execute()
	{
		if(((String)sessionMap.get("logged-in")).equals("true"))
		{
			DQLquery dql = new DQLquery();
			try {
				System.out.println("SELECT * FROM theaters WHERE ref_id="+getRefid());
				String output = dql.run("SELECT * FROM theaters WHERE ref_id="+getRefid(), "timeslots");
				setMessage(output);
			} catch (Exception e) {
				e.printStackTrace();
				setMessage("Error");
			}
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

	public String getRefid() {
		return refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}  
}
