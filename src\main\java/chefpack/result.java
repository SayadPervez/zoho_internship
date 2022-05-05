package chefpack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

public class result extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		Cookie cookies[] = req.getCookies();
		
		String pri = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<head>\n" + 
				"<meta charset=\"UTF-8\">\n" + 
				"<title>Result</title>\n" + 
				"<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css\">\n" + 
				"</head>\n" + 
				"<body style=\"width:90%;margin:0 auto;\">\n" + 
				"	<h1 style=\"text-align:center;\">Cookie Collector</h1><br><br><br>\n"; 
		String pro = "		\n" + 
				"</body>\n" + 
				"</html>";
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/html; charset=utf-8");
		
		out.print(pri);
		for (Cookie c:cookies)
		{
			out.print("<h3>"+c.getName()+" >>> "+c.getValue()+"</h3><br>");
		}
		out.print(pro);
	}
}