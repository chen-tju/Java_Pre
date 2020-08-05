package exam1;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
public class Test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s1 = "���ţ��";
		String s2 = "�������";
		String s3 = "\u4f18\u79c0";
		//String s3 = "���������������";
		List<String> list = new ArrayList<String>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		// [���ع�a��621?!2@#$^*^&)sd sdfhd, ����2����60, ����3˹��10]
		System.out.println(list.toString());
		//����תUnicode
		String chinaToUnicode = Test.chinaToUnicode(list.toString());
		//[\u6210\u91cd\u516ca\u5c3a621?!2@#$^*^&)sd sdfhd, \u4ffa\u4f302\u8ba1\u662f60, \u4ffa\u963f3\u65af\u8fbe10]
		System.out.println(chinaToUnicode);
		//[���ع�a��621?!2@#$^*^&)sd sdfhd, ����2����60, ����3˹��10]
		System.out.println(Test.unicode2String(chinaToUnicode));
	}
	/**
	 * ������ת��Unicode��
	 * 
	 * @param str
	 * @return
	 */
	public static String chinaToUnicode(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int chr1 = (char) str.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941) {// ���ַ�Χ \u4e00-\u9fa5 (����)
				result += "\\u" + Integer.toHexString(chr1);
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}
	/**
	 * �ж��Ƿ�Ϊ�����ַ�
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
	/**
	 * unicode ת ����
	 */
	public static String unicode2String(String unicode) {
		StringBuffer string = new StringBuffer();
		String[] hex = unicode.split("\\\\u");
		for (int i = 0; i < hex.length; i++) {
			try {
				// ���ַ�Χ \u4e00-\u9fa5 (����)
				if(hex[i].length()>=4){//ȡǰ�ĸ����ж��Ƿ��Ǻ���
					String chinese = hex[i].substring(0, 4);
					try {
						int chr = Integer.parseInt(chinese, 16);
						boolean isChinese = Test.isChinese((char) chr);
						//ת���ɹ����ж��Ƿ���  ���ַ�Χ��
						if (isChinese){//�ں��ַ�Χ��
							// ׷�ӳ�string
							string.append((char) chr);
							//����׷��  ������ַ�
							String behindString = hex[i].substring(4);
							string.append(behindString);
						}else {
							string.append(hex[i]);
						}
					} catch (NumberFormatException e1) {
						string.append(hex[i]);
					}					
				}else{
					string.append(hex[i]);
				}
			} catch (NumberFormatException e) {
				string.append(hex[i]);
			}
		}
		return string.toString();
	}
}
