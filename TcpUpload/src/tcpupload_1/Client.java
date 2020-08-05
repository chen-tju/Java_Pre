package tcpupload_1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
/*
 * 文件上传,客户端发送一个文件
 * 读取文件内容,发送服务器端
 */
public class Client { 
	public static void main(String[] args) throws IOException {
		//建立客户端
		Socket socket = new Socket("192.168.2.25", 55555);
		//获取IO流输出流对象
		OutputStream out = socket.getOutputStream();
		//获取资源文件输入流对象
		FileInputStream fis = new FileInputStream("D:\\a.txt");		
		//读取文件内容
		int b;
		while((b=fis.read())!=-1) {
			//输出数据给服务器
			out.write(b);
		}
		//关闭资源文件输入流
		fis.close();
		//告知服务器,不再发送数据了
		socket.shutdownOutput();
		//获取IO流输入流对象
		InputStream in = socket.getInputStream();
		//输入数据
		int read = in.read();
		System.out.println(read);
		System.out.println((char)read);
		//关闭socket
		socket.close();
	}

}
