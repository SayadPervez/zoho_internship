package actionPack;

import servicePack.logincheck;

public class loginAction {
	
	private String type;
	private String uname;
	private String pwd;
	private String problem;
	
	public String execute() throws Exception
	{
		logincheck lc = new logincheck();
		System.out.println("login Action called");
		System.out.println(type+"->"+uname+"->"+pwd);
		if(lc.check(uname,pwd,type))
			{System.out.println("successful login");
			return("success");}
		else
		{
			System.out.println("failed login");	
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

}
