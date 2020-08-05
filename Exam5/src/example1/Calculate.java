package example1;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Calculate {
	public static void main(String[] args){
		int x,y;
		int result = 0;
		String op;
		try{
			Scanner scanner = new Scanner(System.in);
			x = scanner.nextInt();
			y = scanner.nextInt();
			op= scanner.next();
			if(op.equals("+")){
				result = x + y;
			}else if(op.equals("-")){
				result = x - y;
			}else if(op.equals("*")){
				result = x * y;
			}else if(op.equals("/")){
				result = x / y;
			}else{
				throw new Exception();
			}
			System.out.println(x + op + y + "=" + result);
		}catch(InputMismatchException ex){
			System.out.println("Êı¾İ´íÎó");
		}catch(ArithmeticException ex){
			System.out.println("³ıÁã´íÎó");
		}catch(Exception ex){
			System.out.println("ÔËËã·û´íÎó");
		}
	}

}
