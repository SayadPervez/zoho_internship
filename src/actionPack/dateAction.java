package actionPack;

import servicePack.dateService;

public class dateAction {
	
	private String now;
	private String us;
	
	public String execute() {
		dateService d = new dateService();
		setNow(d.getDate(getUs()));
		return("success");
	}

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	public String getUs() {
		return us;
	}

	public void setUs(String us) {
		this.us = us;
	}
}
