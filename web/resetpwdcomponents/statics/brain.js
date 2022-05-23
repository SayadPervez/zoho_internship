$("#setpwd").click(function(e) {
if($("#pwd").val()==$("#pwdc").val())
{
			    $.ajax({
			        type: "POST",
			        url: "/MovieTicketsTiles/resetpwd",
			        data: { 
			            pwdc : document.getElementById("pwdc").value
			        },
			        success: function (result,textStatus,xhrreq) {
			    		if(result==="success")
			    		 {
			    		 	window.location.href="/MovieTicketsTiles/ownerprofilepage";
							}
			    		 else
			    		 {
			    		 window.location.href="commoncomponents/error.jsp";
			    		 }
					},
			        error: function(result) {
			            alert('error');
			        }
			    });
}
else{
	alert("Password do not match !!");
}
});