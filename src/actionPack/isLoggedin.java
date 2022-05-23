package actionPack;

import javax.servlet.http.HttpSession;  
import org.apache.struts2.ServletActionContext;  

public class isLoggedin {
	
	private String problem;
	
	public String execute(){  
        HttpSession session=ServletActionContext.getRequest().getSession(false);  
        System.out.println("Isloggedin called");
        if(session==null){   
            setProblem("No session object was ever created"); 
            System.out.println("Isloggedin returning error null session");
            return "error";
        }  
        else if(session.getAttribute("logged-in")=="true"){  
        	System.out.println("Isloggedin returning success");
            return "success";  
        }  else
        {
        	System.out.println("Isloggedin returning error not logged in");
        	setProblem("Not logged in !!!"); 
        	return("error");
        }
    }

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}
}
