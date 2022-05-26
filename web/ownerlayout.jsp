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
<%@  include file="/commoncomponents/headerhome.jsp" %>  
 
		<div class="row" id="mainContainer">
			<div id="sidetab" class="col l2 tabs">
				<div class="circle z-depth-3 white"></div>
				<h4 style="font-weight:bold;" class="center-align">Theater Owner</h4>
				<h6 class="center-align" style="font-weight:500;color:#039be5"><s:property value="username" /></h6>
				<div id="sidenav">
					<tiles:insertAttribute name="sidetab" />
				</div>
			</div>
			<div id="maintab" class="col l10 tabs" style="overflow-y: visible;">
				<tiles:insertAttribute name="maintab" /> 
			</div>
			
		</div>
<%@ include file="/commoncomponents/footerhome.jsp" %>  
</div>
<script>
$(document).ready(function() { 
	$(".activeclass").prop("disabled",true);
});
$("#mprofile").click(function(e) {
    window.location.href="/MovieTicketsTiles/ownerprofilepage";
});
$("#mshows").click(function(e) {
    window.location.href="/MovieTicketsTiles/ownershowspage";
});
$("#mwallet").click(function(e) {
    window.location.href="/MovieTicketsTiles/ownerwalletpage";
});
$("#mtheaters").click(function(e) {
    window.location.href="/MovieTicketsTiles/ownertheaterspage";
});
$("#logout").click(function(e) {
    window.location.href="/MovieTicketsTiles/logout";
});
</script>
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
<tiles:insertAttribute name="bodyImports" />
</body>  
</html>   