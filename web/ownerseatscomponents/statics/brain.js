var mode = "normal";
var modeObj = document.getElementById("modebadge");
const alphabet = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var dbseats;
var myrefid;

var historyx = [];


var seatsJson = {};

function undo()
{
	console.log("Undo pressed");
	if(historyx.length>0)
	{
	x = historyx.pop();
	popseats();
	colorseats(x);
	}
	
}

function colorseats(jdata)
{
	Object.entries(jdata).forEach(([key, value]) => {
		    if(key!="header")
		    {
		    	if(value=="n")
		    		$(`#${key}`).css("visibility","hidden");
		    	if(value=="f")
		    		$(`#${key}`).css("color","#ff8f00");
		    	if(value=="p")
		    		$(`#${key}`).css("color","#4caf50");
		    }
		    seatsJson[key] = value;
		});
}

//       Seat click events
function seatclickfunc(id)
{
    console.log(id, "just got clicked");
    obj = document.getElementById(id);
    if(mode!="delete")
    {
        /*if(obj.classList.contains("material-icons-outlined"))
        {
            obj.classList.remove("material-icons-outlined")
            obj.classList.add("material-icons-round");
        }
        else
        {
            obj.classList.add("material-icons-outlined")
            obj.classList.remove("material-icons-round");
        }*/
        if(mode=="premium")
            {obj.style.color="#4caf50";seatsJson[id] = "p";}
        else if(mode=="firstclass")
            {obj.style.color="#ff8f00";seatsJson[id] = "f";}
        else
            {obj.style.color="rgb(56, 56, 56)";seatsJson[id] = "e";}
    }
    else{
        obj.style.visibility="hidden";
        seatsJson[id] = "n";
    }
    historyx.push(JSON.parse(JSON.stringify(seatsJson)));
    console.log("Pushed to history")
}

//        FAB EVENTS
function deletefab(){
    if(mode=="normal")
    {
        mode = "delete";
        modeObj.innerText="DELETE MODE"
        modeObj.style.backgroundColor="#d50000";
        modeObj.style.color="white";
        $("#deletefabicon").text("edit");
    }
    else{
        mode = "normal";
        modeObj.innerText="ECONOMY MODE"
        modeObj.style.backgroundColor="#e2e2e2ec";
        modeObj.style.color="black";
        $("#deletefabicon").text("delete_forever");
    }
}

function premiumfab()
{
    mode = "premium";
    modeObj.innerText="PREMIUM MODE"
    modeObj.style.backgroundColor="#4caf50";
    modeObj.style.color="black";
}

function firstclassfab()
{
    mode = "firstclass";
    modeObj.innerText="FIRST CLASS MODE"
    modeObj.style.backgroundColor="#ff8f00";
    modeObj.style.color="black";
}

function economyfab()
{
    mode = "delete";
    deletefab();
}

//        ESC key events
$(document).on('keydown', function(e) {
    
    if (e.keyCode === 27) { // escape key maps to keycode `27`
    console.log("ESC key down");
       mode = "delete";
       modeObj.innerText="DELETE MODE"
       modeObj.style.backgroundColor="#d50000";
       modeObj.style.color="white";
       console.log("History",historyx);
    }
});
$(document).on('keyup', function(e) {
    
    if (e.keyCode === 27) { // escape key maps to keycode `27`
    console.log("ESC key up");
       mode = "normal";
       modeObj.innerText="ECONOMY MODE"
       modeObj.style.backgroundColor="#e2e2e2ec";
       modeObj.style.color="black";
    }
    console.log(seatsJson);
});

//        Layout on load
$(document).ready(()=>{
	x = $("#seatingstatus_struts").text();
	myrefid = $("#refid_struts").text();
	if(x=="")
	{
    	resetbutton();
    }
    else
    {
    	dbseats = JSON.parse(x);
    	console.log(dbseats);
    	console.log(dbseats.header.dimensions[0],dbseats.header.dimensions[1]);
    	populateSeats(dbseats.header.dimensions[0],dbseats.header.dimensions[1]);
    	Object.entries(dbseats).forEach(([key, value]) => {
		    if(key!="header")
		    {
		    	if(value=="n")
		    		$(`#${key}`).css("visibility","hidden");
		    	if(value=="f")
		    		$(`#${key}`).css("color","#ff8f00");
		    	if(value=="p")
		    		$(`#${key}`).css("color","#4caf50");
		    }
		    seatsJson[key] = value;
		});
		$("#nrows").val(dbseats.header.dimensions[0]);
		$("#ncols").val(dbseats.header.dimensions[1]);
		$("#fc_price").val(dbseats.header.firstclass);
		$("#p_price").val(dbseats.header.premium);
		$("#e_price").val(dbseats.header.economy);
    }
    historyx.push(JSON.parse(JSON.stringify(seatsJson)));
});

//        Materlize FAB
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.fixed-action-btn');
    var instances = M.FloatingActionButton.init(elems,{
        hoverEnabled: false
    });
  });

//        Materlize Modals
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.modal');
    var instances = M.Modal.init(elems);
  });

//        RESET BUTTON
function resetbutton(){
    var instance = M.Modal.getInstance($("#modal1"));
    instance.open();
    //
}

function setbutton(){
    $.ajax({
        type: "POST",
        url: "/MovieTicketsTiles/saveseatlayout",
        data: { 
            refid : myrefid,
            data : JSON.stringify(seatsJson)
        },
        success: function (result,textStatus,xhrreq) {
    		if(result==="success")
    		 {toaster("Seat Layout Saved","white green-text text-accent-4");}
    		 else
    		 {
                toaster("Seat Layout Updation Failed","white red-text text-accent-4");}
		},
        error: function(result) {
            toaster("AJAX Erred","white red-text text-accent-4");
        }
    });
}

function popseats()
{
    populateSeats(Number($("#nrows").val()),Number($("#ncols").val()));
    seatsJson["header"]={
    dimensions : [Number($("#nrows").val()),Number($("#ncols").val())],
    premium : Number($("#p_price").val()),
    firstclass : Number($("#fc_price").val()),
    economy : Number($("#e_price").val())
    };
}

//         Populate seats function
function populateSeats(rows,cols)
{
    if(cols<1 || cols>50)
        {toaster("Invalid Number of Columns","white red-text text-accent-4");;return("")}
    if(rows<1 || rows>26)
        {toaster("Invalid Number of Rows","white red-text text-accent-4");;return("")}
    seathtml="";
    seatingheader = `
    <div class="rowx">
        <div class="seatnumbers">
            <strong>
                <span 
                    class="material-icons offset" 
                    style="transform:rotate(270deg);">
                        rounded_corner
                </span>
            </strong>
        </div>`;
    for(var i=1;i<=cols;i+=1)
        seatingheader+=`<span class="seatnumbers" id="_${i}">${i}</span>`
    seatingheader+=`</div><br>`;
    seathtml+=seatingheader;


    for(var i=1;i<=rows;i+=1)
    {
        seathtml+=`
        <div class="rowx">
            <div class="seatletters"><strong>${alphabet[i-1]}</strong></div>
        `;
        for(var j=1;j<=cols;j+=1)
        {
            seathtml+=`<span class="material-icons-round seats offset" id="${alphabet[i-1]}${j}" onclick="seatclickfunc(this.id)">chair</span>`;
            seatsJson[alphabet[i-1]+String(j)] = "e";
        }
        seathtml+=`
		</div><br>
        `;
    }
    $("#seat-container").html(seathtml);
}