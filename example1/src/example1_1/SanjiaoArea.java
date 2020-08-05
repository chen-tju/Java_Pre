package example1_1;
import java.util.Scanner;
public class SanjiaoArea {
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		float a = scanner.nextFloat();
		float b = scanner.nextFloat();
		float c = scanner.nextFloat();
		double p=0.5*(a+b+c);
		System.out.println("p="+p);
		System.out.println("三角形的面积为"+Math.sqrt(p*(p-a)*(p-b)*(p-c)));
	}

}
