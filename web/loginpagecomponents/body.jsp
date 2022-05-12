<%@ taglib prefix="s" uri="/struts-tags" %> 

<div id="mainSplitContainer">

		<div class="sub" id="subicon">
		</div>
		
		<span class="dummy" id="iconpathdummy"><s:property value="iconpath" /></span>

		<div></div>

		<div class="sub">
			<h3 class="center-align"><s:property value="type" /> Login</h3>
			<div class="input-field">
				<input id="uname_login" type="text">
				<label for="uname" class="input_label">Username</label>
			  </div>
			  <div class="input-field">
				<input id="pwd_login" type="password">
				<label for="pwd" class="input_label">Password</label>
			  </div>

			  <button class="btn-large z-depth-2" id="login_button">Login</button>
			  <br><a href="/MovieTicketsTiles/forgotpasswordpage">Forgot Password ?</a><br>
			  <a href="/MovieTicketsTiles/signuppage">Do not have an account ? Sign Up here</a>
		</div>

	</div>