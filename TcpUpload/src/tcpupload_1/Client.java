package tcpupload_1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
/*
 * �ļ��ϴ�,�ͻ��˷���һ���ļ�
 * ��ȡ�ļ�����,���ͷ�������
 */
public class Client { 
	public static void main(String[] args) throws IOException {
		//�����ͻ���
		Socket socket = new Socket("192.168.2.25", 55555);
		//��ȡIO�����������
		OutputStream out = socket.getOutputStream();
		//��ȡ��Դ�ļ�����������
		FileInputStream fis = new FileInputStream("D:\\a.txt");		
		//��ȡ�ļ�����
		int b;
		while((b=fis.read())!=-1) {
			//������ݸ�������
			out.write(b);
		}
		//�ر���Դ�ļ�������
		fis.close();
		//��֪������,���ٷ���������
		socket.shutdownOutput();
		//��ȡIO������������
		InputStream in = socket.getInputStream();
		//��������
		int read = in.read();
		System.out.println(read);
		System.out.println((char)read);
		//�ر�socket
		socket.close();
	}

}
