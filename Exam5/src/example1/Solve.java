package example1;
import java.util.GregorianCalendar;
import java.util.Scanner;
public class Solve  {
	private static Scanner scanner;
	public static void main(String[] args){
		int y,m,d;
				
		scanner = new Scanner (System.in);
		System.out.println("��������:         ");
		String date = scanner.nextLine();
		String ymd[] = date.split("-");
		y = Integer.parseInt(ymd[0]);
		m = Integer.parseInt(ymd[1]);
		d = Integer.parseInt(ymd[2]);

		//�ж��Ƿ�������
		//GregorianCalendar:�ж�����Ƿ�������ķ���
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
			break;
		default:
			System.out.println("�·��������");
			break;
		}
		System.out.println("��һ����"+y+"��ĵ�"+days+"��");

}
}
