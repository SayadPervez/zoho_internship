<%@ taglib prefix="s" uri="/struts-tags" %>
<span style="display:none;" id="seating_struts"><s:property value="seatingjson"></s:property></span>
<span style="display:none;" id="bookedseats_struts"><s:property value="bookedseats"></s:property></span>
<span style="display:none;" id="mywallet_struts"><s:property value="mywallet"></s:property></span>
<span style="display:none;" id="showid_struts"><s:property value="sid"></s:property></span>

<script
src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js">
</script>

<div id="seat-container">
						
</div>


<div id="modal1" class="modal modal-fixed-footer">
    <div class="modal-content">
      <h4>Stats</h4>
      
      <h6>Total Number of Seats : <span id="qwerty"></span></h6>
      
		<div id="statsdiv">
			<canvas id="myChart2" style="display:inline-block;margin:0;"></canvas><br><br><br>
			<canvas id="myChart" style="display:inline-block;margin:0;"></canvas><br><br><br>
			<canvas id="myChart3" style="display:inline-block;margin:0;"></canvas><br><br><br>
			<canvas id="myChartx" style="display:inline-block;margin:0;"></canvas><br><br><br>
		</div>

    </div>
    <div class="modal-footer">
      <a href="#!" class="modal-close btn-flat red-text text-accent-4"><strong>Close</strong></a>
    </div>
  </div>

									
<button class="btn-large indigo accent-4 grey-text text-darken-4" id="setbutton" onclick="setbutton()">
	View Stats
		<span class="material-icons offset offsetoverride">
	insights
	</span>
</button>