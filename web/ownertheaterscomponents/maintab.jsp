<%@ taglib prefix="s" uri="/struts-tags" %>

<span class="dummy" id="jsonString_struts"><s:property value="listOfJsonObjects"></s:property></span>
<span class="dummy" id="seating_struts"><s:property value="seatingstatus"></s:property></span>
<span class="dummy" id="refid_struts"><s:property value="refid"></s:property></span>

<div id="sub-cont">

	<h4 style="font-weight:bold;">Manage Theaters :<button data-target="modal1" id="modalbutton" style="float:right;" class="btn-large grey darken-4"><strong>Add Theater  <span class="material-icons offset">add_circle</span></strong></button></h4><br>
	<br><br>
	<table class="highlight responsive-table">
		<thead>
			<tr>
				<th>Theater Name</th>
				<th>Location</th>
				<th>No. of Screens</th>
				<th>Time Slots</th>
				<th>Options</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>
	
						  <div id="modal1" class="modal modal-fixed-footer">
						    <div class="modal-content">
						      
						      				<div style="width:fit-content;margin:1rem auto;">
												<h4 style="font-weight:bold;">Add a New Theater :</h4><br>
									          
									          <h6>Theater Name :</h6><input id="modal_theatername" type="text"/><br>
									
									          
									          <h6>Theater Location :</h6><input id="modal_theaterlocation" type="text"/><br>
									
									          
									          <h6>Number of Screens :</h6><input id="modal_noofscreens" type="number" min="1" max="100" step="1"/><br>
									
									          
									          <h6>Time Slots :</h6><input id="modal_time" type="time" style="width:20rem !important;"/> <a class="btn-floating btn waves-effect waves-light grey darken-2 z-depth-3" id="fab" style="margin-left:2rem;" ><i class="material-icons">add</i></a><br>
									          <br>
									          <div id="showcasediv">
									          </div>
									                 
										</div>
						      
						      
						    </div>
						    <div class="modal-footer">
						    	<a href="#!" class="modal-close modalbutton waves-effect waves-dark btn-flat red-text" id="closemodalbutton"><strong>Cancel</strong></a>
						      	<a class="modal-close modalbutton waves-effect waves-light btn grey darken-4" id="submitbutton"><strong>Add Theater</strong></a>
						    </div>
						  </div>
						  
				<div id="modal2" class="modal modal-fixed-footer">
						    <div class="modal-content">
						      
						      				<div style="width:fit-content;margin:1rem auto;">
												<h4 style="font-weight:bold;">Edit Theater :</h4><br>
									          
									          <h6>Theater Name :</h6><input id="modal2_theatername" type="text"/><br>
									
									          
									          <h6>Theater Location :</h6><input id="modal2_theaterlocation" type="text"/><br>
									
									          
									          <h6>Number of Screens :</h6><input id="modal2_noofscreens" type="number" min="1" max="100" step="1"/><br>
									
									          
									          <h6>Time Slots :</h6><input id="modal2_time" type="time" style="width:20rem !important;"/> <a class="btn-floating btn waves-effect waves-light red darken-2 z-depth-3" id="fab_reset" style="margin-left:2rem;" ><i class="material-icons">restart_alt</i></a>
									          <a class="btn-floating btn waves-effect waves-light grey darken-2 z-depth-3" id="fab2" style="margin-left:2rem;display:none;" ><i class="material-icons">add</i></a><br>
									          <br>
									          <div id="showcasediv2" class="left">
									          </div>
									          <div id="actid" class="dummy"></div>
									                 
										</div>
						      
						      
						    </div>
						    <div class="modal-footer">
						    	<a href="#!" class="modal-close modalbutton waves-effect waves-dark btn-flat red-text" id="closemodalbutton2"><strong>Cancel</strong></a>
						      	<a class="modal-close modalbutton waves-effect waves-light btn grey darken-4" id="savechanges"><strong>Save Changes</strong></a>
						    </div>
						  </div>
	
</div>