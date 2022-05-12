package actionPack;

public class heroPageAction{
	
	private String type;
	private String iconpath;
	
	public String execute() {
		System.out.println("actionPack.heroPageAction.execute() returned error (Remove this line from println)");
		return("error");
	}
	public String owner() {
		System.out.println("actionPack.heroPageAction.owner() returned success");
		setType("Owner");setIconpath("ownerpngicon");
		System.out.println("ICONPATH : "+getIconpath());
		return("success");
	}
	
	public String customer() {
		System.out.println("actionPack.heroPageAction.owner() returned success");
		setType("Customer");setIconpath("customer0pngicon");
		System.out.println("ICONPATH : "+getIconpath());
		return("success");
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIconpath() {
		return iconpath;
	}
	public void setIconpath(String iconpath) {
		this.iconpath = iconpath;
	}
}
