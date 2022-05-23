package actionPack;

import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;  

import servicePack.DMLquery;

public class addShows implements SessionAware{
	
	private SessionMap<String,Object> sessionMap;
	
	private String jsondata;
	private String message;
	private String problem;
	
	public String execute()
	{
		if(
				((String) sessionMap.get("logged-in")).equals("true")
				&& 
				((String) sessionMap.get("user-type")).equals("owner")
			)
		{
			JSONObject jsonObj = new JSONObject(getJsondata());
			String showid = (String) jsonObj.get("showid");
			int theater_ref_id = Integer.valueOf((String) jsonObj.get("theaternumber"));
			String theatername = (String) jsonObj.get("theatername");
			int screen = Integer.valueOf((String) jsonObj.get("screennumber"));
			String timeslot = (String) jsonObj.get("slot");
			String moviename = (String) jsonObj.get("moviename");
			String duration = (String) jsonObj.get("duration");
			String date = (String) jsonObj.get("date");
			String jsonString = getJsondata();
			
			System.out.println("JsonObject:"+jsonObj);
			
			DMLquery q = new DMLquery();
			int resp_ = -69420;
			try {
				resp_ = q.run(
						"INSERT INTO shows (showid,theater_ref_id,theatername,screen,timeslot,moviename,duration,date,jsonString) "+
						"VALUES("+
								"\'"+showid+"\',"+
								theater_ref_id + "," +
								"\'"+theatername+"\',"+
								screen + ","+
								"\'"+timeslot+"\',"+
								"\'"+moviename+"\',"+
								"\'"+duration+"\',"+
								"\'"+date+"\',"+
								"\'"+jsonString+"\'"
							+")"
						);
				if(resp_>0)
				{
					return("success");
				}
				else {
					setProblem("no rows updated in db");
					return("error");
				}
			} catch (Exception e) {
				setProblem("DMLQuery throwed error @ addSHows.js");
				e.printStackTrace();
				return("error");
			}
		}
		else {
			return("login");
		}
	}

	public String getJsondata() {
		return jsondata;
	}

	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

}
