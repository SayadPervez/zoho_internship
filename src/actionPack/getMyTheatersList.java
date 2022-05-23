package actionPack;

import java.util.ArrayList;
import java.util.Map;  

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  
import org.json.JSONObject; 

import servicePack.DQLquery;
import dbassistPack.UID;
import dbassistPack.getRefByUID;
import dbassistPack.getTheatersByUID;

public class getMyTheatersList implements SessionAware{

	private SessionMap<String,Object> sessionMap;
	
	private String problem;
	private String message;
	
	public String execute()
	{
		if(
				((String) sessionMap.get("logged-in")).equals("true")
				&& 
				((String) sessionMap.get("user-type")).equals("owner")
			)
		{
			DQLquery q = new DQLquery();
			int count = -1;
			try {
				count = q.run("SELECT * from theaters", -1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				setProblem("DQL count throwed error");
				e.printStackTrace();
				return("error");
			}
			if(count<=0)
			{
				setMessage("[]");
				return("message");
			}
			else {
				// there are some entries in theaters table for this username
				UID UID_ = new UID();
				try {
					int uid = UID_.getUID((String)sessionMap.get("user-name"));
					getRefByUID ru = new getRefByUID();
					ArrayList<Integer> refid = ru.get(uid);
					getTheatersByUID rt = new getTheatersByUID();
					ArrayList<String> theatersjson = rt.getJsonByUid(uid); 
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("refid", refid);
					jsonObj.put("theatersjson", theatersjson);
					setMessage(jsonObj.toString());
					return("message");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					setProblem("dbassist.getUID or getRefByUID or getTheaterByUID throwed error");
					e.printStackTrace();
					return("Error");
				}
			}
		}
		else {
			return("login");
		}
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}  
	
}
