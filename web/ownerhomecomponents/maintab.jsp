<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="sub-cont">
	<div style="width:fit-content;margin:1rem auto;padding-bottom:3.7rem;">
			<h4 style="font-weight:bold;">User Details :</h4><br>
          
          <h6>Username :</h6><s:textfield id="username" value ="%{username}" type="text"></s:textfield><br>

          
          <h6>Email ID :</h6><s:textfield id="emailid" value ="%{emailid}" type="email"></s:textfield><br>

          
          <h6>Age :</h6><s:textfield id="age" value ="%{age}" type="number" min="3" max="200" step="1"></s:textfield><br>

          
          <h6>User Type :</h6><s:textfield id="type" value ="%{type}" type="text"></s:textfield><br>

          
          <h6>Wallet :</h6><s:textfield id="wallet" value ="%{wallet}" type="number"></s:textfield><br><br><br>
          
          <button id="editbutton" class="btn-large right grey darken-4"><strong>Edit <span class="material-icons">edit</span></strong></button>
          
          <br><a href="/MovieTicketsTiles/changepassword">Change Password ?</a>         
	</div>
</div>