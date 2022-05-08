package servicePack;

import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  

public class dateService {
	public String getDate(String us)
	{
        Date date = Calendar.getInstance().getTime();
        String x;
		if(!us.equals("true"))
        	x = "dd-M-yyyy";
        else
        	x = "yyyy-M-dd";
        
        DateFormat dateFormat = new SimpleDateFormat(x);  
        String strDate = dateFormat.format(date);  
        return(strDate);  
	}
}
