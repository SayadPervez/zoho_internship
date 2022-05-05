<html>
<head>
<title>
Result
</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>  
<body style="width:90%;margin:0 auto;">
<h2 style="font-weight:700;">Result :</h2> 
<%  
String name=request.getParameter("uname");  
String pwd=request.getParameter("pwd");
String spwd=request.getParameter("savedpwd");
String sun=request.getParameter("saveduname");
Boolean aa=session.getAttribute("uxx").equals(name);
Boolean bb=session.getAttribute("pxx").equals(pwd);
%>   
<h3 class="<%=(pwd.equals(spwd)&&(sun.equals(name)) ? "Green" : "Red")%>-text" >
<%=(pwd.equals(spwd)&&(sun.equals(name)) ? "Success" : "Failure")%></h3>
</body>  
</html>  