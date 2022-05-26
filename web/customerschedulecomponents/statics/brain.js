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
    todayDate = new Date().toJSON().slice(0,10);
    $('select').formSelect();
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
	    			if(x>=todayDate)
	    				allshowsjsonlist.push(JSON.parse(JSON.stringify(temp)));
	    			allshowsjsonlist.sort(sortByProperty("date"));
	        	});
	    		
	    		console.log("allshowsjsonlist : ",allshowsjsonlist);
	    		allshowsjsonlist.forEach((item)=>{
			    		append2tbody(item);
				    });
			},
	        error: function(result) {
	            alert('AJAX Erred');
	        }
	    });
    
    
    
  });
  
$("#submitbutton").click(()=>{
	console.log("Submit button clicked. Take me to seat bookings page")
	console.log("Selected show id", selected_showid);
	$.ajax({
	        type: "POST",
	        url: `/MovieTicketsTiles/cancelShows`,
	        data: { 
	        	sid:selected_showid
	        },
	        success: function (result,textStatus,xhrreq) {
	        	alert(result);
	        	location.reload();
			},
	        error: function(result) {
	            alert('AJAX Erred');
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
		<td class="myflatbutton" onclick="bookfunc(this.id)" id="book_${jsonobject.theaternumber}|${jsonobject.screennumber}|${jsonobject.date}|${jsonobject.slot}">
			Cancel Show <span class="material-icons-outlined offset">event_busy</span>
		</td>
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
	
function bookfunc(id)
{
	var instance = M.Modal.getInstance($("#modal1"));
	instance.open();
	id = id.replace("book_","")
	tempx = allshowsjsonlist.filter((obj)=>{
	if(obj.theaternumber+"|"+obj.screennumber+"|"+obj.date+"|"+obj.slot === id)
	{
		selected_showid = id;
		return(true);}
	else
		return(false);
	});
	console.log("tempx : \n",tempx)
	htmlstring = `
	<div class="row">
		<div class="col s5">
			<h5>Movie Name </h5><br>
			<h5>Theater Name </h5><br>
			<h5>Screen </h5><br>
			<h5>Time Slot </h5><br>
			<h5>Date </h5><br>
		</div>
		<div class="col s1">
			<h5>: </h5><br>
			<h5>: </h5><br>
			<h5>: </h5><br>
			<h5>: </h5><br>
			<h5>: </h5><br>
		</div>
		<div class="col s6">
			<h5>${tempx[0]["moviename"]} </h5><br>
			<h5>${tempx[0]["theatername"]} </h5><br>
			<h5>${tempx[0]["screennumber"]} </h5><br>
			<h5>${tempx[0]["slot"]} </h5><br>
			<h5>${tempx[0]["date"]} </h5><br>
		</div>
	</div>
	`;
	$("#ticketinfodiv").html(htmlstring);
}	