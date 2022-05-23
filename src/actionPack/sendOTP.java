package actionPack;

import servicePack.mailer;
import java.lang.Math;   

import java.util.Map;  

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware; 

public class sendOTP  implements SessionAware{
	
	private String emailid;
	private String otp;
	
	private SessionMap<String,Object> sessionMap;
	
	private String randRange(int min,int max)
	{
		return(Integer.toString((int)(Math.random()*(max-min+1)+min)));
	}
	
	public String execute() {
		mailer m = new mailer();
		String randx = randRange(10000,99999);
		sessionMap.put(getEmailid()+"otp",randx);
		m.sendEmail(emailid, "TMS Password Reset OTP", "<h3>THEATER MANAGEMENT SYSTEM</h3><br>Your password reset OTP is <br><h1><strong style=\"color:red;\">"+randx+"</strong></h1>", true);
		return("success");
	}


	public String getEmailid() {
		return emailid;
	}


	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}


	public String getOtp() {
		return otp;
	}


	public void setOtp(String otp) {
		this.otp = otp;
	}

	@Override  
	public void setSession(Map<String, Object> map) {  
	    sessionMap=(SessionMap)map;  
	}
}
