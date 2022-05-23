package actionPack;

import java.util.ArrayList;
import java.util.Map;  
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;

import dbassistPack.UID;
import dbassistPack.getTheatersByUID;
import dbassistPack.getSeatingByUID;
import dbassistPack.getRefByUID;

public class theatersPageAction implements SessionAware{
	
	private String problem;
	private String listOfJsonObjects;
	private String seatingstatus;
	private String refid;
	
	private SessionMap<String,Object> sessionMap;
	
	public String execute() {
		if(((String)sessionMap.get("logged-in")).equals("true"))
		{
			UID u = new UID();
			int uid = -1;
			try {
				uid = u.getUID((String)sessionMap.get("user-name"));				
			} catch (Exception e) {
				setProblem("getUID throwed error");
				e.printStackTrace();
				return("error");
			}
			try {
				getTheatersByUID obj = new getTheatersByUID();
				ArrayList<String> li = obj.getJsonByUid(uid);
				String temp="";
				for(String s:li)
				{
					temp+=s+"$";
				}
				setListOfJsonObjects(temp.substring(0,temp.length()-1));
								
				getSeatingByUID ob = new getSeatingByUID();
				ArrayList<String> l = ob.get(uid);
				String tl = "";
				for(String s:l)
				{
					tl+=s+"$";
				}
				setSeatingstatus(tl.substring(0,tl.length()-1));
				
				getRefByUID obj_ = new getRefByUID();
				ArrayList<Integer> l_ = obj_.get(uid);
				String tl_ = "";
				for(int s:l_)
				{
					tl_+=s+"$";
				}
				setRefid(tl_.substring(0,tl_.length()-1));
				
				return("success");
			} catch (Exception e) {
				setProblem("getTheatersByUID or getSeatingByUID or getRefByUID throwed error");
				e.printStackTrace();
				return("success");
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

	public String getListOfJsonObjects() {
		return listOfJsonObjects;
	}

	public void setListOfJsonObjects(String listOfJsonObjects) {
		this.listOfJsonObjects = listOfJsonObjects;
	}

	public String getSeatingstatus() {
		return seatingstatus;
	}

	public void setSeatingstatus(String seatingstatus) {
		this.seatingstatus = seatingstatus;
	}

	public String getRefid() {
		return refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	} 
}
