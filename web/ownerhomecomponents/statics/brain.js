var mode=false;

$( document ).ready(function() {
    $("#username").prop("disabled",true);
	$("#emailid").prop("disabled",true);
    $("#age").prop("disabled",true);
    $("#type").prop("disabled",true);
    $("#wallet").prop("disabled",true);
    if($("#username").val()=="")
    {
    	window.location.href="/MovieTicketsTiles/homepage";
    }
});

$("#editbutton").click(function(){
    $("#username").prop("disabled",mode);
	$("#emailid").prop("disabled",mode);
    $("#age").prop("disabled",mode);
    console.log(String($("#age").val()));
    mode = !mode;
    if(mode==true)
    {
    	$("#editbutton").html(`<strong>Apply Changes <span class="material-icons">published_with_changes</span></strong>`);
    }
    else{
    	// ajax call here
    	
    	$.ajax({
        type: "POST",
        url: "/MovieTicketsTiles/applychanges",
        data: { 
            uname : $("#username").val(),
            emailid : $("#emailid").val(),
            myage : String($("#age").val())
        },
        success: function (result,textStatus,xhrreq) {
    		if(result==="success")
    		 {toaster("User Data Updated","white green-text text-accent-4");
    		 window.location.href="/MovieTicketsTiles/homepage";}
    		 else
    		 {
                toaster("User Data Updation Failed","white red-text text-accent-4");}
		},
        error: function(result) {
            toaster("AJAX Erred","white red-text text-accent-4");
        }
    	});
    	
    	$("#editbutton").html(`<strong>Edit <span class="material-icons">edit</span></strong>`);
    }
});