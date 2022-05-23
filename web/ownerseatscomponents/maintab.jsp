<%@ taglib prefix="s" uri="/struts-tags" %>
<span style="display:none;" id="refid_struts"><s:property value="refid"></s:property></span>
<span style="display:none;" id="seatingstatus_struts"><s:property value="seatingstatus"></s:property></span>

<div id="seat-container">
										
									</div>

									<div id="modal1" class="modal modal-fixed-footer">
										<div class="modal-content">
										<h4 class="center-align">Set Theater Layout</h4>
											<div id="inputdiv">
													<div class="input-field">
														<input id="nrows" type="number">
														<label for="nrows" class="input_label">Number of Rows</label>
													  </div>
													  <div class="input-field">
														<input id="ncols" type="number">
														<label for="ncols" class="input_label">Number of Columns</label>
													  </div>
													  <div class="input-field">
														<input id="fc_price" type="number">
														<label for="fc_price" class="input_label">First Class Seats Price</label>
													  </div>
													  <div class="input-field">
														<input id="p_price" type="number">
														<label for="p_price" class="input_label">Premium Seats Price</label>
													  </div>
													  <div class="input-field">
														<input id="e_price" type="number">
														<label for="e_price" class="input_label">Economy Seats Price</label>
													  </div>
											</div>
										</div>
										<div class="modal-footer">
											<a href="#!" class="modal-close waves-effect red white-text waves-dark btn-flat" style="font-weight:bold;">Cancel</a>
											<a href="#!" onclick="popseats()" class="modal-close waves-effect green darken-2 white-text waves-green btn-flat" style="font-weight:bold;">Create Seat Layout</a>
										</div>
									</div>

									<div class="fixed-action-btn" id="modefabdiv">
										<a class="btn-floating btn-large red darken-2">
										<i class="large material-icons">menu</i>
										</a>
										<ul>
										<li><a class="btn-floating red" onclick="deletefab()"><i class="material-icons" id="deletefabicon">delete_forever</i></a></li>
										<li><a class="btn-floating amber darken-3" onclick="firstclassfab()"><i class="material-icons">workspace_premium</i></a></li>
										<li><a class="btn-floating green" onclick="premiumfab()"><i class="material-icons">hotel_class</i></a></li>
										<li><a class="btn-floating blue" onclick="economyfab()"><i class="material-icons">savings</i></a></li>
										</ul>
									</div>

									<button class="btn-large orange darken-2 black-text" id="resetbutton" onclick="resetbutton()">
										Reset Layout
											<span class="material-icons offset offsetoverride">
												restart_alt
											</span>
									</button>
									
									<button class="btn-large green darken-2 black-text" id="setbutton" onclick="setbutton()">
										Save Layout
											<span class="material-icons offset offsetoverride">
												save
											</span>
									</button>

									<span id="modebadge" class="badge">ECONOMY MODE</span>