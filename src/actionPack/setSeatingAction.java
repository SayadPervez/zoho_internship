package actionPack;

import java.util.Map;  
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware; 

import servicePack.DQLquery;

public class setSeatingAction implements SessionAware{
	
	private String refid;
	private String seatingstatus;
	private String problem;
	
	private SessionMap<String,Object> sessionMap;
	
	public String execute() {
		if(
				((String) sessionMap.get("logged-in")).equals("true")
				&& 
				((String) sessionMap.get("user-type")).equals("owner")
			)
		{
			System.out.println(getRefid());
			DQLquery q = new DQLquery();
			try {
				String x = q.run("Select * from theaters where ref_id="+getRefid(), "seating");
				setSeatingstatus(x);
				System.out.println(x);
				return("success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				setProblem("DQLquery Throwed an error");
				e.printStackTrace();
				return("error");
			}
		}
		else {
			return("login");
		}
	}

	public String getRefid() {
		return refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}
	
	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}

	public String getSeatingstatus() {
		return seatingstatus;
	}

	public void setSeatingstatus(String seatingstatus) {
		this.seatingstatus = seatingstatus;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}  
	
}
