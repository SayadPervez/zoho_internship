package dummyclass;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		int a = Integer.parseInt(req.getParameter("num1"));
		int b = Integer.parseInt(req.getParameter("num2"));
		
		RequestDispatcher rd = req.getRequestDispatcher("sq");
		req.setAttribute("sum", a+b);
		rd.forward(req, res);
		
	}
}
