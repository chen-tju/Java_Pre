package javademo;

import java.lang.reflect.Array;
import java.util.Arrays;

class Solution {
    //˼·���ȰѾ��ӷֳ��ַ������飬Ȼ��ȫ�����Сд��Ȼ����ݳ��������ٰѵ�һ������ĸ��д��ת���ַ���
    public static void main(String [] args){
		String text = "To be or not to be";
        String[] strs = text.trim().split(" "); // ɾ����β�ո񣬷ָ��ַ���
        StringBuilder res = new StringBuilder();
        strs[0] = strs[0].substring(0,1).toLowerCase()+strs[0].substring(1);
		
        //���ݳ�������
		for(int i = 0; i < strs.length - 1; i++){
			int min = i;
            for(int j = i + 1; j < strs.length; j++){
               if(strs[min].length() >= strs[j].length()){
            	   min = j;                   
               } 
            }
            if(min != i){
            	String temp = strs[i];
                strs[i] = strs[min];
                strs[min] = temp;
            }
        }
        strs[0] = strs[0].substring(0,1).toUpperCase()+strs[0].substring(1);
        for(int i = 0; i < strs.length; i++){
            //(strs[i].equals("")) continue; // �����յ���������
            res.append(strs[i] + " "); // ������ƴ���� StringBuilder
        }
		System.out.println(res.toString().trim());
		String ans = "��ȷ  On and keep calm code";
		System.out.println(ans);
	}
}


