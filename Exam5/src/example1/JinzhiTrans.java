package example1;

public class JinzhiTrans {
	public static String f1(int n,int b){
		StringBuffer s = new StringBuffer();
		int r = 0;
		do{
			r = n%b;
			if(r>=10){
				r=r+55;
			}else{
				r = r+48;
			}
			s.append((char)r);
			n = n/b;
		}while(n!=0);
		s.reverse();
		return s.toString();
	}
	public static int f2(String s,int b){
		int n = 0;
		for(int i = 0;i<s.length();i++){
			char c = s.charAt(i);
			if(c>='0'&&c<='9'){
				n = n*b+((int)c-48);
				}else if(c>='a'&&c<='z'){
					n = n*b+((int)c-87);
					}else if(c>='A'&&c<='Z'){
						n=n*b+((int)c-55);
					}
			}
		return n;
	}
	public static void main(String[] args){
		System.out.println(f1(38,2));
		System.out.println(f1(38,8));
		System.out.println(f1(38,16));
		System.out.println(f2("100110",2));
		System.out.println(f2("38",2));
		System.out.println(f2("26",2));
	}

}
