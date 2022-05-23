<%@ taglib prefix="s" uri="/struts-tags" %>

<span class="dummy" id="jsonString_struts"><s:property value="listOfJsonObjects"></s:property></span>
<span class="dummy" id="seating_struts"><s:property value="seatingstatus"></s:property></span>
<span class="dummy" id="refid_struts"><s:property value="refid"></s:property></span>

<div id="sub-cont">

	<h4 style="font-weight:bold;">Manage Shows :<button data-target="modal1" id="modalbutton" style="float:right;" class="btn-large grey darken-4"><strong>Add Show  <span class="material-icons offset">add_circle</span></strong></button></h4><br>
	<br><br>
	<table class="highlight responsive-table">
		<thead>
			<tr>
				<th>Theater Name</th>
				<th>Screen</th>
				<th>Time Slot</th>
				<th>Movie Name</th>
				<th>Duration</th>
				<th>Schedule</th>
				<th>Options</th>
			</tr>
		</thead>
		<tbody id="tbody">
			
		</tbody>
	</table>
	
						  <div id="modal1" class="modal modal-fixed-footer">
						    <div class="modal-content">
						      
						      			<div style="width:64%;margin:1rem auto;">
						      			
						      				<h3 class="center-align" style="font-weight:bold;">Add a Show</h3>
						      				
							      			<div class="input-field col s12">
											    <select id="theaters_select" class="browser-default addinp">
											      
											    </select>
											  </div>	
											  
											 <div class="input-field col s12">
											    <select id="theaters_slot" class="browser-default addinp">
											      <option value="" disabled selected>NO THEATERS SELECTED</option>
											    </select>
											  </div>	
											  
											  <div class="input-field col s12">
										         <input id="screennumber" type="number" class="addinp"/> / <strong id="totnoofscreens"></strong>
										         <label for="screennumber">Screen Number</label>
										       </div>				
										       
										       <div id="inputdiv2">
										       	
													  <div class="input-field col s12">
												         <input id="moviename" type="text" class="addinp"/>
												         <label for="moviename">Movie Name</label>
												       </div>
												       
													  <div class="input-field col s12">
												         <input id="movieduration" type="text" class="addinp"/>
												         <label for="movieduration">Movie Duration</label>
												       </div>
												       
												       <div>
														       <input type="date" placeholder="Enter Opening Date" id="startingdate" class="addinp">
														       <input type="date" placeholder="Enter Closing Date" id="closingdate" class="addinp">
												       </div>
										       	
										       </div>							
									                 
										</div>
						      
						      
						    </div>
						    <div class="modal-footer">
						    	<a href="#!" class="modal-close modalbutton waves-effect waves-dark btn-flat red-text" id="closemodalbutton"><strong>Cancel</strong></a>
						      	<a class="modal-close modalbutton waves-effect waves-light btn grey darken-4" id="submitbutton"><strong>Add Show</strong></a>
						    </div>
						  </div>
						
	
</div>