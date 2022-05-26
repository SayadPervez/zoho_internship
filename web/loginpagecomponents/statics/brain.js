var type="none";

window.onload = function(e){
	if(document.getElementById('iconpathdummy').innerText=="customer0pngicon")
	{
		document.getElementById("subicon").style.backgroundImage="url('customer0pngicon')";
		type = "customer"
	}
	else
	{
		document.getElementById("subicon").style.backgroundImage="url('ownerpngicon')";
		type = "owner"
	}
}

$("#login_button").click(function(e) {
    e.preventDefault();
    $.ajax({
        type: "POST",
        url: "login",
        data: { 
            type : type,
            uname : document.getElementById("uname_login").value,
            pwd : document.getElementById("pwd_login").value
        },
        success: function (result,textStatus,xhrreq) {
    		if(result==="success")
    		 {console.log("redirecting...");
    		 window.location.href="/MovieTicketsTiles/homepage";}
    		 else
    		 {
    		 toaster("Check your credentials again !!","white red-text text-accent-4");}
		},
        error: function(result) {
            toaster("Check your credentials again !!","white red-text text-accent-4");
        }
    });
});