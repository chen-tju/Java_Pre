package example1;

public class FindPrime {
	public static void main(String[] args){
		int t = 0;
		boolean f = true;
		for(int i= 2;i<=100;i++){
			f=true;
			for(int j = 2;j<i-1;j++){
				if(i%j==0){
					f=false;
					break;
				}
			}
			if(f){
				t++;
				if(t%5 == 0){
					System.out.println(i+" ");
				}else{
					System.out.print(i+" ");
				}
			}
		}
	}

}
