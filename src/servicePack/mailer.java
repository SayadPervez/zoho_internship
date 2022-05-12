package servicePack;

import javax.mail.Authenticator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.Session; 

public class mailer {
	
	private static Message prepareMessage(Session session,String username,String recepient,String subject,String actmsg,Boolean html) 
	{
		Message message=new MimeMessage(session);
			try 
			{
				message.setFrom(new InternetAddress(username));
				message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
				message.setSubject(subject);
				if(html)
					message.setContent(actmsg, "text/html; charset=utf-8");
				else
					message.setText(actmsg);
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
	return message;
	}
	
	public String sendEmail(String receiptant,String subject,String actualmessage,Boolean html){ 
		
		Properties properties=new Properties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable","true");
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

		String username="recoin.portal@gmail.com";
		String password="hesoyamfullclip";
		Session session= Session.getInstance(properties, new Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username,password);
		}
		});

		Message msg=prepareMessage(session,username,receiptant,subject,actualmessage,html);
		try {
			Transport.send(msg);
			System.out.println("Mail sent");return("success");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Mail error");			
			return("failure");
		}
		}
}
