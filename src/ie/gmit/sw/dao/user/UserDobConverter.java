package ie.gmit.sw.dao.user;

import java.sql.Date;
import java.util.Calendar;

public class UserDobConverter {
	
	private Calendar calDob = Calendar.getInstance();
	
	public void setCalendarDob(Integer y, Integer m, Integer d){
		this.calDob.set(Calendar.YEAR, y);
		this.calDob.set(Calendar.MONTH, m);
		this.calDob.set(Calendar.DAY_OF_MONTH, d);
	}
	public Calendar getCalendarDob(){
		return this.calDob;
	}
	
	public Date getDateDob(Calendar calDob){
		
		return new Date(calDob.getTime().getTime());
	}
}
