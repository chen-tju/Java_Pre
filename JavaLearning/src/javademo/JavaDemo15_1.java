package javademo;

import java.util.Arrays;

//数据二分查找

public class JavaDemo15_1 {
	public static void main(String [] args) throws Exception{
		int data[] = new int [] {1,5,7,2,3,6,0};
		Arrays.sort(data);
		System.out.println(Arrays.binarySearch(data, 6));
		System.out.println(Arrays.binarySearch(data, 9));

	}

}
