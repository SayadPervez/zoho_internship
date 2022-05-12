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
        type: "GET",
        url: "login",
        data: { 
            type : type,
            uname : document.getElementById("uname_login").value,
            pwd : document.getElementById("pwd_login").value
        },
        success: function (result,textStatus,xhrreq) {
    		if(result==="success")
    		 {console.log("redirecting...");
    		 window.location.href="commoncomponents/success.jsp";}
    		 else
    		 {
    		 window.location.href="commoncomponents/error.jsp";}
		},
        error: function(result) {
            alert('error');
        }
    });
});