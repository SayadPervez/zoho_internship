<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Database Entries</title>
</head>
<body>
<h1>Database Contents :</h1>
<%
String x = (String)session.getAttribute("hts");
if(x==null)
{
	response.sendRedirect("dataloggerx");
}
else
	out.print(x);
session.removeAttribute("hts");
%>
</body>
</html>