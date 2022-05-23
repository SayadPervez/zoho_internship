<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
 "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta charset="UTF-8">
<meta name = "viewport" content = "width = device-width, initial-scale = 1">
<title><tiles:getAsString name="title" /></title>  

<tiles:insertAttribute name="headImports" />
</head>  
<body>  
<div class="overlay">
<%@  include file="/commoncomponents/header.jsp" %>  
 
		<div class="row" id="mainContainer">
			
			<div id="maintab" class="col s12 tabs" style="overflow-y: visible;">
				<tiles:insertAttribute name="maintab" /> 
			</div>
			
		</div>
<%@ include file="/commoncomponents/footerhome.jsp" %>  
</div>
<tiles:insertAttribute name="bodyImports" />
</body>  
</html>   