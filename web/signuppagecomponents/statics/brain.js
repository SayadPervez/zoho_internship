var type="customer";

window.onload = function(e){
	$("#subicon").css("background-image","url('customer0pngicon')")
}

$("#signup_button").click(function(e) {
    e.preventDefault();
    $.ajax({
        type: "POST",
        url: "/MovieTicketsTiles/signup",
        data: { 
            type : type,
            uname : document.getElementById("uname_signup").value,
            pwd : document.getElementById("pwd_signup").value,
            age : document.getElementById("age_signup").value,
            emailid : document.getElementById("emailid_signup").value
        },
        success: function (result,textStatus,xhrreq) {
    		if(result==="success")
    		 {console.log("redirecting...");
    		 window.location.href="commoncomponents/success.jsp";}
    		 else
    		 {
    		 console.log(result);}
		},
        error: function(result) {
            window.location.href="commoncomponents/error.jsp";
        }
    });
});

$("#type_signup").change(function(e) {
	if(type=="customer")
	{
		$("#subicon").css("background-image","url('ownerpngicon')");
		type = "owner";
	}
	else{
		$("#subicon").css("background-image","url('customer0pngicon')");
		type = "customer";
	}
});