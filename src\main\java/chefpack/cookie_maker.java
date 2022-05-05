package chefpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

public class cookie_maker extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		String k = req.getParameter("key");
		String v = req.getParameter("value");
		
		Cookie cookie = new Cookie(k,v);
		
		res.addCookie(cookie);	
		res.sendRedirect("/chef/result");
	}
}