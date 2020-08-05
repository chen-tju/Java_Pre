package exam1;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
public class Test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s1 = "贾宇晨牛逼";
		String s2 = "贾宇晨优秀";
		String s3 = "\u4f18\u79c0";
		//String s3 = "贾宇晨是王朝臣儿子";
		List<String> list = new ArrayList<String>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		// [成重公a尺621?!2@#$^*^&)sd sdfhd, 俺估2计是60, 俺阿3斯达10]
		System.out.println(list.toString());
		//中文转Unicode
		String chinaToUnicode = Test.chinaToUnicode(list.toString());
		//[\u6210\u91cd\u516ca\u5c3a621?!2@#$^*^&)sd sdfhd, \u4ffa\u4f302\u8ba1\u662f60, \u4ffa\u963f3\u65af\u8fbe10]
		System.out.println(chinaToUnicode);
		//[成重公a尺621?!2@#$^*^&)sd sdfhd, 俺估2计是60, 俺阿3斯达10]
		System.out.println(Test.unicode2String(chinaToUnicode));
	}
	/**
	 * 把中文转成Unicode码
	 * 
	 * @param str
	 * @return
	 */
	public static String chinaToUnicode(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int chr1 = (char) str.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
				result += "\\u" + Integer.toHexString(chr1);
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}
	/**
	 * 判断是否为中文字符
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
	 * unicode 转 中文
	 */
	public static String unicode2String(String unicode) {
		StringBuffer string = new StringBuffer();
		String[] hex = unicode.split("\\\\u");
		for (int i = 0; i < hex.length; i++) {
			try {
				// 汉字范围 \u4e00-\u9fa5 (中文)
				if(hex[i].length()>=4){//取前四个，判断是否是汉字
					String chinese = hex[i].substring(0, 4);
					try {
						int chr = Integer.parseInt(chinese, 16);
						boolean isChinese = Test.isChinese((char) chr);
						//转化成功，判断是否在  汉字范围内
						if (isChinese){//在汉字范围内
							// 追加成string
							string.append((char) chr);
							//并且追加  后面的字符
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
