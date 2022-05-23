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
		
		Statement st = con.createStatement();
		int rs = st.executeUpdate("INSERT INTO creds (username,emailid,age,password,type) VALUES(\""+__uname+"\",\""+__emailid+"\","+__age+",\""+__password+"\",\""+__type+"\")");
		
		st.close();
		con.close();
		
		System.out.println("NO ERRORS in jdbc signup");
		if(rs==1)
			return(true);
		else
			return(false);
	}
}
