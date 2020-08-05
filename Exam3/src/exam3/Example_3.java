package exam3;
import java.util.Scanner;
public class Example_3 {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		String s=scanner.nextLine();
		int k=s.indexOf("=");
		String str=s.substring(k+1);
		System.out.println(str);
		if(str.length()>100){
			System.out.println("长度不能超过100");
		}
		else{
			str=str.replaceAll("\\[\\?\\]","Java");
			if(str.length()>80){
				str=str.substring(0,80);
			}
			System.out.println(str);
		}
	}

}
