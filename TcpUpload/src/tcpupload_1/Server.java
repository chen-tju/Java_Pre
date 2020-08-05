package tcpupload_1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/*
 * 文件上传,服务器端接收客户端的文件
 * 
 * 接收文件内容,写出到某个文件
 */
public class Server {
	public static void main(String[] args) throws IOException {
		//建立服务器端
		ServerSocket ss = new ServerSocket(55555);
		//通过Socket服务获取Socket,已经完成了连接动作
    	Socket socket = ss.accept();
		//建立连接后，通过Socket中的IO流(Socket流)进行数据的传输。
		InputStream in = socket.getInputStream();
		//获取上传文件在服务器端的那个文件输出流
		FileOutputStream fos = new FileOutputStream("D:\\copy.txt");
		//反复使用一个字节接收
		int b;
		while((b=in.read())!=-1) {
			//每上传一个字节,就写到服务器这个文件中一个字节
			//最终这个文件就接收了所有上传的数据.即文件上传
			fos.write(b);
		}
		//关闭文件流对象
		fos.close();
		//获取输出流,响应数据
		OutputStream out = socket.getOutputStream();
		out.write("o".getBytes()[0]);
		//关闭socket
		socket.close();
		ss.close();
	}
}
