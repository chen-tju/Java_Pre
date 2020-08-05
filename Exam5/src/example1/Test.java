package example1;

public class Test {
	public static void main(String[] args){
		int sum = 0;
		int n = 1;
	    int i = 1;
		while (n<101){
			sum = sum+n*n*i;
			i=-i;
			n=n+1;
		}
	System.out.println("sum="+ sum);
	}
	}