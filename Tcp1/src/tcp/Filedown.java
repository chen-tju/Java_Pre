package tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;

public class Filedown extends Thread {
		//�ļ�����·��
		private String fileDir;
		//socket�������˿�
		private int port;
		//�Ƿ�ֹͣ
		private boolean stop;
		public String getFileDir() {
			return fileDir;
		}
		public void setFileDir(String fileDir) {
			this.fileDir = fileDir;
		}
		public int getPort() {
			return port;
		}
		public void setPort(int port) {
			this.port = port;
		}
		public boolean isStop() {
			return stop;
		}
		public void setStop(boolean stop) {
			this.stop = stop;
		}
		public static void main(String[] args) {
				Filedown fd = new Filedown();
				fd.setFileDir("C:/Users/132/Desktop/TCP_File/Server");
				fd.setPort(9005);
				fd.start();
		}
		@Override
		public void run() {
			    System.out.println("*********************����������*********************");
				Socket socket = null;
				try {
					ServerSocket ss =new ServerSocket(port);
					do {
						socket = ss.accept();
						 // public Socket accept() throws
						// IOException���������ܵ����׽��ֵ����ӡ��˷����ڽ�������֮ǰһֱ������
						System.out.println("����socket����");
						DataInputStream inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
						//���ر���·�����ļ������Զ��ӷ������˼̳ж���
						int bufferSize = 8192;
						byte[] buf = new byte[bufferSize];
						long passedlen = 0;
						long len = 0;
						//��ȡ�ļ���
						String file = fileDir + inputStream.readUTF();
						DataOutputStream fileOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
						len = inputStream.readLong();
						System.out.println("�ļ��ĳ���Ϊ:  " + len  + "\n");
						System.out.println("��ʼ�����ļ�!" + "\n");
						while(true) {
								int read = 0;
								if (inputStream != null) {
									read = inputStream.read(buf);
								}
								passedlen += read;
								if(read == -1) {
									break;
								}
								fileOut.write(buf, 0, read);
								fileOut.flush();
								double a  = Double.valueOf(String.valueOf(passedlen))/Double.valueOf(String.valueOf(len))*100;
								System.out.println("�ļ���ɶ�:  " +a+ "%");
						}
						fileOut.close();
					} while (!stop);
					System.out.println("*********************�������ر�*********************");
				} catch (Exception e) {
						System.out.println("������Ϣ����" + "\n");
						e.printStackTrace();
						return ;
				}
		}
}
