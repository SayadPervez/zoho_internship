var myTheatersList = [];
var theatersJson;
var mytimeslots=[];

var validateNumber = 0;

$(document).ready(function(){
    $('.modal').modal();
    $('select').formSelect();
    $('.datepicker').datepicker();
    
    $.ajax({
        type: "POST",
        url: "/MovieTicketsTiles/getmytheaterslist",
        data: { 
            
        },
        success: function (result,textStatus,xhrreq) {
    		temp0 = JSON.parse(result.replace(/&quot;/g,'"'));
    		temp0.refid.forEach((val,index)=>{
    					$.ajax({
					        type: "POST",
					        url: "/MovieTicketsTiles/viewmyshowsapi",
					        data: { 
					            tref:val
					        },
					        success: function (result,textStatus,xhrreq) {
					    		x = JSON.parse(result.replace(/&quot;/g,'"'))
					    		Object.keys(x).forEach(function(key, idx) {
									  append2tbody(x[key]); 
									  console.log(x[key]);
									}); 
							},
					        error: function(result) {
					            alert('AJAX Erred');
					        }
					    });		
    		});
		},
        error: function(result) {
            alert('AJAX Erred');
        }
    });
    
	/*    $.ajax({
        type: "POST",
        url: "/MovieTicketsTiles/viewmyshowsapi",
        data: { 
            
        },
        success: function (result,textStatus,xhrreq) {
    		
		},
        error: function(result) {
            alert('AJAX Erred');
        }
    });*/
    
  });
  
$("#modalbutton").click( ()=>{
	var instance = M.Modal.getInstance($("#modal1"));
	instance.open();
	$.ajax({
        type: "POST",
        url: "/MovieTicketsTiles/getmytheaterslist",
        data: { 
            
        },
        success: function (result,textStatus,xhrreq) {
    		temp0 = JSON.parse(result.replace(/&quot;/g,'"'));
    		tselectstring = `<option value="" disabled selected>SELECT A THEATER</option>`;
    		temp0.theatersjson.forEach((val,index)=>{
    			temp0.theatersjson[index] = JSON.parse(val); 
    			temp0.theatersjson[index]["theater_ref_id"] = temp0.refid[index];
    			tselectstring+=`<option value="${temp0.theatersjson[index]["theater_ref_id"]}">${temp0.theatersjson[index]["theatername"]}</option> `
    			myTheatersList.push(temp0.theatersjson[index]["theatername"]);
    		});
    		if(myTheatersList.length===0)
    			$("#theaters_select").html(`<option value="" disabled selected>NO THEATERS FOUND</option>`);
    		else
    			$("#theaters_select").html(tselectstring);
    		$('select').formSelect();
    		console.log(temp0);
    		theatersJson = temp0;
    		validateNumber+=1;
		},
        error: function(result) {
            alert('AJAX Erred');
        }
    });
	
}
);

$("#theaters_select").change(()=>{
	console.log("Selected Theater Changed to ",$("#theaters_select").val());
	theatersJson.theatersjson.forEach((val,index)=>{
		if(val.theater_ref_id==$("#theaters_select").val())
			{mytimeslots = val.timeslots.split(";");
			$(`#totnoofscreens`).html(val.noofscreens);
			}
			
	});
	if(mytimeslots.length == 0)
	{
		$("#theaters_slot").html(`<option value="" disabled selected>NO SLOTS CONFIGURED</option>`);
	}
	else{
		tslotstring = `<option value="" disabled selected>SELECT A TIME SLOT</option>`;
		mytimeslots.forEach((val)=>{
			tslotstring+=`<option value="${val}">${val}</option>`;
		});
		$("#theaters_slot").html(tslotstring);
		validateNumber+=1;
	}
});

$("#theaters_slot").change(()=>{
	console.log("Time slot string changed to ",$("#theaters_slot").val());
	validateNumber+=1;
	if(validateNumber>2)
	{
		// next part of input unlocked
		$("#inputdiv2").css("display","block");
	}
});

$("#submitbutton").click(()=>{
	var submitresult = {
	theaternumber : $("#theaters_select").val(),
	screennumber : $("#screennumber").val(),
	slot : $("#theaters_slot").val(),
	moviename : $("#moviename").val(),
	duration : $("#movieduration").val(),
	starting : $("#startingdate").val(),
	closing : $("#closingdate").val()
	};
	theatersJson.theatersjson.forEach((val,index)=>{
		if(val.theater_ref_id==$("#theaters_select").val())
			{submitresult.theatername = val.theatername;
			}
			
	});
	console.log(submitresult);
	var getDaysArray = function(start, end) {
		    for(var arr=[],dt=new Date(start); dt<=new Date(end); dt.setDate(dt.getDate()+1)){
		        arr.push(new Date(dt));
		    }
	    return arr;
	};
	var daylist = getDaysArray(new Date(submitresult.starting),new Date(submitresult.closing));
	dateList = ((daylist.map((v)=>v.toISOString().slice(0,10)).join(";")).split(";"))

	finalPush = [];
	
	dateList.forEach((val,index)=>{
		submitresult.showid = submitresult.theaternumber +"|"+ submitresult.screennumber +"|"+ val +"|"+ submitresult.slot;
		submitresult.date = val;
		finalPush.push(JSON.parse(JSON.stringify(submitresult)));
	});
	
	console.log("finalPush : >>>");
	console.log(finalPush);
	
	finalPush.forEach((val)=>{
			$.ajax({
	        type: "POST",
	        url: "/MovieTicketsTiles/addshowsapi",
	        data: { 
	   			jsondata : JSON.stringify(val)         
	        },
	        success: function (result,textStatus,xhrreq) {
	    		console.log(result);
	    		
			},
	        error: function(result) {
	            alert('AJAX Erred');
	        }
	    });
	});
	window.location.href = "/MovieTicketsTiles/ownershowspage";
});

function deletefunc(id)
{
	actid = id.replace("delete_","");
	console.log(actid);
	$.ajax({
					        type: "POST",
					        url: "/MovieTicketsTiles/deletemyshowsownerapi",
					        data: { 
					            showid:actid
					        },
					        success: function (result,textStatus,xhrreq) {
					    		window.location.href = `/MovieTicketsTiles/ownershowspage`
							},
					        error: function(result) {
					            alert('AJAX Erred');
					        }
					    });	
}

function seatfunc(id)
{
	actid = id.replace("seat_","");
	console.log(actid);
}

function setupfunc(id)
{
	actid = id.replace("setup_","");
	console.log(actid);
}

function append2tbody(jsonobject)
{
	s = `
	<tr>
		<td>${jsonobject.theatername}</td>
		<td>${jsonobject.screennumber}</td>
		<td><span class="badge left">${jsonobject.slot}</span></td>
		<td>${jsonobject.moviename}</td>
		<td>${jsonobject.duration}</td>
		<td>${jsonobject.date}</td>
		<td>
			<span class="material-icons setup" id="setup_${jsonobject.theaternumber}|${jsonobject.screennumber}|${jsonobject.date}|${jsonobject.slot}" onclick="setupfunc(this.id)">settings</span>
			<span class="material-icons seatlayout" id="seat_${jsonobject.theaternumber}|${jsonobject.screennumber}|${jsonobject.date}|${jsonobject.slot}"  onclick="seatfunc(this.id)">chair</span>
			<span class="material-icons delete" id="delete_${jsonobject.theaternumber}|${jsonobject.screennumber}|${jsonobject.date}|${jsonobject.slot}" onclick="deletefunc(this.id)">delete_forever</span>
		</td>
	</tr>	
	`
	$("#tbody").html($("#tbody").html()+s)
}