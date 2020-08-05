package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class Fileup {
		//�ϴ��ļ�·��
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
					//ѡ������ļ�������ļ�
					File fi = new File(filePath + fileName);
					System.out.println("�ļ���Ϊ:  " + fileName + "         �ļ�·��Ϊ: " + filePath );
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
					//ע��ر�socket����Ŷ����Ȼ�ͻ��˻�ȴ�server�����ݹ���
					//ֱ��socket��ʱ���������ݲ�����
					fis.close();
					ps.close();
					s.close();
					System.out.println("�ļ��������");
			}catch (Exception e) {
					e.printStackTrace();
			}
		}		
}
