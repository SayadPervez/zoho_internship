const alphabet = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var dbseats;
var showid;

mySeats = [];
totalCost = 0;
myWallet = 0;
paymentPossible = false;

var seatsJson = {};

//       Seat click events
function seatclickfunc(id)
{
    console.log(id, "just got clicked");
    obj = document.getElementById(id);
    if(obj.classList.contains("material-icons-outlined"))
    {
        obj.classList.remove("material-icons-outlined")
        obj.classList.add("material-icons-round");
        mySeats.push(id);
        totalCost+=getSeatPrice(id);
    }
    else
    {
        obj.classList.add("material-icons-outlined")
        obj.classList.remove("material-icons-round");
        mySeats = mySeats.filter((value)=>{return(value==id)?false:true});
        totalCost-=getSeatPrice(id);
    }    
    $("#infodiv").html(`
    <h5><strong>My seats :</strong> ${mySeats.join(",")}</h5>
    <h5><strong>Total Cost :</strong> ${totalCost} rupees</h5>
    <h5><strong>My Wallet :</strong> ${myWallet} rupees</h5>
    `);
    if(Number(myWallet)<=Number(totalCost))
    {
    	paymentPossible = true;
    }
    else
    {
    	paymentPossible = false;
    }
}

function getSeatPrice(id)
{
	c = dbseats[id];
	if(c=="f")
		price = dbseats.header.firstclass;
	else if(c=="p")
		price = dbseats.header.premium;
	else
		price = dbseats.header.economy;
	return(price);
}

//        Layout on load
$(document).ready(()=>{
		x = $("#seating_struts").text();
		showid = $("#showid_struts").text();
		bookedseats = $("#bookedseats_struts").text();
		bookedseats = bookedseats=="-" ? "" : bookedseats
		myWallet = $("#mywallet_struts").text();
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
		if(bookedseats!="")
		{
			console.log("Some seats seem to be booked");
			bookedseats.split(",").forEach((val)=>{
				obj = document.getElementById(val);
				obj.classList.remove("material-icons-outlined")
        		obj.classList.add("material-icons-round");
				$(`#${val}`).each(function (){
					    this.style.pointerEvents = 'none'; 
					}); 		
				$(`#${val}`).css("opacity","0.3");
			});
		}
		else
		{
			console.log("All seats vacant");
		}
});

//        Materlize Modals
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.modal');
    var instances = M.Modal.init(elems);
  });

function setbutton(){
	if(mySeats.length!=0)
	{
		  var instance = M.Modal.getInstance($("#modal1"));
		  instance.open();
    }
    else{
		toaster("No seats selected to pay for","white red-text text-accent-4");
	}
}

function paybutton()
{
	console.log("paybutton got clicked")
	if(!paymentPossible)
	{
		waiter("Processing...","white green-text text-accent-4");
		waiter("Please Wait","white green-text text-accent-4");
		$.ajax({
			    type: "POST",
			    url: "/MovieTicketsTiles/finalizeTicket",
			    data: { 
			    	sid : showid,
			    	myseats : mySeats.join(","),
			    	totalcost : totalCost
			    },
			    success: function (result,textStatus,xhrreq) {
					if(result==="success")
					 {
					 toaster("Success","white green-text text-accent-4");window.location.href=`/MovieTicketsTiles/customerschedulepage`;}
					 else
					 {
						toaster(result,"white red-text text-accent-4");
					 }
				},
			    error: function(result) {
			        toaster("AJAX Erred","white red-text text-accent-4");
			    }
			});
	}
	else{
		toaster("Insufficient Funds","white red-text text-accent-4");
		window.location.href = '/MovieTicketsTiles/customerwalletpage';
	}
}

$(document).on('keydown', function(event) {
       if (event.key == "Escape") {
           console.log(mySeats);
           console.log("Total Cost :",totalCost);
       }
   });

//         Populate seats function
function populateSeats(rows,cols)
{
    if(cols<1 || cols>50)
        {
		toaster("Invalid Number of Columns","white red-text text-accent-4");
		return("")}
    if(rows<1 || rows>26)
        {
		toaster("Invalid Number of Rows","white red-text text-accent-4");
		return("")}
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
            seathtml+=`<span class="material-icons-outlined seats offset" id="${alphabet[i-1]}${j}" onclick="seatclickfunc(this.id)">chair</span>`;
            seatsJson[alphabet[i-1]+String(j)] = "e";
        }
        seathtml+=`
		</div><br>
        `;
    }
    $("#seat-container").html(seathtml);
    $("footer").html("Screen Here");
}