var jsondata = {};
var jsonli = [];
var timeslots=[];
var timeslot_state= true;
var actualid = null;

timeslotdiv = "";

  $(document).ready(function(){
    $('.modal').modal();
    
  });
  
$(document).ready(()=>{
	var li =($("#jsonString_struts").text().split("$"));
	var lix =($("#seating_struts").text().split("$"));
	var liref =($("#refid_struts").text().split("$"));
	li.forEach((item,index)=>{
		jsonli.push(JSON.parse(item));
		jsonli[index]["timeslots"] = (jsonli[index]["timeslots"]).split(";") 
		jsonli[index]["seatstats"] = lix[index]=="null"?`<span class="material-icons red-text">dangerous</span>`:`<span class="material-icons green-text">check_circle</span>`;
		jsonli[index]["refid"] = liref[index];
	});
	//console.log(jsonli);
	addToTheatersDiv(jsonli);
		timeslot_state= false;
});
  
$("#modalbutton").click( ()=>{
	timeslots=[];
	var instance = M.Modal.getInstance($("#modal1"));
	instance.open();
	$("#showcasediv").html("");
}
);

function setupfunc(id){
	var act_id = id.replace("setup_","");
	actualid = act_id;
	console.log(act_id);
	var li =($("#jsonString_struts").text().split("$"));
	var liref =($("#refid_struts").text().split("$"));
	_index_ = liref.indexOf(act_id);
	var temp = jsonli[_index_]
	$("#modal2_theatername").val(temp["theatername"]);
	$("#modal2_theaterlocation").val(temp["theaterlocation"]);
	$("#modal2_noofscreens").val(temp["noofscreens"]);
	$("#showcasediv2").html($("#tstd_"+act_id).html());
	$("#actid").text(act_id);
	// open edit modal here
	timeslot_state = false;
	var instance = M.Modal.getInstance($("#modal2"));
	instance.open();
}

function deletefunc(id){
	var act_id_ = id.replace("delete_","");
	console.log(act_id_);
	// deleteion ajax call here
			$.ajax({
						        type: "POST",
						        url: "/MovieTicketsTiles/deleteTheater",
						        data: { 
						            refid : act_id_
						        },
						        success: function (result,textStatus,xhrreq) {
						    		window.location.href="/MovieTicketsTiles/ownertheaterspage";
								},
						        error: function(result) {
						            toaster("AJAX Erred","white red-text text-accent-4");
						        }
						    });
}

function seatfunc(id){
	var act_id = id.replace("seat_","");
	console.log(act_id);
	// seating ajax call here
	window.location.href="/MovieTicketsTiles/ownerSeats?refid="+act_id;
}

$("#fab_reset").click( ()=>{
	timeslots = [];
	timeslot_state= true;
	timeslotdiv = "";
	timeslots.forEach((item)=>{
		timeslotdiv += `<span class="badge left">${item}</span>`;
	});
	$("#showcasediv2").html("");
	$("#fab_reset").css("display","none");
	$("#fab2").css("display","inline-block");
}
);

$("#fab").click( ()=>{
	x = $("#modal_time").val()
	if(timeslots.indexOf(x)==-1 && x!="")
		timeslots.push(x);
	timeslotdiv = "";
	timeslots.forEach((item)=>{
		timeslotdiv += `<span class="badge left">${item}</span>`;
	});
	$("#showcasediv").html(timeslotdiv);
}
);

$("#fab2").click( ()=>{
	x = $("#modal2_time").val()
	if(timeslots.indexOf(x)==-1 && x!="")
		timeslots.push(x);
	timeslotdiv = "";
	timeslots.forEach((item)=>{
		timeslotdiv += `<span class="badge left">${item}</span>`;
	});
	$("#showcasediv2").html(timeslotdiv);
}
);

function addToTheatersDiv(data){
	// data should be a list/array of json objects
	tbodystring = ``;
	
	data.forEach((item)=>{
		var timeslotstring = "";
		if(timeslot_state == false)
		{
							$.ajax({
				        type: "POST",
				        url: "/MovieTicketsTiles/getTimeslotString",
				        data: { 
				            refid : actualid
				        },
				        success: function (result,textStatus,xhrreq) {
				    		timeslotstring = result;
				    		console.log(result);
						},
				        error: function(result) {
				            toaster("AJAX Erred","white red-text text-accent-4");
				        }
				    });
		}
		else
		{
				item.timeslots.forEach((timeslot,index__)=>{
					timeslotstring+=`<span class="badge">${timeslot}</span>`;
					timeslotstring+= (index__+1)%3==0? `<br>`:" ";
				});
		}
		tbodystring += `<tr id="row_${item.refid}"><td>${item.theatername}</td>
		<td>${item.theaterlocation}</td>
		<td>${item.noofscreens}</td>
		<td id="tstd_${item.refid}">${
		timeslotstring
		}</td>
		<td>
			<span class="material-icons setup" id="setup_${item.refid}" onclick="setupfunc(this.id)">settings</span>
			<span class="material-icons seatlayout" id="seat_${item.refid}"  onclick="seatfunc(this.id)">chair</span>
			<span class="material-icons delete" id="delete_${item.refid}" onclick="deletefunc(this.id)">delete_forever</span>			
		</td>
		<td>
			${item.seatstats}
				
							
		</td></tr>
		`;
	});
	$("tbody").html($("tbody").html()+tbodystring);
}

$("#savechanges").click( ()=>{
	timeslots.sort();
	var temp = [];
	timeslots.forEach((item)=>{
		x=item.split(":").map(Number);
		if(x[0]>12)
		{
			temp.push(String(x[0]-12)+":"+String(x[1])+" PM");
		}
		else if (x[0]==12)
			temp.push(item+" PM");
		else{
			temp.push(item+" AM");
		}
		
	});
	jsondata["theatername"] = $("#modal2_theatername").val();
	jsondata["theaterlocation"] = $("#modal2_theaterlocation").val();
	jsondata["noofscreens"] = $("#modal2_noofscreens").val();
	jsondata["timeslots"] = temp;
	console.log(jsondata);	
	jsondata["timeslots"] = temp.join(";");
	jsonString = JSON.stringify(jsondata);
	console.log(jsonString);
	
	$.ajax({
        type: "POST",
        url: "/MovieTicketsTiles/editTheaters",
        data: { 
            jsonString : jsonString,
            refid : $("#actid").text()
        },
        success: function (result,textStatus,xhrreq) {
    		if(result==="success")
    		 {window.location.href="/MovieTicketsTiles/ownertheaterspage";
    		 $("#actid").text("")}
    		 else
    		 {toaster("JSP Returned Error","white red-text text-accent-4");;
    		 console.log(result)}
		},
        error: function(result) {
            toaster("AJAX Erred","white red-text text-accent-4");
        }
    });
	

}
);

$("#submitbutton").click( ()=>{
	timeslots.sort();
	var temp = [];
	timeslots.forEach((item)=>{
		x=item.split(":").map(Number);
		if(x[0]>12)
		{
			temp.push(String(x[0]-12)+":"+String(x[1])+" PM");
		}
		else if (x[0]==12)
			temp.push(item+" PM");
		else{
			temp.push(item+" AM");
		}
		
	});
	jsondata["theatername"] = $("#modal_theatername").val();
	jsondata["theaterlocation"] = $("#modal_theaterlocation").val();
	jsondata["noofscreens"] = $("#modal_noofscreens").val();
	jsondata["timeslots"] = temp;
	console.log(jsondata);	
	addToTheatersDiv([jsondata]);
	jsondata["timeslots"] = temp.join(";");
	jsonString = JSON.stringify(jsondata);
	console.log(jsonString);
	
	$.ajax({
        type: "POST",
        url: "/MovieTicketsTiles/addTheaters",
        data: { 
            jsonString : jsonString
        },
        success: function (result,textStatus,xhrreq) {
    		if(result==="success")
    		 {window.location.href="/MovieTicketsTiles/ownertheaterspage";}
    		 else
    		 {toaster("JSP Returned Error","white red-text text-accent-4");
    		 console.log(result)}
		},
        error: function(result) {
            toaster("AJAX Erred","white red-text text-accent-4");
        }
    });
	
	
}
);
