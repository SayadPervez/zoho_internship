const alphabet = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
var dbseats;
var showid;

mySeats = [];
totalCost = 0;
myWallet = 0;
paymentPossible = false;

var bookedseats;
var bookedseatsli;

var seatsJson = {};

var totalNumberOfSeats = 0;
var totalBooked = 0;
var Pnos = 0,Pty =0,Poc=0;
var Fnos = 0,Fty =0,Foc=0;
var Enos = 0,Ety =0,Eoc=0;



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

function getSeatClass(id)
{
	return(dbseats[id]);
}

//        Layout on load
$(document).ready(()=>{
		x = $("#seating_struts").text();
		showid = $("#showid_struts").text();
		bookedseats = $("#bookedseats_struts").text();
		bookedseats = bookedseats=="-" ? "" : bookedseats;
		if(bookedseats=="")
			totalBooked = 0
		else
		{
			bookedseatsli = bookedseats.split(",");
			totalBooked = bookedseatsli.length;
		}

		myWallet = $("#mywallet_struts").text();
    	dbseats = JSON.parse(x);
    	console.log(dbseats);
    	console.log(dbseats.header.dimensions[0],dbseats.header.dimensions[1]);
    	populateSeats(dbseats.header.dimensions[0],dbseats.header.dimensions[1]);
    	Object.entries(dbseats).forEach(([key, value]) => {
		    if(key!="header")
		    {
		    	totalNumberOfSeats+=1
		    	if(value=="n")
		    		{$(`#${key}`).css("visibility","hidden");totalNumberOfSeats-=1;}
		    	else if(value=="f")
		    		{$(`#${key}`).css("color","#ff8f00");Fnos+=1;}
		    	else if(value=="p")
		    		{$(`#${key}`).css("color","#4caf50");Pnos+=1;}
		    	else{
		    	Enos+=1;}
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
				bookedseatsli.forEach((seat)=>{
				xtempx = getSeatClass(seat);
				if(xtempx=="f")
					Foc+=1;
				if(xtempx=="p")
					Poc+=1;
				if(xtempx=="e")
					Eoc+=1;
			});
			Fty = Fnos - Foc;Ety = Enos - Eoc;Pty = Pnos - Poc;
		
		$("#qwerty").html(totalNumberOfSeats);
		
						var xValues = ["Booked", "Vacant"];
						var yValues = [totalBooked, totalNumberOfSeats-totalBooked];
						var barColors = [
						  "#2b5797",
						  "#e8c3b9"
						];
		
					new Chart("myChart", {
					  type: "doughnut",
					  data: {
					    labels: xValues,
					    datasets: [{
					      backgroundColor: barColors,
					      data: yValues
					    }]
					  },
					  options: {
					    title: {
					      display: true,
					      text: "Seat Occupancy Stats"
					    }
					  }
					});
		
		
		
		
		
		
		
		
		
						var xValues0 = ["First Class", "Premium", "Economy"];
						var yValues0 = [Fnos, Pnos, Enos];
						var barColors0 = [
						  "#ff8f00",
						  "#4caf50",
						  "#313131"
						];
		
					new Chart("myChart2", {
					  type: "doughnut",
					  data: {
					    labels: xValues0,
					    datasets: [{
					      backgroundColor: barColors0,
					      data: yValues0
					    }]
					  },
					  options: {
					    title: {
					      display: true,
					      text: "Seat Layout Stats"
					    }
					  }
					});
					
					
					
					
					
					
					
					
					var xValues0f = ["First Class Booked","First Class Vacant", "Premium Booked","Premium Vacant", "Economy Booked", "Economy Vacant"];
						var yValues0f = [Foc,Fty,Poc, Pty,Eoc, Ety];
						var barColors0f = [
						  "#ff8f00",
						  "#FFC233",
						  "#4caf50",
						  "#CBFFCF",
						  "#313131",
						  "#616161"
						];
		
					new Chart("myChart3", {
					  type: "doughnut",
					  data: {
					    labels: xValues0f,
					    datasets: [{
					      backgroundColor: barColors0f,
					      data: yValues0f
					    }]
					  },
					  options: {
					    title: {
					      display: true,
					      text: "Class wise Vacancy Stats"
					    }
					  }
					});
					
					
					var xValuesx = ["First Class", "Premium", "Economy"];
					var yValuesx = [Number(dbseats.header.firstclass), Number(dbseats.header.premium), Number(dbseats.header.economy)];
					var barColorsx = ["#ff8f00", "#4caf50","#313131"];
					
					new Chart("myChartx", {
					  type: "horizontalBar",
					  data: {
					    labels: xValuesx,
					    datasets: [{
					      backgroundColor: barColorsx,
					      data: yValuesx
					    }]
					  },
					  options: {
					    title: {
					      display: true,
					      text: "Class wise Price"
					    }
					  }
					});
					
					
});

//        Materlize Modals
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.modal');
    var instances = M.Modal.init(elems);
  });

function setbutton(){
		  var instance = M.Modal.getInstance($("#modal1"));
		  instance.open();
}

$(document).on('keydown', function(event) {
       if (event.key == "Escape") {
           console.log("dbseats : ",dbseats);
           console.log("seatsjson : ",seatsJson);
           console.log("bookedseats : ",bookedseats);
           console.log("Total Number of Seats : ",totalNumberOfSeats);
           console.log("Total Number of Seats Booked : ",totalBooked);
           console.log("Fnos Pnos Enos : ",Fnos,Pnos,Enos);
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
            seathtml+=`<span class="material-icons-outlined seats offset" id="${alphabet[i-1]}${j}">chair</span>`;
            seatsJson[alphabet[i-1]+String(j)] = "e";
        }
        seathtml+=`
		</div><br>
        `;
    }
    $("#seat-container").html(seathtml);
    $("footer").html("Screen Here");
}