package servicePack;

import dbassistPack.userfunctions;

public class usertypeService {
	public String run(String username)
	{
		/*
		 * Returns logged in user's type
		 * */
		userfunctions uf = new userfunctions();
		return(uf.getUserType(username));
	}
}
