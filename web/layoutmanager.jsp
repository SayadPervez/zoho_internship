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
<tiles:insertAttribute name="body" />  
<%@ include file="/commoncomponents/footer.jsp" %>  
</div>
<tiles:insertAttribute name="bodyImports" />
<script>
function toaster(data,classes="",rounded=true)
{
  if(rounded)
    M.toast({html: "<strong>"+data+"</strong>", classes: 'rounded '+classes ,displayLength:1500});
  else
    M.toast({html: "<strong>"+data+"</strong>", classes: classes ,displayLength:1500});
}
function waiter(data,classes="",rounded=true)
{
  if(rounded)
    M.toast({html: "<strong>"+data+"</strong>", classes: 'rounded '+classes ,displayLength:30000});
  else
    M.toast({html: "<strong>"+data+"</strong>", classes: classes ,displayLength:30000});
}
</script>
</body>  
</html>   