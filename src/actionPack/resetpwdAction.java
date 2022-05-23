package actionPack;

import java.util.Map;  

import servicePack.resetpwdService;

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware; 

public class resetpwdAction implements SessionAware{
	
	private SessionMap<String,Object> sessionMap;
	private String pwdc;
	private String problem;
	
	public String execute()
	{
		
	String useremailid = (String)sessionMap.get("resetMail-id");
	sessionMap.remove("resetMail-id");
	sessionMap.remove("logged-in");
	resetpwdService rps = new resetpwdService();
		try {
			if(rps.execute_(useremailid, getPwdc()))
				return("success");
			else {
				setProblem("0 lines updated on running query");
				return("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setProblem("Error @ db query execution");
			return("error");
		}
	}

	public String getPwdc() {
		return pwdc;
	}

	public void setPwdc(String pwdc) {
		this.pwdc = pwdc;
	}
	
	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	} 
}
