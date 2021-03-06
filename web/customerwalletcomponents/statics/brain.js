var mode=false;

$( document ).ready(function() {
    $("#wallet").prop("disabled",true);
    if($("#username").val()=="")
    {
    	window.location.href="/MovieTicketsTiles/homepage";
    }
});

$("#editbutton").click(function(){
    $("#wallet").prop("disabled",mode);
    mode = !mode;
    if(mode==true)
    {
    	$("#editbutton").html(`<strong>Apply Changes <span class="material-icons">published_with_changes</span></strong>`);
    }
    else{
    	// ajax call here
    	
    	$.ajax({
        type: "POST",
        url: "/MovieTicketsTiles/editwallet",
        data: { 
            wallet : String($("#wallet").val())
        },
        success: function (result,textStatus,xhrreq) {
    		if(result==="success")
    		 {toaster("User Wallet Updated","white green-text text-accent-4");
    		 window.location.href="/MovieTicketsTiles/customerwalletpage";}
    		 else
    		 {
                toaster("Wallet Updation Failed","white red-text text-accent-4");}
		},
        error: function(result) {
            toaster("AJAX Erred","white red-text text-accent-4");
        }
    	});
    	
    	$("#editbutton").html(`<strong>Edit <span class="material-icons">edit</span></strong>`);
    }
});