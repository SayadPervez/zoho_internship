<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>  
<head>
<title>Login Page</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>
<body style="width:90%;margin:0 auto;">  
<h2 style="font-weight:700;">Login</h2>
<%
String u = request.getParameter("uname");
String p = request.getParameter("pwd");
session.setAttribute("uxx",u);
session.setAttribute("pxx",p);
%>
<form action="rsult.jsp">  
<input type="text" name="uname" placeholder="Username"><br><br>
<input type="text" name="pwd" placeholder="Password"><br><br>
<input type="text" name="saveduname" style="display:none;" value="<%= u%>">
<input type="text" name="savedpwd" style="display:none;" value="<%= p%>">
<input class="btn-large" type="submit" value="Login"><br/>  
</form>  
</body>  
</html> 