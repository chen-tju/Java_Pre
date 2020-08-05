package exam1;
import java.io.File;
public class DemoFile {
	private static void deleteFile(File file){
		if(file !=null){
			if(file.isFile()){
				file.delete();
			}else if(file.isDirectory()){
				File files[] = file.listFiles();
				for(int  i=0;i<files.length;i++){
					deleteFile(files[i]);
				}
				file.delete();
			}
		}
	}
	private static void listFile(File file,int level){
		if(file!=null){
			if(file.isFile()){
				System.out.println(String.format("%1$s %2$tY- %2$tm- %2$-5td %2$tH: %2$-7tM %3$20d  %4$s",aaa(level),file.lastModified(),file.length(),file.getName()));
			}else if(file.isDirectory()){
				File files[] = file.listFiles();
				System.out.println(String.format("%1$s %2$tY- %2$tm- %2$-5td %2$tH: %2$-7tM %3$-20s %4$s",aaa(level),file.lastModified(),"<dir>",file.getName()));
				for(int i = 0;i<files.length;i++){
					listFile(files[i],level+1);
				}
			}
		}
	}
	private static String aaa(int n){
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<n;i++){
			sb.append("   ");
		}
		return sb.toString();
	}
	public static void main(String[] args){
		File file = new File("D:/a");
		listFile(file,1);
		deleteFile(file);
		listFile(file,1);
	}

}
