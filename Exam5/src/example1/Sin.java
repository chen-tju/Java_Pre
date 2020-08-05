package example1;
import java.text.DecimalFormat;
public class Sin {
	public static double f1(double x){
		double s = 0;
		double t = x;
		int n = 1;
		while (Math.abs(t)>=0.00001){
			s = s+t;
			t = (-1)*t*x*x/((n+1)*(n+2));
			n=n+2;
		}
		return s;
	}
public static double f2(double x){
	double s = 0;
	double t = 1;
	int n = 1;
	double p = x;
	while(Math.abs(p/t)>=0.00001){
		s = s+p/t;
		p = (-1)*p*x*x;
		t = 1;
		n = n+2;
		for(int i=1;i<=n;i++){
			t = t*i;
		}
	}
	return s;
}
public static void main(String[] args){
	DecimalFormat df = new DecimalFormat("0.0000");
	System.out.println(df.format(f1(2)));
	System.out.println(df.format(f2(2)));
}
}
