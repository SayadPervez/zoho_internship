var flag=false;

$(document).ready(function() { $("#otpdiv").css("visibility","hidden"); });

function showOTPdiv()
{
$("#otpdiv").css("visibility","visible");
if(flag==false)
{
	flag=true;
	$("#forgot_button").text("Submit OTP");
	$("#emaildiv").css("display","none");
}
}

$("#forgot_button").click(function(e) {
    e.preventDefault();
    if(flag==false)
    {$("#forgot_button").text("Loading ...")
			    $.ajax({
			        type: "GET",
			        url: "sendotp",
			        data: { 
			            emailid : document.getElementById("emailid_forgot").value,
			            otp : "none"
			        },
			        success: function (result,textStatus,xhrreq) {
			    		if(result==="success")
			    		 {
			    		 	showOTPdiv();
							}
			    		 else
			    		 {
			    		 ("#forgot_button").text("Send OTP")
			    		 window.location.href="commoncomponents/error.jsp";
			    		 }
					},
			        error: function(result) {
			            toaster("AJAX Erred","white red-text text-accent-4");
			        }
			    });
	}
	else{
		console.log("already otp is sent");
		
		
		
		$.ajax({
			        type: "GET",
			        url: "validateotp",
			        data: { 
			            emailid : document.getElementById("emailid_forgot").value,
			            otp : document.getElementById("otp_forgot").value
			        },
			        success: function (result,textStatus,xhrreq) {
			    		if(result==="success")
			    		 {
			    		 	window.location.href="/MovieTicketsTiles/resetpasswordpage";
							}
			    		 else
			    		 {
			    		 window.location.href="commoncomponents/error.jsp";
			    		 }
					},
			        error: function(result) {
			            toaster("AJAX Erred","white red-text text-accent-4");
			        }
			    });
		
		
		
		
		
		
		
		
		
		
	}
});