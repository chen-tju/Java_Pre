package example1;
import java.util.GregorianCalendar;
import java.util.Scanner;
class MyException extends Exception{
	public MyException(){
		super("������Ч");
	}
	public MyException(String s){
		super(s);
	}
}

class Data {
	public int dayNum(int y,int m,int d) throws MyException
	{		if(m<1||m>12||d>31||d<1)
	        {
		      throw new MyException("�������ڲ��Ϸ���");
	        }
	else{
			GregorianCalendar gre = new GregorianCalendar();
			boolean isLeapYear=gre.isLeapYear(y);//����true:�����꣬false����������
			int ap=isLeapYear?29:28;//�ж�2�·ݵ�����
			int days=0;
			switch (m) {
			case 1:
				days=d;
				break;
			case 2:
				days=31+d;
				break;
			case 3:
				days=31+ap+d;
				break;
			case 4:
				days=31+ap+31+d;
				break;
			case 5:
				days=31+ap+31+30+d;
				break;
			case 6:
				days=31+ap+31+30+31+d;
				break;
			case 7:
				days=31+ap+31+30+31+30+d;
				break;
			case 8:
				days=31+ap+31+30+31+30+31+d;
				break;
			case 9:
				days=31+ap+31+30+31+30+31+31+d;
				break;
			case 10:
				days=31+ap+31+30+31+30+31+31+30+d;
				break;
			case 11:
				days=31+ap+31+30+31+30+31+31+30+31+d;
				break;
			case 12:
				days=31+ap+31+30+31+30+31+31+30+31+30+d;
				break;}
			return days;
	}
			
	}
}

public class SolveTwo { 
	public static void main(String[] args){
		Data n = new Data();
		int y,m,d;
		try{
			Scanner scanner = new Scanner (System.in);
			System.out.println("����������:         ");
			String date = scanner.nextLine();
			String ymd[] = date.split("-");
			y = Integer.parseInt(ymd[0]);
			m = Integer.parseInt(ymd[1]);
			d = Integer.parseInt(ymd[2]);
			int days= n.dayNum(y,m,d );
			System.out.println("��һ����"+y+"��ĵ�"+days+"��");
		}catch(MyException ex){
			System.out.println(ex);
		}
	}
}
	//public int dayNum(int y,int m,int d)throws MyException
	//{
	
	//}	
	
	//else
		//throw new MyException("�������ڲ��Ϸ���");

	
		//static void f() throws MyException{
			///throw new MyException();
		//}
		//private static Scanner scanner;
       // public class SolveTwo {
		//public static void main(String[] args){
			//int y,m,d;
			
					
					
					