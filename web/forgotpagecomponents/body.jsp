<%@ taglib prefix="s" uri="/struts-tags" %> 

<div id="mainSplitContainer">

		<div class="sub" id="subicon">
		</div>

		<div></div>

		<div class="sub">
			<h3 class="center-align">Forgot Password</h3><br>
			<div class="input-field">
				<input id="emailid_forgot" type="text">
				<label for="emailid" class="input_label">Email id</label>
			  </div>
			  <div class="input-field" id="otpdiv">
				<input id="otp_forgot" type="number">
				<label for="otp" class="input_label">OTP</label>
			  </div>

			  <button class="btn-large z-depth-2" id="forgot_button">Send OTP</button>
		</div>

	</div>