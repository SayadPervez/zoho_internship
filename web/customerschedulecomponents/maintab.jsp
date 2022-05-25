	<%@ taglib prefix="s" uri="/struts-tags" %>

<span class="dummy" id="jsonString_struts"><s:property value="listOfJsonObjects"></s:property></span>
<span class="dummy" id="seating_struts"><s:property value="seatingstatus"></s:property></span>
<span class="dummy" id="refid_struts"><s:property value="refid"></s:property></span>

<div id="sub-cont">

	<h4 style="font-weight:bold;">My Schedule :
		
		<button onclick="searchnow()" style="float:right;" class="btn-large grey darken-4">
			<strong>Search  <span class="material-icons offset">search</span></strong>
		</button>
		<div class="input-field col s2" style="float:right;">
          <input placeholder="Search ?" id="searchinput" type="text">
          <label for="searchinput">Search box</label>
        </div>
		  <div class="input-field col s2" style="float:right;">
		    <select id="searchselect">
		      <option value="" selected></option>
		      <option value="moviename">Movie</option>
		      <option value="theatername">Theater Name</option>
		      <option value="date">Date</option>
		    </select>
		    <label>Search By</label>
		  </div>
	</h4><br>
	<br><br>
	<table class="highlight responsive-table">
		<thead>
			<tr>
				<th>Movie Name</th>
				<th>Theater Name</th>
				<th>Screen</th>
				<th>Time slot</th>
				<th>Duration</th>
				<th>Schedule</th>
				<th style="visibility:hidden;">Options</th>
			</tr>
		</thead>
		<tbody id="tbody">
			
		</tbody>
	</table>
	
	  <div id="modal1" class="modal modal-fixed-footer">
	    <div class="modal-content">
	      
	      			<div style="width:64%;margin:1rem auto;">
	      			
	      				<h3 class="center-align" style="font-weight:bold;">Cancel Show</h3>
	      				
		  				<div id="ticketinfodiv">
		  					
		  				</div>
				                 
					</div>
	      
	      
	    </div>
	    <div class="modal-footer">
	    	<a href="#!" class="modal-close modalbutton waves-effect waves-dark btn-flat red-text" id="closemodalbutton"><strong>Back</strong></a>
	      	<a class="modal-close modalbutton waves-effect waves-light btn grey darken-4" id="submitbutton"><strong>Cancel Shows</strong></a>
	    </div>
	  </div>
						
	
</div>