package example1_1;
import java.util.Scanner;
public class TixingArea {
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		float a = scanner.nextFloat();
		float b = scanner.nextFloat();
		float h = scanner.nextFloat();
		double area = (a+b)*h*0.5;
		System.out.println("area="+area);
	}

}
