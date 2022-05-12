package servicePack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class signupService {
public Boolean signup(String __uname, String __password,String __type,String __age, String __emailid) throws Exception{
		
		String ipAddressMYSQL = "127.0.0.1";
		String portMYSQL = "3306";
		String databaseName = "tms";
		String urlMYSQL = "jdbc:mysql://"+ipAddressMYSQL+":"+portMYSQL+"/"+databaseName;
		System.out.println(urlMYSQL);
		
		String usernameMYSQL = "root";
		String passwordMYSQL = "root123";
		
		Class.forName("com.mysql.cj.jdbc.Driver");	
		Connection con = DriverManager.getConnection(urlMYSQL,usernameMYSQL,passwordMYSQL);
		
		Statement st2 = con.createStatement();
		int rs2 = st2.executeUpdate("INSERT INTO lookup_creds (username,emailid,usertype) VALUES(\""+__uname+"\",\""+__emailid+"\",\""+__type+"\")");
		
		Statement st = con.createStatement();
		int rs = st.executeUpdate("INSERT INTO "+__type+"_creds (username,emailid,age,password) VALUES(\""+__uname+"\",\""+__emailid+"\","+__age+",\""+__password+"\")");
		
		
		
		
		st.close();
		st2.close();
		con.close();
		
		System.out.println("NO ERRORS in jdbc signup");
		if(rs==1&&rs2==1)
			return(true);
		else
			return(false);
	}
}
