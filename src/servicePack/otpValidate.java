package servicePack;

import java.util.Map;  

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware; 

public class otpValidate implements SessionAware{
	
	private SessionMap<String,Object> sessionMap;
	private String otp;
	private String emailid;
	private String problem;
	
	public String execute()
	{
		System.out.println(getOtp());
		String serverotp = (String)sessionMap.get(getEmailid()+"otp");
		System.out.println(serverotp);
		if(serverotp.equals(getOtp()))
		{
			sessionMap.remove(getEmailid()+"otp");
			sessionMap.put("resetMail-id",getEmailid());
			return("success");
		}
		else {
				setProblem("Wrong OTP");
				return("error");
			}
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	public String getProblem() {
		return problem;
	}

	public void setProblem(String otp) {
		this.problem = problem;
	}
	
	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
}
