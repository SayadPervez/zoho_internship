package dbassistPack;

import java.sql.ResultSet;

import servicePack.DQLquery;

public class userfunctions {
	public Boolean isUser(String username)
	{
		DQLquery dql = new DQLquery();
		try {
			int count = dql.run("SELECT * FROM creds WHERE username=\""+username+"\" OR emailid=\""+username+"\"",-1);
			if(count==0)
				return(false);
			return(true);
		} catch (Exception e) {
			e.printStackTrace();
			return(false);
		}
	}
	public String getUserType(String username)
	{
		DQLquery dql = new DQLquery();
		try {
			String type = dql.run("SELECT * FROM creds WHERE username=\""+username+"\" OR emailid=\""+username+"\"","type");
			System.out.println("usefuntions.getUseType returning "+type);
			return(type);
			
		} catch (Exception e) {
			e.printStackTrace();
			return("error");
		}		
	}
}
