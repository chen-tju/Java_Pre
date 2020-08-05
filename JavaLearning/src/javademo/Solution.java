package javademo;

import java.lang.reflect.Array;
import java.util.Arrays;

class Solution {
    //思路：先把句子分成字符串数组，然后全部变成小写，然后根据长短排序，再把第一个首字母大写，转成字符串
    public static void main(String [] args){
		String text = "To be or not to be";
        String[] strs = text.trim().split(" "); // 删除首尾空格，分割字符串
        StringBuilder res = new StringBuilder();
        strs[0] = strs[0].substring(0,1).toLowerCase()+strs[0].substring(1);
		
        //根据长短排序
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
            //(strs[i].equals("")) continue; // 遇到空单词则跳过
            res.append(strs[i] + " "); // 将单词拼接至 StringBuilder
        }
		System.out.println(res.toString().trim());
		String ans = "正确  On and keep calm code";
		System.out.println(ans);
	}
}


