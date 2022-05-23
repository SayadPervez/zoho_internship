<%@ taglib prefix="s" uri="/struts-tags" %>
<span style="display:none;" id="seating_struts"><s:property value="seatingjson"></s:property></span>
<span style="display:none;" id="bookedseats_struts"><s:property value="bookedseats"></s:property></span>
<span style="display:none;" id="mywallet_struts"><s:property value="mywallet"></s:property></span>
<span style="display:none;" id="showid_struts"><s:property value="sid"></s:property></span>

<div id="seat-container">
						
</div>


	
 <div id="modal1" class="modal bottom-sheet">
    <div class="modal-content">
      <h4>Make Payment</h4>
      <div id="infodiv" style="padding:3rem;padding-bottom:1rem;">
		
		</div>
		<button onclick="paybutton()" class="modal-close waves-effect green darken-2 btn-flat white-text" style="margin:3rem;margin-top:0;"><strong>Pay</strong></button>
    </div>
  </div>
									
<button class="btn-large green darken-2 black-text" id="setbutton" onclick="setbutton()">
	Make the Payment
		<span class="material-icons offset offsetoverride">
	monetization_on
	</span>
</button>