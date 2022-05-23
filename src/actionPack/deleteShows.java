package actionPack;

import java.util.Map;  

import servicePack.DMLquery;

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;

import dbassistPack.UID;  

public class deleteShows implements SessionAware{
	
	private String problem;
	private String showid;
	
	private SessionMap<String,Object> sessionMap;
	
	public String execute()
	{
		if(
				((String) sessionMap.get("logged-in")).equals("true")
				&& 
				((String) sessionMap.get("user-type")).equals("owner")
			)
		{
			
			DMLquery q = new DMLquery();
			try {
				int xx = q.run("DELETE FROM shows WHERE showid=\'"+getShowid()+"\'");
				
				if(xx==1)
					return("success");
				else
				{
					setProblem(xx+" rows were updated");
					return("error");
				}
			} catch (Exception e) {
				e.printStackTrace();
				setProblem("DMLquery thowed error");
				return("error");
			}
		}
		else {
			setProblem("Login error");
			return("login");
		}
	}



	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}



	public String getShowid() {
		return showid;
	}



	public void setShowid(String showid) {
		this.showid = showid;
	}

}