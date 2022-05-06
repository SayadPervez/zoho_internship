package dataLoggerpack;

import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.LinkedList;

public class datalogger extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		String name = (String)req.getParameter("name");
		String age = req.getParameter("age");
		System.out.println("name: "+name+"\n\nage: "+age);
		try {putData(name,age);
		} catch (Exception e) {
			e.printStackTrace();
		}
		res.sendRedirect("allData.jsp");
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		String name = (String)req.getParameter("name");
		String age = req.getParameter("age");
		System.out.println("name: "+name+"\n\nage: "+age);
		try {
			String hts = putData(null,null);
			HttpSession session=req.getSession(); 
			session.setAttribute("hts",hts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		res.sendRedirect("allData.jsp");
	}
	
	public static String putData(String name,String age) throws Exception
	{
		String ipAddressMYSQL = "127.0.0.1";
		String portMYSQL = "3306";
		String databaseName = "testdb";
		String urlMYSQL = "jdbc:mysql://"+ipAddressMYSQL+":"+portMYSQL+"/"+databaseName;
		System.out.println(urlMYSQL);
		
		String usernameMYSQL = "root";
		String passwordMYSQL = "root123";
		
		Class.forName("com.mysql.cj.jdbc.Driver");	
		Connection con = DriverManager.getConnection(urlMYSQL,usernameMYSQL,passwordMYSQL);
		
		if(name!=null && age!=null)
		{	
			Statement st = con.createStatement();
			st.executeUpdate("INSERT into datalogger(name,age) VALUES('"+name+"',"+age+")");
			st.close();
		}
		
		Statement st2 = con.createStatement();
		ResultSet rs = st2.executeQuery("SELECT * FROM datalogger");
		String htmlString = "";
		while(rs.next())
		{
			String namex = rs.getString("name");
			int uid = rs.getInt("uid");
			int agex = rs.getInt("age");
			htmlString += "<h3>"+uid+" -> "+namex+" : "+agex+" years old</h3>";
		}
		st2.close();
		
		con.close();
		
		return(htmlString);
	}
}
