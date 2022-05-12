package actionPack;

import servicePack.mailer;
import java.lang.Math;   

public class sendOTP {
	
	private String emailid;
	private String otp;
	
	private String randRange(int min,int max)
	{
		return(Integer.toString((int)(Math.random()*(max-min+1)+min)));
	}
	
	public String execute() {
		mailer m = new mailer();
		m.sendEmail(emailid, "TMS Password Reset OTP", "<h3>THEATER MANAGEMENT SYSTEM</h3><br>Your password reset OTP is <strong style=\"color:red;\">"+randRange(1000,9999)+"</strong>", true);
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
}
