package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class Fileup {
		//上传文件路径
		private String filePath;
		private String host;
		private int port;
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public int getPort() {
			return port;
		}
		public void setPort(int port) {
			this.port = port;
		}
		public static void main(String[] args) {
				Fileup fu = new Fileup();
				fu.setHost("10.137.194.118");
				fu.setPort(9005);
				fu.setFilePath("C:/Users/132/Desktop/TCP_File/Client");
				fu.uploadFile("a.txt");
		}
		public void uploadFile(String fileName){
			Socket s = null;
			try {
					s = new Socket(host, port);
					//选择进行文件传输的文件
					File fi = new File(filePath + fileName);
					System.out.println("文件名为:  " + fileName + "         文件路径为: " + filePath );
					DataInputStream fis = new DataInputStream(new FileInputStream(filePath + fileName));
					DataOutputStream ps  = new DataOutputStream(s.getOutputStream());
					ps.writeUTF(fi.getName());
					ps.flush();
					ps.writeLong((long) fi.length());
					ps.flush();
					int bufferSize = 8192;
					byte[] buf = new byte[bufferSize];
					while(true) {
							int read = 0;
							if (fis != null) {
									read = fis.read(buf);
							}						
							if(read == -1) {
								break;
							} 
							ps.write(buf,0,read);
					}
					ps.flush();
					//注意关闭socket链接哦，不然客户端会等待server的数据过来
					//直到socket超时，导致数据不完整
					fis.close();
					ps.close();
					s.close();
					System.out.println("文件传输完成");
			}catch (Exception e) {
					e.printStackTrace();
			}
		}		
}
