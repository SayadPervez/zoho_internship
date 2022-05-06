<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Name</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<style>
input{
	text-align:center;
}
</style>
</head>
<body style="width:80%;margin:0 auto;padding:0 1rem">
	<h1 style="text-align:center;">	Data Logger	</h1>
	<form action="dataloggerx" method="post">
	<input type="text" name="name" placeholder="name"><br><br>
	<input type="text" name="age" placeholder="age"><br><br>
	<input type="submit" class="btn-large" style="display:block;margin:auto;">
	</form>
</body>
</html>