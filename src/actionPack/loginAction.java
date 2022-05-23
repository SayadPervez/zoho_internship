package actionPack;

import servicePack.logincheck;
import servicePack.usertypeService;

import java.util.Map;  

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  

public class loginAction implements SessionAware{
	
	private String type;
	private String uname;
	private String pwd;
	private String problem;
	
	private SessionMap<String,Object> sessionMap;  
	
	public String execute() throws Exception
	{
		logincheck lc = new logincheck();
		System.out.println("login Action called");
		System.out.println(type+"->"+uname+"->"+pwd);
		sessionMap.clear();
		if(lc.check(uname,pwd,type))
			{System.out.println("successful login");
			sessionMap.put("logged-in", "true");
			sessionMap.put("user-name",uname);
			usertypeService uta = new usertypeService();
			String type_ = uta.run(uname);
			sessionMap.put("user-type", type_);
			System.out.println("AM returning success... not my fault");
			return("success");}
		else
		{
			System.out.println("failed login");	
			sessionMap.put("logged-in", "false");
			setProblem("login error");
			return("error");}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
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
