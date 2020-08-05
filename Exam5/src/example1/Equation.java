package example1;
import java.text.DecimalFormat;
public class Equation {
	static void getRoot(double a,double b,double c){
		if(a!=0){
			double d = b*b -4*a*c;
			DecimalFormat df= new DecimalFormat("0.0000");
			if(d>0){
				double x1=(-b + Math.sqrt(d))/(a*2);
				double x2=(-b - Math.sqrt(d))/(a*2);
				System.out.println("x2="+df.format(x1));
				System.out.println("x2="+df.format(x1));
			}else if (d==0){
				double x=-b/(2*a);
				System.out.println("x1=x2="+df.format(x));
			}else{
				double real = -b/(2*a);
				double image = Math.sqrt(-d)/(2*a);
				System.out.println("x1="+df.format(real)+"+"+df.format(image)+"i");
				System.out.println("x2="+df.format(real)+"-"+df.format(image)+"i");
			}
		}else{
			System.out.println("不是二次方程");
		}
	}
	public static void main(String[] args){
		getRoot(4,4,1);
		getRoot(1,5,6);
		getRoot(10,1,2);
		getRoot(0,2,3);
	}

}
