var allshowsjsonlist = [];

var selected_showid = null; 

var validateNumber = 0;

$(document).ready(function(){
    $('.modal').modal();
    $('select').formSelect();
    
    $.ajax({
	        type: "POST",
	        url: "/MovieTicketsTiles/viewallshowsapi",
	        data: { 
	        },
	        success: function (result,textStatus,xhrreq) {
	    		temp = JSON.parse(result.replace(/&quot;/g,'"'));
	    		Object.keys(temp).forEach((val,ind)=>{
	    			temp2  = JSON.parse(temp[val]);
	    			temp2.seats = JSON.parse(temp2.seats);
	    			allshowsjsonlist.push(JSON.parse(JSON.stringify(temp2)));
	    		});
	    		console.log(allshowsjsonlist);
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
	console.log("SUbmit button clicked. Take me to seat bookings page")
	console.log("Selected show id", selected_showid);
	window.location.href = `/MovieTicketsTiles/customerSeats?sid=${selected_showid.replaceAll("|","$")}`;
});

function append2tbody(jsonobject)
{
	console.log("i got called");
	s = `
	<tr>
		<td>${jsonobject.moviename}</td>
		<td>${jsonobject.theatername}</td>
		<td>${jsonobject.location}</td>
		<td>${jsonobject.screennumber}</td>
		<td><span class="badge left">${jsonobject.slot}</span></td>
		<td>${jsonobject.duration}</td>
		<td>${jsonobject.date}</td>
		<td class="myflatbutton" onclick="bookfunc(this.id)" id="book_${jsonobject.theaternumber}|${jsonobject.screennumber}|${jsonobject.date}|${jsonobject.slot}">
			Book Tickets <span class="material-icons-outlined offset">local_activity</span>
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
			<h5>Location </h5><br>
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
			<h5>: </h5><br>
		</div>
		<div class="col s6">
			<h5>${tempx[0]["moviename"]} </h5><br>
			<h5>${tempx[0]["theatername"]} </h5><br>
			<h5>${tempx[0]["location"]} </h5><br>
			<h5>${tempx[0]["screennumber"]} </h5><br>
			<h5>${tempx[0]["slot"]} </h5><br>
			<h5>${tempx[0]["date"]} </h5><br>
		</div>
	</div>
	`;
	$("#ticketinfodiv").html(htmlstring);
}	