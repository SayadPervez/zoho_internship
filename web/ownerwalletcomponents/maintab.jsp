<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="sub-cont">
	<div style="width:fit-content;margin:1rem auto;padding-bottom:3.7rem;">
			<h4 style="font-weight:bold;">User Wallet :</h4><br>

          <h6>Wallet :</h6><s:textfield id="wallet" value ="%{wallet}" type="number"></s:textfield><br><br><br>
          
          <button id="editbutton" class="btn-large right grey darken-4"><strong>Edit <span class="material-icons">edit</span></strong></button>
        
	</div>
</div>