package exam4;
import java.util.Date;
public class TestDateUtil {
	public static void main(String args[]){
		System.out.println(Example4.getDate("yyyy.MM.dd HH:mm:ss"));
		Date date = new Date();
		Date date1 = Example4.addDate(date,2);
		System.out.println(Example4.getDate(date1,"yyyy.MM.dd HH:mm:ss"));
		int n = Example4.getIntervalDays(date1,date);
		System.out.println(n);
	}

}
