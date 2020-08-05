package exam4;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class Example4 {
	public static String getDate(String format){
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cd.getTime());
	}
public static String getDate(Date date,String format){
	SimpleDateFormat sdf = new SimpleDateFormat(format);
	return sdf.format(date);
}
public static int getIntervalDays(Date startday,Date endday){
	if(startday.after(endday)){
		Date cal = startday;
		startday = endday;
		endday = cal;
	}
	long sl = startday.getTime();
	long el = endday.getTime();
	long ei = el - sl;
	return (int)(ei/(1000*60*60*24));
}
public static Date addDate(Date date,int day){
	java.util.Calendar c = java.util.Calendar.getInstance();
	c.setTime(date);
	c.add(java.util.Calendar.DATE, day);
	return c.getTime();
}
}
