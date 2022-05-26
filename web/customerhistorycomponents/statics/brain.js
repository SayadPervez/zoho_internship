var allshowsjsonlist = [];

var selected_showid = null; 

var validateNumber = 0;

var todayDate = null;

function sortByProperty(property){  
   return function(a,b){  
      if(a[property] > b[property])  
         return 1;  
      else if(a[property] < b[property])  
         return -1;  
  
      return 0;  
   }  
}

$(document).ready(function(){
    $('.modal').modal();
    $('select').formSelect();
    todayDate = new Date().toJSON().slice(0,10);
    $.ajax({
	        type: "POST",
	        url: "/MovieTicketsTiles/myShowsAPI",
	        data: { 
	        },
	        success: function (result,textStatus,xhrreq) {
	        	result = result.split("$-$");
	        	result.forEach((item)=>{
	        		temp = JSON.parse(item.replace(/&quot;/g,'"'));
	    			console.log("temp : ", temp);
	    			x = new Date(temp.date).toJSON().slice(0,10)
	    			console.log("x: ",x);
	    			console.log("Current Date : " , todayDate);
	    			if(x<todayDate)
	    				allshowsjsonlist.push(JSON.parse(JSON.stringify(temp)));
	    			allshowsjsonlist.sort(sortByProperty("date"));
	        	});
	    		
	    		console.log("allshowsjsonlist : ",allshowsjsonlist);
	    		allshowsjsonlist.forEach((item)=>{
			    		append2tbody(item);
				    });
			},
	        error: function(result) {
				toaster("AJAX Erred","white red-text text-accent-4");
	        }
	    });
    
    
    
  });

function append2tbody(jsonobject)
{
	console.log("i got called");
	s = `
	<tr>
		<td>${jsonobject.moviename}</td>
		<td>${jsonobject.theatername}</td>
		<td>${jsonobject.screennumber}</td>
		<td><span class="badge left">${jsonobject.slot}</span></td>
		<td>${jsonobject.duration}</td>
		<td>${jsonobject.date}</td>
	</tr>	
	`
	$("#tbody").html($("#tbody").html()+s)
}

function cleartbody()
{
	$("#tbody").html("");
}

function searchnow()
{
	console.log("Search Button Got Clicked");
	si = $("#searchinput").val()
	ss = $("#searchselect").val()
	cleartbody();
	if((si == null || si == "") && (ss == null || ss==""))
	{
		console.log("all");
		allshowsjsonlist.forEach(item => append2tbody(item));
	}
	else{
		
		allshowsjsonlist.filter((item)=>{
			return((item[ss].toLowerCase()).indexOf(si.toLowerCase())>-1)
		}).forEach(item => append2tbody(item));
	}
}