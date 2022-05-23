package actionPack;

import java.util.Map;  

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;

import org.json.JSONObject;

import servicePack.DMLquery;

public class saveseatlayout implements SessionAware{
	
	private String problem;
	private String refid;
	private String data;
	
	private SessionMap<String,Object> sessionMap; 
	
	public String execute()
	{
		if(
				((String) sessionMap.get("logged-in")).equals("true")
				&& 
				((String) sessionMap.get("user-type")).equals("owner")
			)
		{
			
			System.out.println(getData());
			DMLquery q = new DMLquery();
			try {
				q.run("UPDATE theaters SET seating=\'"+getData()+"\' where ref_id="+getRefid());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				setProblem("DML query Error");
				e.printStackTrace();
				return("error");
			}
			return("success");
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

	public String getRefid() {
		return refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	} 
	
}
