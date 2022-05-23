package actionPack;

import java.util.Map;  

import dbassistPack.UID;
import servicePack.DMLquery;

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;  

public class addTheatersAction implements SessionAware{
	
	private String jsonString;
	private String problem;
	
	private SessionMap<String,Object> sessionMap;
	
	public String execute()
	{
		if(((String)sessionMap.get("logged-in")).equals("true"))
		{
			JSONObject jsonObj = new JSONObject(getJsonString());
			UID id = new UID();
			int uid=-1;
			try {
				uid = id.getUID((String)sessionMap.get("user-name"));
			} catch (Exception e) {
				e.printStackTrace();
				setProblem("getUID thowed error");
				return("error");
			}
			String theatername = (String) jsonObj.get("theatername");
			String location = (String) jsonObj.get("theaterlocation");
			int noofScreens = Integer.valueOf((String) jsonObj.get("noofscreens"));
			String timeslots = (String) jsonObj.get("timeslots");
			DMLquery q = new DMLquery();
			try {
				int xx = q.run("INSERT INTO theaters (userid,theatername,location,noofscreens,timeslots,jsonString)"+
			" VALUES("+uid+",\'"+theatername+"\',\'"+location+"\',"+noofScreens+",\'"+timeslots+"\',\'"+getJsonString()+"\')");
				
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

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
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
