package actionPack;

public class formAction {
	
	private String data;
	
	public String execute()
	{
		System.out.println("getData : "+getData());
		if(getData()==null)
			return("success");
		else
			return("nextPage");
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
