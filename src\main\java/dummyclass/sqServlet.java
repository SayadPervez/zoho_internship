package dummyclass;

import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class sqServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		int a = Integer.parseInt( req.getParameter("sum") );
		
		PrintWriter out = res.getWriter();
		
		out.print("The total plus square is "+(a*a));
	}
}
