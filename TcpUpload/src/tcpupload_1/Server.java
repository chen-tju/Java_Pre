package tcpupload_1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/*
 * �ļ��ϴ�,�������˽��տͻ��˵��ļ�
 * 
 * �����ļ�����,д����ĳ���ļ�
 */
public class Server {
	public static void main(String[] args) throws IOException {
		//������������
		ServerSocket ss = new ServerSocket(55555);
		//ͨ��Socket�����ȡSocket,�Ѿ���������Ӷ���
    	Socket socket = ss.accept();
		//�������Ӻ�ͨ��Socket�е�IO��(Socket��)�������ݵĴ��䡣
		InputStream in = socket.getInputStream();
		//��ȡ�ϴ��ļ��ڷ������˵��Ǹ��ļ������
		FileOutputStream fos = new FileOutputStream("D:\\copy.txt");
		//����ʹ��һ���ֽڽ���
		int b;
		while((b=in.read())!=-1) {
			//ÿ�ϴ�һ���ֽ�,��д������������ļ���һ���ֽ�
			//��������ļ��ͽ����������ϴ�������.���ļ��ϴ�
			fos.write(b);
		}
		//�ر��ļ�������
		fos.close();
		//��ȡ�����,��Ӧ����
		OutputStream out = socket.getOutputStream();
		out.write("o".getBytes()[0]);
		//�ر�socket
		socket.close();
		ss.close();
	}
}
