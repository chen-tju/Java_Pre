package exam4;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Geshi {
	public static void main(String[] args){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy年MM月dd日h时mm分");
		Date date = new Date();
		System.out.println(fmt.format(date));
	}
	
}