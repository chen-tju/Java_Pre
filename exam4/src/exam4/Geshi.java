package exam4;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Geshi {
	public static void main(String[] args){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy��MM��dd��hʱmm��");
		Date date = new Date();
		System.out.println(fmt.format(date));
	}
	
}