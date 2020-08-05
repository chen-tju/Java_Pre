package example1;
class IllegalDataException extends Exception{
	public IllegalDataException(){
		super("数据无效");
	}
	public IllegalDataException(String s)
	{
		super(s);
	}
}
class MyApp{
	public double area(float a,float b,float c) throws IllegalDataException
	{
		if(a+b>c&&a+c>b&&b+c>a)
		{
			float p = (a+b+c)/2;
			return Math.sqrt(p*(p-a)*(p-b)*(p-c));
		}else
			throw new IllegalDataException("三角形数据错误");
	}
}
public class TestMyApp {
	public static void main (String[] args){
		MyApp m = new MyApp();
		try{
			double s1 = m.area(3, 4, 5);
			System.out.println(s1);
			double s2 = m.area(3, 4, 1);
			System.out.println(s1);
		}catch(IllegalDataException ex){
			System.out.println(ex);
		}
	}

}
