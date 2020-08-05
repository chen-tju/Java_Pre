package example1;
import java.util.Scanner;
public class GetNextDate {
	private static Scanner scanner;

	public static void main(String[] args){
		int y,m,d;
		int dnum = 0;
		boolean isLeap = false;
		scanner = new Scanner (System.in);
		String date = scanner.nextLine();
		String ymd[] = date.split("-");
		y = Integer.parseInt(ymd[0]);
		m = Integer.parseInt(ymd[1]);
		d = Integer.parseInt(ymd[2]);
		if(y<1000||y>9999||m<1||m>12){
			System.out.println("日期不合法!");
			return;
		}
		if((y%400 == 0)||(y%4 == 0&&y%100 != 0)){
			isLeap = true;
		}
		switch(m){
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			dnum=31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			dnum=30;
			break;
		case 2:
			if(isLeap){
				dnum=29;
			}else{
				dnum=28;
			}
		}
		if(d<1||d>dnum){
			System.out.println("日期不合法！");
			return;
			}
			if(d != dnum){
				d=d++;
			}else{
				if(m==12){
					y++;
					m = 1;
					d = 1;
				}else{
					m++;
					d=1;
				}
			}
		
		System.out.println(y+"-" + m + "-" + d);
		}

}
