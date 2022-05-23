package actionPack;

import servicePack.signupService;
import servicePack.mailer;

import java.util.Map;  

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware; 

public class signupAction implements SessionAware{
	
	private String type;
	private String uname;
	private String pwd;
	private String emailid;
	private String age;
	private String problem;
	
	private SessionMap<String,Object> sessionMap; 
	
	public String execute() throws Exception
	{
		signupService ss = new signupService();
		System.out.println("signup Action called");
		System.out.println(type+"->"+uname+"->"+pwd+"->"+emailid+"->"+age);
		if(ss.signup(getUname(),getPwd(),getType(),getAge(),getEmailid()))
			{System.out.println("successful signup");
			mailer m = new mailer();
			String status_ = m.sendEmail(emailid,"TMS Sign Up NOtification","<h3>THEATER MANAGEMENT APP</h3><br><div>You registered as a <strong style=\"color:red;\">"+getType()+"</strong> in our app under the following credentials : <br><ul><li>Username : "+getUname()+"<li>Email ID : "+getEmailid()+"<li>Password : "+getPwd()+"<li>Age : "+getAge()+"</ul></div>",true);
			if(status_.equals("success"))
			{
				sessionMap.put("logged-in", "true");
				sessionMap.put("user-type", type);
				sessionMap.put("user-name",uname);
				return("success");
			}
			else
			{
				setProblem("mail error");
				return("error");
			}
			}
		else
		{
			System.out.println("failed signup");	
			setProblem("signup error");
			return("error");}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
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
